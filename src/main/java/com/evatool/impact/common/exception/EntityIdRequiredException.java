package com.evatool.impact.common.exception;

public class EntityIdRequiredException extends RuntimeException {
    public EntityIdRequiredException() {
        super("The entity must have an id for this operation.");
    }
}
