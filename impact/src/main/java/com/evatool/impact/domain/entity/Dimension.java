package com.evatool.impact.domain.entity;

import com.evatool.impact.common.exception.PropertyViolationException;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity(name = "DIMENSION")
@Table(name = "DIMENSION")
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
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private Type type;

    @Getter
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    public Dimension() {

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

    public void setName(String name) {
        if (name == null) {
            logger.error("Attempted to set name to null.");
            throw new PropertyViolationException("Name cannot be null.");
        }
        this.name = name;
    }

    public void setDescription(String description) {
        if (description == null) {
            logger.error("Attempted to set description to null.");
            throw new PropertyViolationException("Description cannot be null.");
        }
        this.description = description;
    }

    public static boolean isValidType(String value) {
        if (value == null) {
            return false;
        }
        for (var e : Type.values()) {
            if (value.equalsIgnoreCase(e.toString())) {
                return true;
            }
        }
        return false;
    }
}
