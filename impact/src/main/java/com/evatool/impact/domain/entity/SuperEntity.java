package com.evatool.impact.domain.entity;

import com.evatool.impact.common.exception.PropertyViolationException;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public class SuperEntity {
    @Getter
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID", nullable = false)
    protected String id;

    // Allowed transitions: null -> null and null -> valid.
    public void setId(String id) throws IllegalArgumentException {
        if (this.idAlreadySet()) {
            throw new PropertyViolationException("Existing id cannot be set.");
        } else if (id != null && !this.idIsValid(id)) {
            throw new PropertyViolationException("Id must be a valid UUID.");
        }
        this.id = id;
    }

    private boolean idAlreadySet() {
        return this.id != null;
    }

    private boolean idIsValid(String id) {
        try {
            var uuid = UUID.fromString(id);
            return uuid.toString().equals(id);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
