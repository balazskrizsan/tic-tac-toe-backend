package com.kbalazsworks.tictactoe;

import com.kbalazsworks.tictactoe.state.entities.State;
import com.kbalazsworks.tictactoe.state.services.StateService;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

public class MockFactory
{
    public static final LocalDateTime localDateTimeMock = LocalDateTime.of(2011, 1, 2, 3, 4, 5);
    public static final State         stateMock         = new State(localDateTimeMock);

    public static void onStateService_setStateMock(StateService stateService)
    {
        when(stateService.getState()).thenReturn(stateMock);
    }
}
