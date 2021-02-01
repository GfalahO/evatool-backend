package com.evatool.impact.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IdNullException extends Exception{
    public IdNullException(String message) {
        super(message);
    }
}
