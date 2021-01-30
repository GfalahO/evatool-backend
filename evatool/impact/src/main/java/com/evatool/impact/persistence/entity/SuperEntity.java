package com.evatool.impact.persistence.entity;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
public class SuperEntity {
    @Getter
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID", nullable = false)
    protected String id;

    public void setId(String id) {
        if (validIdSetToNull(id)) {
            throw new IllegalArgumentException("Existing id cannot be set to null.");
        } else if (idIsAlreadySet()) {
            throw new IllegalArgumentException("Existing id cannot be set.");
        } else if (!idIsValid(id)) {
            throw new IllegalArgumentException("Id must be a valid UUID.");
        }
        this.id = id;
    }

    private boolean validIdSetToNull(String id) {
        return id == null && this.id != null;
    }

    private boolean idIsAlreadySet() {
        return this.id != null;
    }

    private boolean idIsValid(String id) {
        if (id == null) {
            return true;
        }
        try {
            UUID.fromString(id);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

}
