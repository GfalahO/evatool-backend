package com.evatool.impact.common.exception;

import java.util.ResourceBundle;

public class EntityNullException extends IllegalArgumentException {
    public static final String MESSAGE_FORMAT = ResourceBundle.getBundle("impact").getString("EntityNullExceptionMessageFormat");

    public EntityNullException(Class c) {
        super(String.format(MESSAGE_FORMAT, c.getSimpleName()));
    }
}
