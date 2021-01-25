package com.fae.evatool.impact.persistence.entity;

import com.sun.istack.NotNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Stakeholder {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @NotNull
    private String name; // Required for UI.

    public Stakeholder(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        //return String.format("Stakeholder[id=%s, #impacts=&d]", this.id, this.impacts.size());
        return String.format("Stakeholder[id=%s]", this.id);
    }

    public String getId() {
        return this.id;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null.");
        }
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
