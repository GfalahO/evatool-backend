package com.evatool.impact.domain.entity;

import com.evatool.impact.common.exception.InvalidUuidException;
import com.evatool.impact.common.exception.PropertyViolationException;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
public class SuperEntity {

    private static final Logger logger = LoggerFactory.getLogger(SuperEntity.class);

    protected SuperEntity() {

    }

    @Getter
    @Id
    @GeneratedValue(generator = "SuperEntityUuidGenerator")
    @GenericGenerator(name = "SuperEntityUuidGenerator", strategy = "com.evatool.impact.domain.entity.SuperEntityUuidGenerator")
    @Column(name = "ID", updatable = false, nullable = false)
    protected UUID id;

    public void setId(UUID id) {
        logger.debug("Set id (UUID)");
        if (this.idAlreadySet()) {
            logger.error("Attempted to set existing id.");
            throw new PropertyViolationException("Existing id cannot be set.");
        }
        this.id = id;
    }

    public void setId(String id) {
        logger.debug("Set id (String)");
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
            UUID.fromString(id); // TODO [tzaika] Question: the result of UUID.fromString is not used. Is there a clean way of returning it and not make code ugly?
            return true;
        } catch (IllegalArgumentException ex) {
            logger.error("Exception: [{}]", ex.getMessage());
            return false;
        }
    }

    public static void probeExistingId(String id) {
        var entity = new SuperEntity();
        entity.setId(id);
    }

    public static void probeNonExistingId(String id) {
        if (id != null) {
            logger.error("Id must be null");
            throw new PropertyViolationException(String.format("A newly created '%s' must have null id.", Dimension.class.getSimpleName()));
        }
    }
}
