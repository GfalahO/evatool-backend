package com.evatool.impact.common.exception;

public class EventEntityAlreadyExistsException extends RuntimeException {

    public EventEntityAlreadyExistsException(String entity) {
        super(String.format("The '%s' transmitted in the event already exist.", entity));
    }
}
