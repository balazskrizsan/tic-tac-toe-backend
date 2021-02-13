package com.kbalazsworks.tictactoe.fake_builders;

import com.kbalazsworks.tictactoe.MockFactory;
import com.kbalazsworks.tictactoe.domain.entities.GameState;

import java.time.LocalDateTime;

public class GameStateFakeBuilder
{
    public static final Long defaultId1 = 101001L;

    private Long          id        = defaultId1;
    private long          gameId    = GameFakeBuilder.defaultId1;
    private short         placeId   = 1;
    private String        playerId  = "X";
    private LocalDateTime createdAt = MockFactory.localDateTimeMock;

    public GameState build()
    {
        return new GameState(id, gameId, placeId, playerId, createdAt);
    }

    public GameStateFakeBuilder setId(Long id)
    {
        this.id = id;

        return this;
    }

    public GameStateFakeBuilder setGameId(long gameId)
    {
        this.gameId = gameId;

        return this;
    }

    public GameStateFakeBuilder setPlaceId(short placeId)
    {
        this.placeId = placeId;

        return this;
    }

    public GameStateFakeBuilder setPlayerId(String playerId)
    {
        this.playerId = playerId;

        return this;
    }

    public GameStateFakeBuilder setCreatedAt(LocalDateTime createdAt)
    {
        this.createdAt = createdAt;

        return this;
    }
}
