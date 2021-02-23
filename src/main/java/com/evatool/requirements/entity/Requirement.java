
package com.evatool.requirements.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "REQ_Requirement")
public class Requirement {

    @Id
    private UUID id = UUID.randomUUID();
    private UUID projectId;
    private String title;
    private String description;
    @ManyToMany
    private Collection<RequirementsVariant> variants = new ArrayList<>();

    public Requirement() {
    }

    public Requirement(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws IllegalArgumentException {
        if (title == null) {
            throw new IllegalArgumentException("Title cannot be null.");
        }
        this.title = title;
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

    public Collection<RequirementsVariant> getVariants() {
        return variants;
    }

    public void setVariants(Collection<RequirementsVariant> variants) throws IllegalArgumentException {
        if (variants == null) {
            throw new IllegalArgumentException("Variants cannot be null.");
        }
        this.variants = variants;
    }

    public UUID getId() {
        return id;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) throws IllegalArgumentException {
        if (projectId == null) {
            throw new IllegalArgumentException("ProjectId cannot be null.");
        }
        this.projectId = projectId;
    }
}

