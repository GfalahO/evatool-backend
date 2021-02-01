
package com.evatool.requirements.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Stakeholder {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id = UUID.randomUUID();
    private String titel;

    public Stakeholder() {
    }

    public Stakeholder(String titel) {
        this.titel = titel;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
