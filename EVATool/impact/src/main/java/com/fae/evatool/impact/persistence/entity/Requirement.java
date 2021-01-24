package com.fae.evatool.impact.persistence.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Requirement {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @ManyToMany
    private Set<Impact> impacts;

    @Override
    public String toString() {
        //return String.format("Requirement[id=%s, #impacts=%d]", this.id, this.impacts.size());
        return String.format("Requirement[id=%s]", this.id);
    }

    public String getId(){
        return this.id;
    }

    public Set<Impact> getImpacts() {
        return this.impacts;
    }
}
