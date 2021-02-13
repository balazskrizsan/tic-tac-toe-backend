package com.kbalazsworks.tictactoe.domain.repositories;

import com.kbalazsworks.tictactoe.domain.services.JooqService;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
abstract public class AbstractRepository
{
    private JooqService jooqService;

    @Autowired
    public void setJooqService(JooqService jooqService)
    {
        this.jooqService = jooqService;
    }

    DSLContext createQueryBuilder()
    {
        return jooqService.getDbContext();
    }
}
