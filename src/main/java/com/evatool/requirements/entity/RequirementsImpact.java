
package com.evatool.requirements.entity;



import com.google.gson.Gson;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "REQ_RequirementImpact")
public class RequirementsImpact {

    @Id
    private UUID id = UUID.randomUUID();
    private String title;
    private String description;
    private int value;

    private Dimension dimension;

    public RequirementsImpact() {
    }

   public static RequirementsImpact fromJson(String json){
        return  new Gson().fromJson(json, RequirementsImpact.class);

    }

    public RequirementsImpact(String title, String description, int value, Dimension dimension) {
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
