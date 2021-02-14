package com.kbalazsworks.tictactoe.integration.domain.services.game_service;

import com.kbalazsworks.tictactoe.AbstractIntegrationTest;
import com.kbalazsworks.tictactoe.MockFactory;
import com.kbalazsworks.tictactoe.annotations.TruncateAllTables;
import com.kbalazsworks.tictactoe.domain.entities.Game;
import com.kbalazsworks.tictactoe.domain.services.GameService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class GameServiceStartNewGameTest extends AbstractIntegrationTest
{
    @Autowired
    private GameService gameService;

    @Test
    @TruncateAllTables
    public void newGameStarting_returnsWithRandomStarterAndGameId()
    {
        // Arrange
        long          expectedUserId        = 1;
        String        expectedStarterUserId = "^[X|Y]$";
        LocalDateTime expectedCreatedAt     = MockFactory.stateMock.getNow();

        // Act
        Game actualGame = gameService.startNewGame(MockFactory.stateMock);
        //@todo: check if all field inserted in sql

        // Assert
        assertAll(
            () -> assertThat(actualGame.id()).isEqualTo(expectedUserId),
            () -> assertThat(actualGame.starerUserId()).matches(expectedStarterUserId),
            () -> assertThat(actualGame.createdAt()).isEqualTo(expectedCreatedAt)
        );
    }
}
