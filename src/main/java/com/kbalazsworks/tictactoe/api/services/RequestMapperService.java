package com.kbalazsworks.tictactoe.api.services;

import com.kbalazsworks.tictactoe.api.requests.game_requests.ChoosePlaceCreateRequest;
import com.kbalazsworks.tictactoe.domain.entities.GameState;
import com.kbalazsworks.tictactoe.state.entities.State;

public class RequestMapperService
{
    public static GameState mapToEntity(long gameId, ChoosePlaceCreateRequest request, State state)
    {
        return new GameState(null, gameId, request.placeId(), request.playerId(), state.getNow());
    }
}
