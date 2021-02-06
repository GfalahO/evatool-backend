package com.evatool.requirements.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
public class Requirement {

    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID id = UUID.randomUUID();
    private String titel;
    private String description;
    @ManyToMany
    private Collection<ScenarioVariants> variants = new ArrayList<>();

    /*    @OneToOne
    private Requirement_GR requirement_gr;*/

    public Requirement() {
    }

    public Requirement(String titel, String description) {
        this.titel = titel;
        this.description = description;
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

    public Collection<ScenarioVariants> getVariants() {
        return variants;
    }

    public void setVariants(Collection<ScenarioVariants> variants) {
        this.variants = variants;
    }

/*
    public Requirement_GR getRequirement_gr() {
        return requirement_gr;
    }

    public void setRequirement_gr(Requirement_GR requirement_gr) {
        this.requirement_gr = requirement_gr;
    }*/

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}

