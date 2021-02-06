package com.evatool.requirements.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class RequirementGR {

    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID id = UUID.randomUUID();
    @ManyToOne
    private RequirementsImpact requirementsImpact;
    @ManyToOne
    private Requirement requirement;
    private int points;

    public RequirementGR() {
    }

    public RequirementGR(RequirementsImpact requirementsImpact, Requirement requirement, int points) {
        this.requirementsImpact = requirementsImpact;
        this.requirement = requirement;
        this.points = points;
    }

    public RequirementsImpact getRequirementsImpact() {
        return requirementsImpact;
    }

    public void setRequirementsImpact(RequirementsImpact requirementsImpact) {
        this.requirementsImpact = requirementsImpact;
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
