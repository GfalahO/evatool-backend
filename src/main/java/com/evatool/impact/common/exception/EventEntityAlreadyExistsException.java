package com.evatool.impact.common.exception;

public class EventEntityAlreadyExistsException extends RuntimeException {
    public EventEntityAlreadyExistsException() {
        super("The entity transmitted in the event already exist.");
    }
}
