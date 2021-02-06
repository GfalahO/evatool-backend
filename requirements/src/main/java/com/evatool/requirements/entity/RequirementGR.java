
package com.evatool.requirements.entity;


import javax.persistence.*;
import java.util.UUID;

@Entity
public class RequirementGR {

    @Id
    private UUID id = UUID.randomUUID();
    @ManyToOne
    private RequirementsImpacts requirementsImpacts;
    @ManyToOne
    private Requirement requirement;
    private int points;

    public RequirementGR() {
    }

    public RequirementGR(RequirementsImpacts requirementsImpacts, Requirement requirement, int points) {
        this.requirementsImpacts = requirementsImpacts;
        this.requirement = requirement;
        this.points = points;
    }

    public RequirementsImpacts getRequirementsImpacts() {
        return requirementsImpacts;
    }

    public void setRequirementsImpacts(RequirementsImpacts requirementsImpacts) {
        this.requirementsImpacts = requirementsImpacts;
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
