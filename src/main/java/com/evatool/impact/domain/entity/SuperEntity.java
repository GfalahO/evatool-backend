package com.evatool.impact.domain.entity;

import com.evatool.impact.common.exception.PropertyViolationException;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@MappedSuperclass
public class SuperEntity {

    private static final Logger logger = LoggerFactory.getLogger(SuperEntity.class);

    protected SuperEntity() {

    }

    @Getter
    @NotNull
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

    private boolean idAlreadySet() {
        return this.id != null;
    }

}
