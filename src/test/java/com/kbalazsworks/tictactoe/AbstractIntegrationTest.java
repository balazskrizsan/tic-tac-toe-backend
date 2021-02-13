package com.kbalazsworks.tictactoe;

import com.kbalazsworks.tictactoe.domain.services.JooqService;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractIntegrationTest extends AbstractTest
{
    @Autowired
    private JooqService jooqService;

    protected DSLContext getQueryBuilder()
    {
        return jooqService.getDbContext();
    }
}
