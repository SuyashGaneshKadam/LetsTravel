package com.SK.LetsTravel.CustomExceptions;

public class SeatsNotAvailableException extends Exception{
    public SeatsNotAvailableException(String message) {
        super(message);
    }
}
