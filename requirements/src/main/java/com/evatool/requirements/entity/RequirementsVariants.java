package com.evatool.requirements.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class RequirementsVariants {

    @Id
    private UUID id = UUID.randomUUID();
    private String title;
    private String description;


    public RequirementsVariants() {
    }

    public RequirementsVariants(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String titel) {
        if (titel == null) {
            throw new IllegalArgumentException("Title cannot be null.");
        }
        this.title = titel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null.");
        }
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
