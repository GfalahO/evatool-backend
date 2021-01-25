package com.fae.evatool.impact.persistence.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Stakeholder {
    @Getter
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Getter
    @NotNull
    private String name; // Required for UI.

    public Stakeholder() {

    }

    public Stakeholder(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "Stakeholder[id=%s]",
                this.id);
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null.");
        }
        this.name = name;
    }
}
