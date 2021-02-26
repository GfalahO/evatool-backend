package com.evatool.requirements.error.exceptions;

import com.evatool.requirements.entity.Requirement;

import java.util.UUID;

public class EntityNotFoundException extends RuntimeException {
    public static final String MESSAGE_FORMAT = "'%s' with id '%s' was not found.";

    private Requirement requirement;
    private boolean rollback = false;

    public EntityNotFoundException(Class<?> c, UUID id) {
        super(String.format(MESSAGE_FORMAT, c.getSimpleName(), id.toString()));
    }

    public EntityNotFoundException(Class<?> c, UUID id,boolean rollback, Requirement requirement) {
        this(c,id);
        this.rollback=rollback;
        this.requirement=requirement;
    }

    public Requirement getRequirement() {
        return requirement;
    }

    public boolean isRollback() {
        return rollback;
    }
}
