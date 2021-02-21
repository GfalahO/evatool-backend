package com.evatool.impact.domain.entity;

import com.evatool.impact.common.exception.PropertyViolationException;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "IMP_DIMENSION")
@Table(name = "IMP_DIMENSION")
public class Dimension extends SuperEntity {

    private static final Logger logger = LoggerFactory.getLogger(Dimension.class);

    public enum Type {
        SOCIAL,
        ECONOMIC
    }

    @Getter
    @Column(name = "NAME", nullable = false)
    private String name;

    @Getter
    @Column(name = "TYPE", nullable = false)
    private Type type;

    @Getter
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    public Dimension() {
        super();
        logger.debug("{} created", Dimension.class.getSimpleName());
    }

    public Dimension(String name, Type type, String description) {
        this();
        this.setName(name);
        this.setType(type);
        this.setDescription(description);
    }

    @Override
    public String toString() {
        return "Dimension{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dimension dimension = (Dimension) o;
        return Objects.equals(name, dimension.name) && type == dimension.type && Objects.equals(description, dimension.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, description);
    }

    public void setName(String name) {
        logger.debug("Set Name");
        if (name == null) {
            logger.error("Attempted to set name to null");
            throw new PropertyViolationException("Name cannot be null.");
        }
        this.name = name;
    }

    public void setType(Type type) {
        if (type == null) {
            logger.error("Attempted to set type to null");
            throw new PropertyViolationException("Type cannot be null.");
        }
        this.type = type;
    }

    public void setDescription(String description) {
        logger.debug("Set Description");
        if (description == null) {
            logger.error("Attempted to set description to null");
            throw new PropertyViolationException("Description cannot be null.");
        }
        this.description = description;
    }
}
