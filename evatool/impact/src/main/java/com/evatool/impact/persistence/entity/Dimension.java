package com.evatool.impact.persistence.entity;

import com.evatool.impact.exception.PropertyViolationException;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "DIMENSION")
@Table(name = "DIMENSION")
public class Dimension extends SuperEntity {
    @Getter
    @Column(name = "NAME", nullable = false)
    private String name;

    @Getter
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    public Dimension() {

    }

    public Dimension(String name, String description) {
        this.setName(name);
        this.setDescription(description);
    }

    @Override
    public String toString() {
        return String.format(
                "Dimension[id=%s, name=%s, description=%s]",
                this.id, this.name, this.description);
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
}
