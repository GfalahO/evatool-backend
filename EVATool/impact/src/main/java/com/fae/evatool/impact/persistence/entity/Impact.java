package com.fae.evatool.impact.persistence.entity;

import com.sun.istack.NotNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Impact {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @NotNull
    private double value;

    @NotNull
    private String reason;

    @ManyToOne
    @NotNull
    private Dimension dimension;

    @ManyToMany
    private List<Requirement> requirements = new ArrayList<>();

    //@ManyToMany
    //private Set<Scenario> scenarios;

    @OneToMany
    private List<Stakeholder> stakeholders = new ArrayList<>();

    public Impact() {

    }

    public Impact(double value, String reason, Dimension dimension) {
        this.setValue(value);
        this.setReason(reason);
        this.setDimension(dimension);
    }

    @Override
    public String toString() {
        // Add # requirements, scenarios and Stakeholder...
        return String.format("Impact[id=%s, value=%d, reason=%s, dimension=%s]", this.id, this.value, this.reason, this.dimension.toString());
    }

    public String getId() {
        return this.id;
    }

    public void setValue(double value) {
        if (value < -1.0 || value > 1.0) {
            throw new IllegalArgumentException("Value must be in range [-1, 1]");
        }
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    public void setReason(String reason) {
        if (reason == null) {
            throw new IllegalArgumentException("Reason cannot be null.");
        }
        this.reason = reason;
    }

    public String getReason() {
        return this.reason;
    }

    public void setDimension(Dimension dimension) {
        if (dimension == null) {
            throw new IllegalArgumentException("Dimension cannot be null.");
        }
        this.dimension = dimension;
    }

    public Dimension getDimension() {
        return this.dimension;
    }

    public void addRequirement(Requirement requirement) {
        this.requirements.add(requirement);
    }

    public List<Requirement> getRequirements() {
        return this.requirements;
    }

    //public Set<Scenario> getScenarios() {
    //    return this.scenarios;
    //}

    public List<Stakeholder> getStakeholders() {
        return this.stakeholders;
    }
}
