package com.evatool.analysis.error.exceptions;

public class EventEntityDoesNotExistException extends RuntimeException {
    public EventEntityDoesNotExistException() {
        super("The entity transmitted in the event does not exist.");
    }
}