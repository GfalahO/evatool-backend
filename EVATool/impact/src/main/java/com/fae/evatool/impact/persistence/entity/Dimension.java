package com.fae.evatool.impact.persistence.entity;

import com.sun.istack.NotNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Dimension {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    public Dimension(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Dimension[id=%s, name=%s, description=%s]", this.id, this.name, this.description);
    }

    public String getId() {
        return this.id;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null."); // More elegant solution with field reference in exception string?
        }
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null.");
        }
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
