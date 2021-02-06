package com.evatool.impact.domain.entity;

import com.evatool.impact.common.exception.PropertyViolationException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "DIMENSION")
@Table(name = "DIMENSION")
public class Dimension extends SuperEntity {
    @Getter
    @Column(name = "NAME", nullable = false)
    private String name;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private DimensionType type;

    @Getter
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    public Dimension() {

    }

    public Dimension(String name, DimensionType type, String description) {
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

    public void setName(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new PropertyViolationException("Name cannot be null.");
        }
        this.name = name;
    }

    public void setDescription(String description) throws IllegalArgumentException {
        if (description == null) {
            throw new PropertyViolationException("Description cannot be null.");
        }
        this.description = description;
    }

    public static boolean isDimensionType(String value) {
        for (var e : DimensionType.values()) {
            if (value.equalsIgnoreCase(e.toString())) {
                return true;
            }
        }
        return false;
    }
}
