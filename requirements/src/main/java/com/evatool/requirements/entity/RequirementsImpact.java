package com.evatool.requirements.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

// renamed because the entity is owned by another module and they have already used up the name 'Impact'
@Entity
public class RequirementsImpact {

    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID id = UUID.randomUUID();
    private String titel;
    private String description;
    private int value;

    /* @OneToOne
    private Requirement_GR requirement_gr; */
    @ManyToOne
    private RequirementsStakeholder requirementsStakeholder;

    private Dimension dimension;

    public RequirementsImpact() {
    }

    public RequirementsImpact(String titel, String description, int value, Dimension dimension, RequirementsStakeholder requirementsStakeholder) {
        this.titel = titel;
        this.description = description;
        this.value = value;
        this.dimension = dimension;
        this.requirementsStakeholder = requirementsStakeholder;
    }

    public enum Dimension {
        SAFETY, PRIVAT
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    /*
    public Requirement_GR getRequirement_gr() {
        return requirement_gr;
    }

    public void setRequirement_gr(Requirement_GR requirement_gr) {
        this.requirement_gr = requirement_gr;
    }*/

    public RequirementsStakeholder getStakeholder() {
        return requirementsStakeholder;
    }

    public void setStakeholder(RequirementsStakeholder requirementsStakeholder) {
        this.requirementsStakeholder = requirementsStakeholder;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
