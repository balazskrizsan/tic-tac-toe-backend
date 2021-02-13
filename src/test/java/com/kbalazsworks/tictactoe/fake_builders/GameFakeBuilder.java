package com.kbalazsworks.tictactoe.fake_builders;

import com.kbalazsworks.tictactoe.MockFactory;
import com.kbalazsworks.tictactoe.domain.entities.Game;

import java.time.LocalDateTime;

public class GameFakeBuilder
{
    public static final Long defaultId1 = 100001L;

    private Long          id         = defaultId1;
    private String        starerUser = "X";
    private LocalDateTime createdAt  = MockFactory.localDateTimeMock;

    public Game build()
    {
        return new Game(id, starerUser, createdAt);
    }

    public GameFakeBuilder setId(Long id)
    {
        this.id = id;

        return this;
    }

    public GameFakeBuilder setStarerUser(String starerUser)
    {
        this.starerUser = starerUser;

        return this;
    }

    public GameFakeBuilder setCreatedAt(LocalDateTime createdAt)
    {
        this.createdAt = createdAt;

        return this;
    }
}
