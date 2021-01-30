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

    public void setId(String id) throws IllegalArgumentException {
        if (idExists()) {
            throw new IllegalArgumentException("Existing id cannot be set.");
        } else if (id != null && !idIsValid(id)) {
            throw new IllegalArgumentException("Id must be a valid UUID.");
        }
        this.id = id;
    }
    
    private boolean idExists() {
        return this.id != null;
    }

    private boolean idIsValid(String id) {
        try {
            UUID.fromString(id);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
