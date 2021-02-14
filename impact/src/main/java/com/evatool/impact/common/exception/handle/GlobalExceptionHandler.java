package com.evatool.impact.common.exception.handle;

import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.common.exception.InvalidUuidException;
import com.evatool.impact.common.exception.PropertyViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(EntityNotFoundException exception, WebRequest webRequest) {
        logger.error("{} handled. Returning HttpStatus NOT_FOUND (404)", exception.getClass().getSimpleName());
        var errorMessage = new ErrorMessage(exception.getMessage(), getUri(webRequest));
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PropertyViolationException.class)
    public ResponseEntity<ErrorMessage> handlePropertyViolationException(PropertyViolationException exception, WebRequest webRequest) {
        logger.error("{} handled. Returning HttpStatus BAD_REQUEST (400)", exception.getClass().getSimpleName());
        var errorMessage = new ErrorMessage(exception.getMessage(), getUri(webRequest));
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidUuidException.class)
    public ResponseEntity<ErrorMessage> handleInvalidUuidException(InvalidUuidException exception, WebRequest webRequest) {
        logger.error("{} handled. Returning HttpStatus BAD_REQUEST (400)", exception.getClass().getSimpleName());
        var errorMessage = new ErrorMessage(exception.getMessage(), getUri(webRequest));
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    private String getUri(WebRequest webRequest) {
        return ((ServletWebRequest) webRequest).getRequest().getRequestURI();
    }
}
