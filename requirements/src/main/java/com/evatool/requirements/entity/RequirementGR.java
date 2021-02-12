
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
        if (requirementsImpacts == null) {
            throw new IllegalArgumentException("RequirementsImpacts cannot be null.");
        }

        this.requirementsImpacts = requirementsImpacts;
    }

    public Requirement getRequirement() {
        return requirement;
    }

    public void setRequirement(Requirement requirement) {
        if (requirement == null) {
            throw new IllegalArgumentException("Requirement cannot be null.");
        }
        this.requirement = requirement;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        if (points < -1 || points > 1) {
            throw new IllegalArgumentException("Value must be in range [-1, 1]");
        }

        this.points = points;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
