package com.evatool.impact.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PropertyViolationException extends IllegalArgumentException {
    public PropertyViolationException(String message) {
        super(message);
    }
}
