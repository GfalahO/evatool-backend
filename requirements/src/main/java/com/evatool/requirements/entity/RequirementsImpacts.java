
package com.evatool.requirements.entity;



import javax.persistence.*;
import java.util.UUID;

@Entity
public class RequirementsImpacts {

    @Id
    private UUID id = UUID.randomUUID();
    private String title;
    private String description;
    private int value;

    private Dimension dimension;

    public RequirementsImpacts() {
    }

    public RequirementsImpacts(String title, String description, int value, Dimension dimension) {
        this.title = title;
        this.description = description;
        this.value = value;
        this.dimension=dimension;
    }

    public enum Dimension{
        SAFETY,PRIVAT
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException("Title cannot be null.");
        }
        this.title = title;
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
        if (value < -1 || value > 1) {
            throw new IllegalArgumentException("Value must be in range [-1, 1]");
        }
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
