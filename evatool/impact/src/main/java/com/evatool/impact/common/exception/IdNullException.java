package com.evatool.impact.common.exception;

public class IdNullException extends Exception {
    public static final String MESSAGE_FORMAT = "Illegal attempt to retrieve '%s' with null id";

    public IdNullException(Class c) {
        super(String.format(this.MESSAGE_FORMAT, c.getSimpleName()));
    }
}
