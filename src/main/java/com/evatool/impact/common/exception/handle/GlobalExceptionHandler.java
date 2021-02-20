package com.evatool.impact.common.exception.handle;

import com.evatool.impact.common.exception.EntityIdMustBeNullException;
import com.evatool.impact.common.exception.EntityIdRequiredException;
import com.evatool.impact.common.exception.EntityNotFoundException;
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
        logger.info("{} handled. Returning HttpStatus NOT_FOUND (404)", exception.getClass().getSimpleName());
        var errorMessage = new ErrorMessage(exception, exception.getMessage(), getUri(webRequest), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityIdRequiredException.class)
    public ResponseEntity<ErrorMessage> handleEntityIdRequiredException(EntityIdRequiredException exception, WebRequest webRequest) {
        logger.info("{} handled. Returning HttpStatus UNPROCESSABLE_ENTITY (422)", exception.getClass().getSimpleName());
        var errorMessage = new ErrorMessage(exception, exception.getMessage(), getUri(webRequest), HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(errorMessage, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(EntityIdMustBeNullException.class)
    public ResponseEntity<ErrorMessage> handleEntityIdMustBeNullException(EntityIdMustBeNullException exception, WebRequest webRequest) {
        logger.info("{} handled. Returning HttpStatus UNPROCESSABLE_ENTITY (422)", exception.getClass().getSimpleName());
        var errorMessage = new ErrorMessage(exception, exception.getMessage(), getUri(webRequest), HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(errorMessage, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private String getUri(WebRequest webRequest) {
        return ((ServletWebRequest) webRequest).getRequest().getRequestURI();
    }
}
