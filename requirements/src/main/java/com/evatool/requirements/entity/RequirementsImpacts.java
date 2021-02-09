
package com.evatool.requirements.entity;



import javax.persistence.*;
import java.util.UUID;

@Entity
public class RequirementsImpacts {

    @Id
    private UUID id = UUID.randomUUID();
    private String titel;
    private String description;
    private int value;

    private Dimension dimension;

    public RequirementsImpacts() {
    }

    public RequirementsImpacts(String titel, String description, int value, Dimension dimension) {
        this.titel = titel;
        this.description = description;
        this.value = value;
        this.dimension=dimension;
    }

    public enum Dimension{
        SAFETY,PRIVAT
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null.");
        }
        this.titel = titel;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
