package com.evatool.impact.common.exception;

public class DataConcurrencyException extends RuntimeException {
    public DataConcurrencyException(String message) {
        super(message);
    }
}
