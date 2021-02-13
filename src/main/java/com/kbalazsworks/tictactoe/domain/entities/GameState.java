package com.kbalazsworks.tictactoe.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record GameState(
    @JsonProperty Long id,
    @JsonProperty long gameId,
    @JsonProperty short placeId,
    @JsonProperty String playerId,
    LocalDateTime createdAt
)
{
}
