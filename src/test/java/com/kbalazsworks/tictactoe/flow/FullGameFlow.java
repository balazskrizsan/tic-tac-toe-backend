package com.kbalazsworks.tictactoe.flow;

import com.kbalazsworks.tictactoe.AbstractE2eTest;
import com.kbalazsworks.tictactoe.annotations.TruncateAllTables;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FullGameFlow extends AbstractE2eTest
{
    @Test
    @TruncateAllTables
    public void created5PositionStep_xWonTheGame() throws Exception
    {
        long gameId = startGame();

        choosePlace(gameId, "X", "1");
        choosePlace(gameId, "O", "4");
        choosePlace(gameId, "X", "2");
        choosePlace(gameId, "O", "9");
        ResultActions result = choosePlace(gameId, "X", "3");

        result
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.errorCode").value(0))
            .andExpect(jsonPath("$.data.finished").value(true))
            .andExpect(jsonPath("$.data.winner").value("X"))
            .andExpect(jsonPath("$.data.nextPlayerId").value(IsNull.nullValue()));
    }

    @Test
    @TruncateAllTables
    public void created5PositionStep_oWonTheGame() throws Exception
    {
        long gameId = startGame();

        choosePlace(gameId, "O", "7");
        choosePlace(gameId, "X", "4");
        choosePlace(gameId, "O", "8");
        choosePlace(gameId, "X", "5");
        ResultActions result = choosePlace(gameId, "O", "9");

        result
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.errorCode").value(0))
            .andExpect(jsonPath("$.data.finished").value(true))
            .andExpect(jsonPath("$.data.winner").value("O"))
            .andExpect(jsonPath("$.data.nextPlayerId").value(IsNull.nullValue()));
    }

    @Test
    @TruncateAllTables
    public void created5PositionStep_tie() throws Exception
    {
        long gameId = startGame();

        choosePlace(gameId, "O", "1");
        choosePlace(gameId, "X", "2");
        choosePlace(gameId, "O", "3");
        choosePlace(gameId, "X", "5");
        choosePlace(gameId, "O", "4");
        choosePlace(gameId, "X", "6");
        choosePlace(gameId, "O", "8");
        choosePlace(gameId, "X", "7");
        ResultActions result = choosePlace(gameId, "O", "9");

        result
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.errorCode").value(0))
            .andExpect(jsonPath("$.data.finished").value(true))
            .andExpect(jsonPath("$.data.winner").value("tie"))
            .andExpect(jsonPath("$.data.nextPlayerId").value(IsNull.nullValue()));
    }

    private ResultActions choosePlace(long gameId, String playerId, String placeId) throws Exception
    {
        String gamePlaceChooseUri = "/v1/game/{gameId}/choose-place";

        MultiValueMap<String, String> testedPostData = new LinkedMultiValueMap<>()
        {{
            add("playerId", playerId);
            add("placeId", placeId);
        }};
        return getMockMvc().perform(
            MockMvcRequestBuilders
                .post(gamePlaceChooseUri, gameId)
                .params(testedPostData)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .accept(MediaType.APPLICATION_JSON)
        );
    }

    private long startGame() throws Exception
    {
        String gameStartUri = "/v1/game/start";
        getMockMvc().perform(
            MockMvcRequestBuilders
                .get(gameStartUri)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .accept(MediaType.APPLICATION_JSON)
        );

        return 1; // Should be come from the returned JSON, temp solution
    }
}
