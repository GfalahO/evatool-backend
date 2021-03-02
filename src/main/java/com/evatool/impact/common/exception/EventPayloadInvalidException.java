package com.evatool.impact.common.exception;

public class EventPayloadInvalidException extends RuntimeException {
    public EventPayloadInvalidException(String json, Exception cause) {
        super(String.format("Invalid payload received: %s", json), cause);
    }
}
