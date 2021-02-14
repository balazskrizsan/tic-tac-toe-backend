package com.kbalazsworks.tictactoe.domain.repositories;

import com.kbalazsworks.tictactoe.domain.entities.GameState;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameStateRepository extends AbstractRepository
{
    private final com.kbalazsworks.tictactoe.db.tables.GameState gameStateTable =
        com.kbalazsworks.tictactoe.db.tables.GameState.GAME_STATE;

    public void create(GameState gameState)
    {
        getQueryBuilder()
            .insertInto(
                gameStateTable,
                gameStateTable.GAME_ID,
                gameStateTable.PLACE,
                gameStateTable.PLAYERID,
                gameStateTable.CREATED_AT
            )
            .values(
                gameState.gameId(),
                gameState.placeId(),
                gameState.playerId(),
                gameState.createdAt()
            )
            .execute();
    }

    public List<GameState> searchByGameId(long gameId)
    {
        return getQueryBuilder()
            .selectFrom(gameStateTable)
            .where(gameStateTable.GAME_ID.eq(gameId))
            .fetchInto(GameState.class);
    }
}
