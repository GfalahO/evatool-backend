
package com.evatool.requirements.entity;


import javax.persistence.*;
import java.util.UUID;

@Entity
public class Requirement_GR {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id = UUID.randomUUID();
    @ManyToOne
    private Inpacts inpacts;
    @ManyToOne
    private Requirement requirement;
    private int points;

    public Requirement_GR() {
    }

    public Requirement_GR(Inpacts inpacts, Requirement requirement, int points) {
        this.inpacts = inpacts;
        this.requirement = requirement;
        this.points = points;
    }

    public Inpacts getInpacts() {
        return inpacts;
    }

    public void setInpacts(Inpacts inpacts) {
        this.inpacts = inpacts;
    }

    public Requirement getRequirement() {
        return requirement;
    }

    public void setRequirement(Requirement requirement) {
        this.requirement = requirement;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
