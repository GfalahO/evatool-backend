package com.evatool.impact.common.exception;

import java.util.ResourceBundle;

public class EntityNotFoundException extends Exception {
    public static final String MESSAGE_FORMAT = ResourceBundle.getBundle("impact").getString("EntityNotFoundExceptionMessageFormat");

    public EntityNotFoundException(Class c, String id) {
        super(String.format(MESSAGE_FORMAT, c.getSimpleName(), id));
    }
}
