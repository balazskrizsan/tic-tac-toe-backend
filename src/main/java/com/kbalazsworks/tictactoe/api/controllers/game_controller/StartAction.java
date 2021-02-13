package com.kbalazsworks.tictactoe.api.controllers.game_controller;

import com.kbalazsworks.tictactoe.api.builders.ResponseEntityBuilder;
import com.kbalazsworks.tictactoe.api.value_objects.ResponseData;
import com.kbalazsworks.tictactoe.domain.entities.Game;
import com.kbalazsworks.tictactoe.domain.services.GameService;
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

    @Autowired
    public void setGameService(GameService gameService)
    {
        this.gameService = gameService;
    }

    @GetMapping("/start")
    public ResponseEntity<ResponseData<Game>> action() throws Exception
    {
        return new ResponseEntityBuilder<Game>().setData(gameService.startNewGame()).build();
    }
}

