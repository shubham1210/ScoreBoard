package com.io.scoreboard.exception;

/**
 * when we have limited player left and we still try to pull more players
 */
public class NoPlayerException extends Exception{
    public NoPlayerException(String message) {
        super(message);
    }
}
