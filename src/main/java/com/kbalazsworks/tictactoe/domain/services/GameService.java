package com.kbalazsworks.tictactoe.domain.services;

import com.kbalazsworks.tictactoe.domain.entities.Game;
import com.kbalazsworks.tictactoe.domain.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GameService
{
    private GameRepository gameRepository;

    @Autowired
    public void setReviewRepository(GameRepository gameRepository)
    {
        this.gameRepository = gameRepository;
    }

    public Game startNewGame()
    {
        return new Game(1L, "X", LocalDateTime.now());
    }
}
