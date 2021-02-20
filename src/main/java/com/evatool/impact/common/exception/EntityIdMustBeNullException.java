package com.evatool.impact.common.exception;

public class EntityIdMustBeNullException extends RuntimeException {
    public EntityIdMustBeNullException() {
        super("A newly created entity cannot have an id.");
    }
}
