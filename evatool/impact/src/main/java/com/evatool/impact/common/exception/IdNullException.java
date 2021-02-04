package com.evatool.impact.common.exception;

import java.util.ResourceBundle;

public class IdNullException extends Exception {
    public static final String MESSAGE_FORMAT = ResourceBundle.getBundle("impact").getString("IdNullExceptionMessageFormat");

    public IdNullException(Class c) {
        super(String.format(MESSAGE_FORMAT, c.getSimpleName()));
    }
}
