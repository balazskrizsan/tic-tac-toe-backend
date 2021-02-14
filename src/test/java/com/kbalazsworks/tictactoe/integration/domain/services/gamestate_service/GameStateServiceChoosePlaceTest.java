package com.kbalazsworks.tictactoe.integration.domain.services.gamestate_service;

import com.kbalazsworks.tictactoe.AbstractIntegrationTest;
import com.kbalazsworks.tictactoe.domain.entities.GameState;
import com.kbalazsworks.tictactoe.domain.services.GameStateService;
import com.kbalazsworks.tictactoe.domain.value_objects.ActiveGameState;
import com.kbalazsworks.tictactoe.fake_builders.GameStateFakeBuilder;
import org.junit.Test;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.platform.commons.JUnitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

public class GameStateServiceChoosePlaceTest extends AbstractIntegrationTest
{
    @Autowired
    private GameStateService gameStateService;

    @Test
    @SqlGroup(
        {
            @Sql(
                executionPhase = BEFORE_TEST_METHOD,
                config = @SqlConfig(transactionMode = ISOLATED),
                scripts = {
                    "classpath:test/sqls/_truncate_tables.sql",
                    "classpath:test/sqls/preset_insert_one_game.sql"
                }
            ),
            @Sql(
                executionPhase = AFTER_TEST_METHOD,
                config = @SqlConfig(transactionMode = ISOLATED),
                scripts = {"classpath:test/sqls/_truncate_tables.sql"}
            )
        }
    )
    public void insertOnlyOneValidState_returnWillContinueTheGame()
    {
        // Arrange
        GameState testedGameState      = new GameStateFakeBuilder().build();
        GameState expectedGameState    = new GameStateFakeBuilder().setId(1L).build();
        String    expectedNextPlayerId = "O";
        boolean   expectedFinished     = false;
        String    expectedWinner       = null;
        Map<Integer, String> expectedGameStateMap = Map.of(
            1, "X",
            2, "-",
            3, "-",
            4, "-",
            5, "-",
            6, "-",
            7, "-",
            8, "-",
            9, "-"
        );

        // Act
        ActiveGameState actualActiveGameState = gameStateService.choosePlace(testedGameState);

        // Assert
        GameState actualGameState = getQueryBuilder().selectFrom(gameStateTable).fetchOneInto(GameState.class);

        assertAll(
            () -> assertThat(actualGameState).isEqualTo(expectedGameState),
            () -> assertThat(actualActiveGameState.finished()).isEqualTo(expectedFinished),
            () -> assertThat(actualActiveGameState.winner()).isEqualTo(expectedWinner),
            () -> assertThat(actualActiveGameState.nextPlayerId()).isEqualTo(expectedNextPlayerId),
            () -> assertThat(actualActiveGameState.currentGameStatePlace()).isEqualTo(expectedGameStateMap)
        );
    }

    private record TestData(
        GameState testedGameState,
        String expectedNextPlayerId,
        boolean expectedFinished,
        String expectedWinner,
        Map<Integer, String> expectedGameStateMap
    )
    {
    }


    private TestData provider(int currentRepetition)
    {
        if (currentRepetition == 1)
        {
            return new TestData(new GameStateFakeBuilder().build(), null, true, "X", Map.of(
                1, "X",
                2, "X",
                3, "X",
                4, "O",
                5, "-",
                6, "-",
                7, "O",
                8, "-",
                9, "-"
            )
            );
        }

        if (currentRepetition == 2)
        {
            return new TestData(new GameStateFakeBuilder().setPlayerId("O").build(), null, true, "O", Map.of(
                1, "O",
                2, "X",
                3, "X",
                4, "O",
                5, "-",
                6, "-",
                7, "O",
                8, "-",
                9, "-"
            )
            );
        }

        throw new JUnitException("Repetition not found with id#" + currentRepetition);
    }

    @RepeatedTest(2)
    @SqlGroup(
        {
            @Sql(
                executionPhase = BEFORE_TEST_METHOD,
                config = @SqlConfig(transactionMode = ISOLATED),
                scripts = {
                    "classpath:test/sqls/_truncate_tables.sql",
                    "classpath:test/sqls/preset_insert_one_game.sql",
                    "classpath:test/sqls/preset_insert_4_game_state_1_to_win.sql",
                }
            ),
            @Sql(
                executionPhase = AFTER_TEST_METHOD,
                config = @SqlConfig(transactionMode = ISOLATED),
                scripts = {"classpath:test/sqls/_truncate_tables.sql"}
            )
        }
    )
    public void insertOneXToWonTheGame_wonByProvider(RepetitionInfo repetitionInfo)
    {
        // Arrange
        TestData testData = provider(repetitionInfo.getCurrentRepetition());

        // Act
        ActiveGameState actualActiveGameState = gameStateService.choosePlace(testData.testedGameState());

        // Assert
        assertAll(
            () -> assertThat(actualActiveGameState.finished()).isEqualTo(testData.expectedFinished()),
            () -> assertThat(actualActiveGameState.winner()).isEqualTo(testData.expectedWinner()),
            () -> assertThat(actualActiveGameState.nextPlayerId()).isEqualTo(testData.expectedNextPlayerId()),
            () -> assertThat(actualActiveGameState.currentGameStatePlace()).isEqualTo(testData.expectedGameStateMap())
        );
    }

    @Test
    @SqlGroup(
        {
            @Sql(
                executionPhase = BEFORE_TEST_METHOD,
                config = @SqlConfig(transactionMode = ISOLATED),
                scripts = {
                    "classpath:test/sqls/_truncate_tables.sql",
                    "classpath:test/sqls/preset_insert_one_game.sql",
                    "classpath:test/sqls/preset_insert_8_game_state_1_to_tie.sql"
                }
            ),
            @Sql(
                executionPhase = AFTER_TEST_METHOD,
                config = @SqlConfig(transactionMode = ISOLATED),
                scripts = {"classpath:test/sqls/_truncate_tables.sql"}
            )
        }
    )
    public void insertOnlyOneValidState_returnTie()
    {
        // Arrange
        GameState testedGameState      = new GameStateFakeBuilder().build();
        String    expectedNextPlayerId = null;
        boolean   expectedFinished     = true;
        String    expectedWinner       = "tie";
        Map<Integer, String> expectedGameStateMap = Map.of(
            1, "X",
            2, "X",
            3, "O",
            4, "O",
            5, "X",
            6, "X",
            7, "X",
            8, "O",
            9, "O"
        );

        // Act
        ActiveGameState actualActiveGameState = gameStateService.choosePlace(testedGameState);

        // Assert
        assertAll(
            () -> assertThat(actualActiveGameState.finished()).isEqualTo(expectedFinished),
            () -> assertThat(actualActiveGameState.winner()).isEqualTo(expectedWinner),
            () -> assertThat(actualActiveGameState.nextPlayerId()).isEqualTo(expectedNextPlayerId),
            () -> assertThat(actualActiveGameState.currentGameStatePlace()).isEqualTo(expectedGameStateMap)
        );
    }
}
