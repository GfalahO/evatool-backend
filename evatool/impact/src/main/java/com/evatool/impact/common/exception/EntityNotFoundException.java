package com.evatool.impact.common.exception;

public class EntityNotFoundException extends Exception {
    public static final String format = "'%s' with id '%s' was not found.";

    public EntityNotFoundException(Class c, String id) {
        super(String.format(format, c.getSimpleName(), id));
    }
}
