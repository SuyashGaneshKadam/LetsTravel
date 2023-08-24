package com.SK.LetsTravel.CustomExceptions;

public class RouteNotFoundException extends Exception{
    public RouteNotFoundException(String message) {
        super(message);
    }
}
