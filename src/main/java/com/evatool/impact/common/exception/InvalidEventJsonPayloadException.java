package com.evatool.impact.common.exception;

public class InvalidEventJsonPayloadException extends RuntimeException {
    public InvalidEventJsonPayloadException(String json, Exception cause) {
        super(String.format("Invalid json received: %s", json), cause);
    }
}
