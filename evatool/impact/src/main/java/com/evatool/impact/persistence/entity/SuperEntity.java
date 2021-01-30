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
        if (id == null && this.id != null) {
            throw new IllegalArgumentException("Id cannot be null.");
        } else if (idIsAlreadySet()) {
            throw new IllegalArgumentException("Cannot set existing id.");
        } else if (id != null && !idIsValidUuid(id)) {
            throw new IllegalArgumentException("Id must be a valid UUID.");
        }
        this.id = id;
    }

    private boolean idIsAlreadySet() {
        return this.id != null;
    }

    private boolean idIsValidUuid(String id) {
        try {
            UUID.fromString(id);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

}
