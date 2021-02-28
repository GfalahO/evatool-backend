package com.evatool.requirements.error.exceptions;

public class EventEntityAlreadyExistsException extends RuntimeException {
    public EventEntityAlreadyExistsException() {
        super("The entity transmitted in the event already exist.");
    }
}
