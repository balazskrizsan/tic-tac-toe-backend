package com.kbalazsworks.tictactoe.domain.exceptions;

public class RepositoryNotFoundException extends HttpException
{
    public RepositoryNotFoundException()
    {
        super();
    }

    public RepositoryNotFoundException(String message)
    {
        super(message);
    }
}
