
package com.evatool.requirements.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
public class Requirement {

    //TODO Proejktid hinterlegen
    @Id
    private UUID id = UUID.randomUUID();
    private String titel;
    private String description;
    @ManyToMany
    private Collection<RequirementsVariants> variants = new ArrayList<>();

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
        if (description == null) {
            throw new IllegalArgumentException("Title cannot be null.");
        }
        this.titel = titel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws IllegalArgumentException {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null.");
        }

        this.description = description;
    }

    public Collection<RequirementsVariants> getVariants() {
        return variants;
    }

    public void setVariants(Collection<RequirementsVariants> variants) {
        if (description == null) {
            throw new IllegalArgumentException("Variants cannot be null.");
        }
        this.variants = variants;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}

