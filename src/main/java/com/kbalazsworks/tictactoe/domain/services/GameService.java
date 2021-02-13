package com.kbalazsworks.tictactoe.domain.services;

import com.kbalazsworks.tictactoe.domain.entities.Game;
import com.kbalazsworks.tictactoe.domain.entities.GameState;
import com.kbalazsworks.tictactoe.domain.repositories.GameRepository;
import com.kbalazsworks.tictactoe.domain.value_objects.ActiveGameState;
import com.kbalazsworks.tictactoe.state.entities.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GameService
{
    private GameRepository gameRepository;

    @Autowired
    public void setGameRepository(GameRepository gameRepository)
    {
        this.gameRepository = gameRepository;
    }

    public Game startNewGame(State state)
    {
        String starerUserId = (int) (Math.random() * 2) == 1 ? "X" : "Y";

        Long newGameId = gameRepository.create(new Game(null, starerUserId, state.getNow()));

        return new Game(newGameId, starerUserId, state.getNow());
    }

    public ActiveGameState choosePlace(GameState mapToEntity)
    {
        return new ActiveGameState(false, null, "X", Map.of(1, "X"));
    }
}
