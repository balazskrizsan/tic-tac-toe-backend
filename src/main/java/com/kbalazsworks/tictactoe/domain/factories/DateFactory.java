package com.kbalazsworks.tictactoe.domain.factories;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateFactory
{
    public Date create()
    {
        return new Date();
    }
}
