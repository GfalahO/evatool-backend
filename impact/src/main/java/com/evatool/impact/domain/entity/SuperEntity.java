package com.evatool.impact.domain.entity;

import com.evatool.impact.common.exception.InvalidUuidException;
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
    @Column(name = "ID", updatable = false, nullable = false)
    protected UUID id;

    // Allowed transitions: null -> null and null -> valid.
    public void setId(UUID id) {
        if (this.idAlreadySet()) {
            throw new PropertyViolationException("Existing id cannot be set.");
        }
        this.id = id;
    }

    public void setId(String id) {
        if (!isValidUuid(id)) {
            throw new InvalidUuidException(id);
        }
        this.setId(UUID.fromString(id));
    }

    private boolean idAlreadySet() {
        return this.id != null;
    }

    public static boolean isValidUuid(String id) {
        if (id == null) {
            return false;
        }
        try {
            UUID.fromString(id); // TODO [tzaika] the result of UUID.fromString is not used. Is there a clean way of returning it and not make code ugly?
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
