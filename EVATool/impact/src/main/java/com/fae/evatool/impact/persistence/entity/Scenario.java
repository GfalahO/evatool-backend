package com.fae.evatool.impact.persistence.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

// Really required? "Only in Dashboard". Remove entity?
@Entity
public class Scenario {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;


    // Relationship defines this attribute?
    private boolean applicability;

    @ManyToMany
    private Set<Impact> impacts;

    // Attributes for UI?

    // We do not create this entity ourselves.

    @Override
    public String toString() {
        //return String.format("Scenario[id=%s, applicability=%b, #impacts=%d]", this.id, this.applicability, this.impacts.size());
        return String.format("Scenario[id=%s, applicability=%b]", this.id, this.applicability);
    }

    public boolean getApplicability() {
        return this.applicability;
    }

    public Set<Impact> getImpacts() {
        return this.impacts;
    }
}
