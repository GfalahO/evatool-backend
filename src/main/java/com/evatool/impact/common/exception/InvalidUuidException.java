package com.evatool.impact.common.exception;

public class InvalidUuidException extends RuntimeException {
    public InvalidUuidException(String id) {
        super(String.format("Invalid UUID (%s).", id));
    }
}
