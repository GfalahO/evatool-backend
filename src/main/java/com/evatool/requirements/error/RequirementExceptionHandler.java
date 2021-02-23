package com.evatool.requirements.error;

import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.common.exception.handle.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RequirementExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(RequirementExceptionHandler.class);


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(EntityNotFoundException exception, WebRequest webRequest) {
        logger.info("{} handled. Returning HttpStatus NOT_FOUND (404)", exception.getClass().getSimpleName());
        var errorMessage = new ErrorMessage(exception, exception.getMessage(), getUri(webRequest), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    private String getUri(WebRequest webRequest) {
        return ((ServletWebRequest) webRequest).getRequest().getRequestURI();
    }
}
