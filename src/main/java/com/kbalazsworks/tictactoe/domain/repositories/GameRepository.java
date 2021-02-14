package com.kbalazsworks.tictactoe.domain.repositories;

import com.kbalazsworks.tictactoe.domain.entities.Game;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepository extends AbstractRepository
{
    private final com.kbalazsworks.tictactoe.db.tables.Game gameTable =
        com.kbalazsworks.tictactoe.db.tables.Game.GAME;

    public Long create(Game game)
    {
        return getQueryBuilder()
            .insertInto(
                gameTable,
                gameTable.STARER_USER_ID,
                gameTable.CREATED_AT
            )
            .values(game.starerUserId(), game.createdAt())
            .returningResult(gameTable.ID)
            .fetchOne()
            .getValue(gameTable.ID);
    }
}
