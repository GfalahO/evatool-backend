package com.evatool.impact.common.exception;

import java.util.UUID;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Class<?> c, UUID id) {
        super(String.format("'%s' with id '%s' was not found.", c.getSimpleName(), id.toString()));
    }
}
