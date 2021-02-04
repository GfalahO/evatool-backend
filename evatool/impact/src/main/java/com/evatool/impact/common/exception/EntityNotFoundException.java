package com.evatool.impact.common.exception;

public class EntityNotFoundException extends Exception {
    public static final String MESSAGE_FORMAT = "'%s' with id '%s' was not found.";

    public EntityNotFoundException(Class c, String id) {
        super(String.format(this.MESSAGE_FORMAT, c.getSimpleName(), id));
    }
}
