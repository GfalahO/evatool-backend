package com.evatool.impact.common.exception;

public class EventEntityDoesNotExistException extends RuntimeException {
    public EventEntityDoesNotExistException(String entity) {
        super(String.format("The '%s' transmitted in the event does not exist.", entity));
    }
}
