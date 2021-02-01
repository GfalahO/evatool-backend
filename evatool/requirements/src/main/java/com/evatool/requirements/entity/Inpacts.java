
package com.evatool.requirements.entity;



import javax.persistence.*;
import java.util.UUID;

@Entity
public class Inpacts {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id = UUID.randomUUID();
    private String titel;
    private String description;
    private int value;
    /*@OneToOne
    private Requirement_GR requirement_gr;*/
    @ManyToOne
    private Stakeholder stakeholder;

    private Dimension dimension;

    public Inpacts() {
    }

    public Inpacts(String titel, String description, int value, Dimension dimension, Stakeholder stakeholder) {
        this.titel = titel;
        this.description = description;
        this.value = value;
        this.dimension=dimension;
        this.stakeholder=stakeholder;
    }

    public enum Dimension{
        SAFETY,PRIVAT
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

    public Stakeholder getStakeholder() {
        return stakeholder;
    }

    public void setStakeholder(Stakeholder stakeholder) {
        this.stakeholder = stakeholder;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
