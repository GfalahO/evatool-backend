package com.evatool.impact.common.exception;

public class EntityNullException extends IllegalArgumentException {
    public static final String MESSAGE_FORMAT = "'%s' must not be null.";

    public EntityNullException(Class c) {
        super(String.format(this.MESSAGE_FORMAT, c.getSimpleName()));
    }
}
