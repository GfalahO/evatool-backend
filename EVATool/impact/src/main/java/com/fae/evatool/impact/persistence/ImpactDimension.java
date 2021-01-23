package com.fae.evatool.impact.persistence;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ImpactDimension {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String name;

    private String description;

    public ImpactDimension(String name, String description) {
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
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }
}
