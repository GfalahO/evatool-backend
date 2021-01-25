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

    private double value;

    private String reason;

    @ManyToOne
    private Dimension dimension;

    @ManyToMany
    private List<Requirement> requirements = new ArrayList<>();

    //@ManyToMany
    //private Set<Scenario> scenarios;

    @OneToMany
    private List<Stakeholder> stakeholders = new ArrayList<>();

    public Impact(){

    }

    public Impact(double value, String reason, Dimension dimension) { // Add dimension as mandatory parameter? -> #1
        //this.value = value;
        setValue(value);
        this.reason = reason;
        this.dimension = dimension;
    }

    @Override
    public String toString() {
        // Add # requirements, scenarios and Stakeholder...
        return String.format("Impact[id=%s, value=%d, reason=%s, dimension=%s]", this.id, this.value, this.reason, this.dimension); // #1 add .toString to this.dimension
    }

    public String getId() {
        return this.id;
    }

    public void setValue(double value) {
        if (value < -1.0 || value > 1.0) {
            throw new IllegalArgumentException("Value must be in range [-1, 1]");
        } else {
            this.value = value;
        }
    }

    public double getValue() {
        return this.value;
    }

    public String getReason() {
        return this.reason;
    }

    public Dimension getDimension() {
        return this.dimension;
    }

    public void addRequirement(Requirement requirement){
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
