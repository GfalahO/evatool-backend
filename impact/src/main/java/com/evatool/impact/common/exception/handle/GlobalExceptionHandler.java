package com.evatool.impact.common.exception.handle;

import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.common.exception.PropertyViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(EntityNotFoundException exception, WebRequest webRequest) {
        var errorMessage = new ErrorMessage(exception.getMessage());
        var responseEntity = new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @ExceptionHandler(PropertyViolationException.class)
    public ResponseEntity<ErrorMessage> handlePropertyViolationException(PropertyViolationException exception, WebRequest webRequest) {
        var errorMessage = new ErrorMessage(exception.getMessage());
        var responseEntity = new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    // TODO [tzaika] This causes some tests to fail. Fix or omit completely?
    // This happens by default so its kinda useless.
    //@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception exception, WebRequest webRequest) {
        var errorMessage = new ErrorMessage(exception.getMessage());
        var responseEntity = new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }
}
