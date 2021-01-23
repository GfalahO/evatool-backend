package com.fae.evatool.impact.persistence;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Impact {
    @Id
    @Column(name = "UUID", nullable = false)
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column(name = "value", nullable = false)
    private int value;

    @Column(name = "reason", nullable = false)
    private String reason;

    @ManyToOne
    private ImpactDimension dimension;

    @ManyToMany
    private Set<Requirement> requirements;

    @ManyToMany
    private Set<Scenario> scenarios;

    @OneToMany
    private Set<Stakeholder> stakeholders;

    public Impact(int value, String reason) { // Add dimension as mandatory parameter? -> #1
        this.value = value;
        this.reason = reason;
    }

    @Override
    public String toString() {
        // Add # requirements, scenarios and Stakeholder...
        return String.format("Impact[id=%s, value=%d, reason=%s, dimension=%s]", this.id, this.value, this.reason, this.dimension); // #1 add .toString to this.dimension
    }

    public String getId() {
        return this.id;
    }

    public int getValue() {
        return this.value;
    }

    public String getReason() {
        return this.reason;
    }

    public ImpactDimension getDimension() {
        return this.dimension;
    }

    public Set<Requirement> getRequirements() {
        return this.requirements;
    }

    public Set<Scenario> getScenarios() {
        return this.scenarios;
    }

    public Set<Stakeholder> getStakeholders(){
        return this.stakeholders;
    }
}
