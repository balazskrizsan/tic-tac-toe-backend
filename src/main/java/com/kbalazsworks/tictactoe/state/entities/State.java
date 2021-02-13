package com.kbalazsworks.tictactoe.state.entities;

import java.time.LocalDateTime;

public class State
{
    private final LocalDateTime now;

    public State(LocalDateTime now)
    {
        this.now  = now;
    }

    public LocalDateTime getNow()
    {
        return now;
    }
}
