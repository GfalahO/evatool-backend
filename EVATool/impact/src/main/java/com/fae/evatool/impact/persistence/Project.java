package com.fae.evatool.impact.persistence;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Project {
    @Id
    @Column(name = "UUID", nullable = false)
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @OneToMany
    private Set<Impact> impacts;

    // We do not create this entity ourselves.

    @Override
    public String toString() {
        //return String.format("Project[id=%s, #impacts=%d]", this.id, this.impacts.size());
        return String.format("Project[id=%s]", this.id);
    }

    public Set<Impact> getImpacts() {
        return this.impacts;
    }
}
