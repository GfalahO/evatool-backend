package com.evatool.impact.exception;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(Class c, String id) {
        super(String.format("'%s' with id '%s' not found.", c.getSimpleName(), id));
    }
}
