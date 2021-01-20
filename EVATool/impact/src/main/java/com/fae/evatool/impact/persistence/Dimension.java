package com.fae.evatool.impact.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Dimension {
    @Id
    @Column(name = "UUID", nullable = false)
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    public Dimension(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Dimension[id=%s, name=%s, description=%s]", this.id, this.name, this.description);
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }
}
