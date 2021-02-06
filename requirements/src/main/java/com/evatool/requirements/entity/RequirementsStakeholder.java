package com.evatool.requirements.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

// renamed because the entity is owned by another module and they have already used up the name 'Stakeholder'
@Entity
public class RequirementsStakeholder {

    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID id = UUID.randomUUID();
    private String titel;

    public RequirementsStakeholder() {
    }

    public RequirementsStakeholder(String titel) {
        this.titel = titel;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
