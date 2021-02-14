package com.kbalazsworks.tictactoe;

import com.kbalazsworks.tictactoe.domain.services.JooqService;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractIntegrationTest extends AbstractTest
{
    protected final com.kbalazsworks.tictactoe.db.tables.GameState gameStateTable =
        com.kbalazsworks.tictactoe.db.tables.GameState.GAME_STATE;

    protected final com.kbalazsworks.tictactoe.db.tables.Game gameTable =
        com.kbalazsworks.tictactoe.db.tables.Game.GAME;

    @Autowired
    private JooqService jooqService;

    protected DSLContext getQueryBuilder()
    {
        return jooqService.getDbContext();
    }
}
