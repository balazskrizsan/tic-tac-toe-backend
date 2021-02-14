package com.kbalazsworks.tictactoe.e2e.api.controllers.game_controller;

import com.kbalazsworks.tictactoe.AbstractE2eTest;
import com.kbalazsworks.tictactoe.annotations.TruncateAllTables;
import com.kbalazsworks.tictactoe.domain.entities.Game;
import com.kbalazsworks.tictactoe.domain.services.GameService;
import com.kbalazsworks.tictactoe.fake_builders.GameFakeBuilder;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GameControllerStartActionTest extends AbstractE2eTest
{
    @MockBean
    private GameService gameService;

    @Test
    @TruncateAllTables
    public void callingMockedService_validDataReturnedWith200ok() throws Exception
    {
        // Arrange
        String testedUri = "/v1/game/start";

        ResultMatcher expectedStatusCode = status().isOk();
        Game          expectedData       = new GameFakeBuilder().build();
        int           expectedErrorCode  = 0;

        Game mockedNewGame = new GameFakeBuilder().build();
        when(gameService.startNewGame(any())).thenReturn(mockedNewGame);

        // Act
        ResultActions result = getMockMvc().perform(
            MockMvcRequestBuilders
                .get(testedUri)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .accept(MediaType.APPLICATION_JSON)
        );

        // Assert
        result
            .andExpect(expectedStatusCode)
            .andExpect(jsonPath("$.errorCode").value(expectedErrorCode))
            .andExpect(jsonPath("$.data.id").value(expectedData.id()))
            .andExpect(jsonPath("$.data.starerUserId").value(expectedData.starerUserId()))
            .andExpect(jsonPath("$.data.createdAt").value(expectedData.createdAt().toString()));
    }
}
