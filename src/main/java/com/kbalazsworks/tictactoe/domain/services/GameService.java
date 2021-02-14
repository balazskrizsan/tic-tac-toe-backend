package com.kbalazsworks.tictactoe.domain.services;

import com.kbalazsworks.tictactoe.domain.entities.Game;
import com.kbalazsworks.tictactoe.domain.repositories.GameRepository;
import com.kbalazsworks.tictactoe.state.entities.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        String starerUserId = (int) (Math.random() * 2) == 1 ? "X" : "O";

        Long newGameId = gameRepository.create(new Game(null, starerUserId, state.getNow()));

        return new Game(newGameId, starerUserId, state.getNow());
    }
}
