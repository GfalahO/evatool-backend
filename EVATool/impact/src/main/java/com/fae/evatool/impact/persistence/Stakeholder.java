package com.fae.evatool.impact.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Stakeholder {
    @Id
    @Column(name = "UUID", nullable = false)
    @GeneratedValue(generator = "system-uuid")
    private String id;

    // Attributes for UI?

    // We do not create this entity ourselves.

    @Override
    public String toString() {
        //return String.format("Stakeholder[id=%s, #impacts=&d]", this.id, this.impacts.size());
        return String.format("Stakeholder[id=%s]", this.id);
    }
}
