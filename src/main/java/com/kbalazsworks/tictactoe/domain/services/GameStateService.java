package com.kbalazsworks.tictactoe.domain.services;

import com.kbalazsworks.tictactoe.domain.entities.GameState;
import com.kbalazsworks.tictactoe.domain.repositories.GameStateRepository;
import com.kbalazsworks.tictactoe.domain.value_objects.ActiveGameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameStateService
{
    private GameStateRepository gameStateRepository;

    @Autowired
    public void setGameStateRepository(GameStateRepository gameStateRepository)
    {
        this.gameStateRepository = gameStateRepository;
    }

    public ActiveGameState choosePlace(GameState gameState)
    {
        //@todo: validate if already used place on gameId
        gameStateRepository.create(gameState);

        List<GameState> newGameState = gameStateRepository.searchByGameId(gameState.gameId());

        Map<Integer, String> gameStatePlaceMap = getStateMap(newGameState);

        if (newGameState.size() >= 9)
        {
            return new ActiveGameState(true, "tie", null, gameStatePlaceMap);
        }

        String winner = checkGameEnd(newGameState);
        if (winner != null)
        {
            return new ActiveGameState(true, winner, null, gameStatePlaceMap);
        }

        String nextPlayerId = gameState.playerId().equals("X") ? "O" : "X";

        return new ActiveGameState(false, null, nextPlayerId, gameStatePlaceMap);
    }

    private Map<Integer, String> getStateMap(List<GameState> gameStateList)
    {
        Map<Integer, String> filledStateMap = new HashMap<>();

        for (int i = 1; i <= 9; i++)
        {
            int finalI = i;

            Optional<GameState> gameState = gameStateList.stream().filter(s -> s.placeId() == finalI).findFirst();

            filledStateMap.put(i, gameState.isEmpty() ? "-" : gameState.get().playerId());
        }

        return filledStateMap;
    }

    private String checkGameEnd(List<GameState> gameStates)
    {
        List<List<Short>> winnerSets = List.of(
            List.of((short) 1, (short) 2, (short) 3),
            List.of((short) 4, (short) 5, (short) 6),
            List.of((short) 7, (short) 8, (short) 9),
            List.of((short) 1, (short) 4, (short) 7),
            List.of((short) 2, (short) 5, (short) 8),
            List.of((short) 3, (short) 6, (short) 9),
            List.of((short) 1, (short) 5, (short) 9),
            List.of((short) 3, (short) 5, (short) 7)
        );

        List<Short> gameStatesOfO = gameStates.stream()
            .filter(g -> g.playerId().equals("O"))
            .map(GameState::placeId)
            .collect(Collectors.toList());

        List<Short> gameStatesOfX = gameStates.stream()
            .filter(g -> g.playerId().equals("X"))
            .map(GameState::placeId)
            .collect(Collectors.toList());

        for (List<Short> winnerSet : winnerSets)
        {
            int foundInX = 0;
            int foundInO = 0;

            for (Short winnerItem : winnerSet)
            {
                if (gameStatesOfO.contains(winnerItem))
                {
                    foundInO++;
                }

                if (gameStatesOfX.contains(winnerItem))
                {
                    foundInX++;
                }

                if (foundInX >= 3) {
                    return  "X";
                }

                if (foundInO >= 3) {
                    return  "O";
                }
            }
        }

        return null;
    }
}
