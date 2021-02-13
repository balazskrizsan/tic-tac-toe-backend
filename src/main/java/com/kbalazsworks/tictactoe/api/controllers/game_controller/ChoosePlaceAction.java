package com.kbalazsworks.tictactoe.api.controllers.game_controller;

import com.kbalazsworks.tictactoe.api.builders.ResponseEntityBuilder;
import com.kbalazsworks.tictactoe.api.requests.game_requests.ChoosePlaceCreateRequest;
import com.kbalazsworks.tictactoe.api.services.JavaxValidatorService;
import com.kbalazsworks.tictactoe.api.services.RequestMapperService;
import com.kbalazsworks.tictactoe.api.value_objects.ResponseData;
import com.kbalazsworks.tictactoe.domain.services.GameService;
import com.kbalazsworks.tictactoe.domain.value_objects.ActiveGameState;
import com.kbalazsworks.tictactoe.state.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("ChoosePlaceAction")
@RequestMapping(GameConfig.CONTROLLER_URI_V1)
public class ChoosePlaceAction
{
    private GameService  gameService;
    private StateService stateService;

    @Autowired
    public void setGameService(GameService gameService)
    {
        this.gameService = gameService;
    }

    @Autowired
    public void setStateService(StateService stateService)
    {
        this.stateService = stateService;
    }

    @PostMapping("/{gameId}/choose-place")
    public ResponseEntity<ResponseData<ActiveGameState>> action(
        ChoosePlaceCreateRequest request,
        @PathVariable long gameId
    )
    throws Exception
    {
        new JavaxValidatorService<ChoosePlaceCreateRequest>().validate(request);

        return new ResponseEntityBuilder<ActiveGameState>()
            .setData(
                gameService.choosePlace(
                    RequestMapperService.mapToEntity(
                        gameId,
                        request,
                        stateService.getState()
                    )
                )
            )
            .build();
    }
}

