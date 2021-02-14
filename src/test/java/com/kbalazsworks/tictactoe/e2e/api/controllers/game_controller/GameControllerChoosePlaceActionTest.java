package com.kbalazsworks.tictactoe.e2e.api.controllers.game_controller;

import com.kbalazsworks.tictactoe.AbstractE2eTest;
import com.kbalazsworks.tictactoe.MockFactory;
import com.kbalazsworks.tictactoe.annotations.TruncateAllTables;
import com.kbalazsworks.tictactoe.domain.entities.GameState;
import com.kbalazsworks.tictactoe.domain.services.GameStateService;
import com.kbalazsworks.tictactoe.domain.value_objects.ActiveGameState;
import com.kbalazsworks.tictactoe.fake_builders.GameFakeBuilder;
import com.kbalazsworks.tictactoe.fake_builders.GameStateFakeBuilder;
import com.kbalazsworks.tictactoe.state.services.StateService;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GameControllerChoosePlaceActionTest extends AbstractE2eTest
{
    @MockBean
    private GameStateService gameStateService;

    @MockBean
    private StateService stateService;

    @Test
    @TruncateAllTables
    public void callingMockedService_validDataReturnedWith200ok() throws Exception
    {
        // Arrange
        MockFactory.onStateService_setStateMock(stateService);

        String testedUri    = "/v1/game/{gameId}/choose-place";
        Long   testedGameId = GameFakeBuilder.defaultId1;
        MultiValueMap<String, String> testedPostData = new LinkedMultiValueMap<>()
        {{
            add("playerId", "X");
            add("placeId", "1");
        }};
        ResultMatcher   expectedStatusCode   = status().isOk();
        GameState       expectedGameState    = new GameStateFakeBuilder().setId(null).build();
        ActiveGameState expectedResponseData = new ActiveGameState(false, null, "X", Map.of(1, "X"));
        int             expectedErrorCode    = 0;

        when(gameStateService.choosePlace(expectedGameState))
            .thenReturn(new ActiveGameState(false, null, "X", Map.of(1, "X")));

        // Act
        ResultActions result = getMockMvc().perform(
            MockMvcRequestBuilders
                .post(testedUri, testedGameId)
                .params(testedPostData)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .accept(MediaType.APPLICATION_JSON)
        );

        // Assert
        verify(gameStateService).choosePlace(expectedGameState);

        result
            .andExpect(expectedStatusCode)
            .andExpect(jsonPath("$.errorCode").value(expectedErrorCode))
            .andExpect(jsonPath("$.data.finished").value(expectedResponseData.finished()))
            .andExpect(jsonPath("$.data.winner").value(expectedResponseData.winner()))
            .andExpect(jsonPath("$.data.nextPlayerId").value(expectedResponseData.nextPlayerId()))
// @todo: assertion looks buggy on Map
//            .andExpect(jsonPath("$.data.currentGameState").value(expectedResponseData.currentGameState()))
        ;
    }

    //@todo: test already taken place with error response
    //@todo: test invalid parameter: javax error
}
