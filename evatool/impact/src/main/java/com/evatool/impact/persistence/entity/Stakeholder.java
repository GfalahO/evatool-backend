package com.evatool.impact.persistence.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "STAKEHOLDER")
@Table(name = "STAKEHOLDER")
public class Stakeholder extends SuperEntity {
    @Getter
    @Column(name = "NAME", nullable = false)
    private String name;

    public Stakeholder() {

    }

    public Stakeholder(String name) {
        this();
        this.setName(name);
    }

    private Stakeholder(String id, String name) {
        this(name);
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format(
                "Stakeholder[id=%s, name=%s]",
                this.id, this.name);
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null.");
        }
        this.name = name;
    }
}
