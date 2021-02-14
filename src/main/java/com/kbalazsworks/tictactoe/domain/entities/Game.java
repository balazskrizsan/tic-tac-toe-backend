package com.kbalazsworks.tictactoe.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record Game(
    @JsonProperty Long id,
    @JsonProperty String starterUserId,
    LocalDateTime createdAt
)
{
}
