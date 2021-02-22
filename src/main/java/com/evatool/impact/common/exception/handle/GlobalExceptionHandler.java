package com.evatool.impact.common.exception.handle;

import com.evatool.impact.common.exception.EntityIdMustBeNullException;
import com.evatool.impact.common.exception.EntityIdRequiredException;
import com.evatool.impact.common.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
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
        return createResponseEntity(exception, webRequest, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityIdRequiredException.class)
    public ResponseEntity<ErrorMessage> handleEntityIdRequiredException(EntityIdRequiredException exception, WebRequest webRequest) {
        return createResponseEntity(exception, webRequest, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(EntityIdMustBeNullException.class)
    public ResponseEntity<ErrorMessage> handleEntityIdMustBeNullException(EntityIdMustBeNullException exception, WebRequest webRequest) {
        return createResponseEntity(exception, webRequest, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(DataIntegrityViolationException.class) // TODO This has to be removed
    public ResponseEntity<ErrorMessage> handleDataIntegrityViolationException(DataIntegrityViolationException exception, WebRequest webRequest) {
        return createResponseEntity(exception, webRequest, HttpStatus.CONFLICT);
    }

    private ResponseEntity<ErrorMessage> createResponseEntity(Exception exception, WebRequest webRequest, HttpStatus httpStatus) {
        logger.info("{} handled. Returning HttpStatus {}", exception.getClass().getSimpleName(), httpStatus);
        var errorMessage = new ErrorMessage(exception, getUri(webRequest), httpStatus);
        return new ResponseEntity<>(errorMessage, httpStatus);
    }

    private String getUri(WebRequest webRequest) {
        return ((ServletWebRequest) webRequest).getRequest().getRequestURI();
    }
}
