package com.evatool.impact.domain.entity;

import com.evatool.impact.common.exception.InvalidUuidException;
import com.evatool.impact.common.exception.PropertyViolationException;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public class SuperEntity {

    private static final Logger logger = LoggerFactory.getLogger(SuperEntity.class);

    @Getter
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID", updatable = false, nullable = false)
    protected UUID id;

    // Allowed transitions: null -> null and null -> valid.
    public void setId(UUID id) {
        if (this.idAlreadySet()) {
            logger.error("Attempted to set existing id.");
            throw new PropertyViolationException("Existing id cannot be set.");
        }
        this.id = id;
    }

    public void setId(String id) {
        if (this.idAlreadySet()) {
            logger.error("Attempted to set existing id.");
            throw new PropertyViolationException("Existing id cannot be set.");
        }
        if (!isValidUuid(id)) {
            logger.error("Attempted to set invalid id.");
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
            UUID.fromString(id);
            return true;
        } catch (IllegalArgumentException ex) {
            logger.error(ex.getMessage(), ex);
            return false;
        }
    }
}
