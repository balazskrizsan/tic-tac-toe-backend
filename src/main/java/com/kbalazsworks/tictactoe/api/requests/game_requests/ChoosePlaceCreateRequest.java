package com.kbalazsworks.tictactoe.api.requests.game_requests;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public record ChoosePlaceCreateRequest(
    @Pattern(regexp = "^[X|Y]$")
    String playerId,

    @Min(1)
    @Max(9)
    short placeId
)
{
}
