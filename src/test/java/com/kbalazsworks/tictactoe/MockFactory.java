package com.kbalazsworks.tictactoe;

import com.kbalazsworks.tictactoe.state.entities.State;

import java.time.LocalDateTime;

public class MockFactory
{
    public static final LocalDateTime localDateTimeMock = LocalDateTime.of(2011, 1, 2, 3, 4, 5);
    public static final State         stateMock         = new State(localDateTimeMock);
}
