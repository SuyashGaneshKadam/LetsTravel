package com.SK.LetsTravel.CustomExceptions;

public class TransportNotFoundException extends Exception{
    public TransportNotFoundException(String message) {
        super(message);
    }
}
