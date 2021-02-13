package com.kbalazsworks.tictactoe.api.controllers.game_controller;

import com.kbalazsworks.tictactoe.api.builders.ResponseEntityBuilder;
import com.kbalazsworks.tictactoe.api.value_objects.ResponseData;
import com.kbalazsworks.tictactoe.domain.entities.Game;
import com.kbalazsworks.tictactoe.domain.services.GameService;
import com.kbalazsworks.tictactoe.state.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("GameStartAction")
@RequestMapping(GameConfig.CONTROLLER_URI_V1)
public class StartAction
{
    private GameService gameService;
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

    @GetMapping("/start")
    public ResponseEntity<ResponseData<Game>> action() throws Exception
    {
        return new ResponseEntityBuilder<Game>().setData(gameService.startNewGame(stateService.getState())).build();
    }
}

