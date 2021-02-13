package com.kbalazsworks.tictactoe.state.services;

import com.kbalazsworks.tictactoe.state.entities.State;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;

@Service
public class StateService
{
    public State getState()
    {
        return new State(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }
}
