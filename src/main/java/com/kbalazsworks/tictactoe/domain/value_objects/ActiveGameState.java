package com.kbalazsworks.tictactoe.domain.value_objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record ActiveGameState(
    @JsonProperty boolean finished,
    @JsonProperty String winner,
    @JsonProperty String nextPlayerId,
    @JsonProperty Map<Integer, String> currentGameStatePlace
)
{
}
