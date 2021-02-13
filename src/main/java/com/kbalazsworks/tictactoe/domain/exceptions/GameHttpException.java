package com.kbalazsworks.tictactoe.domain.exceptions;

public class GameHttpException extends HttpException
{
    public GameHttpException(String message)
    {
        super(message);
    }
}
