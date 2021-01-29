package com.evatool.impact.persistence.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "STAKEHOLDER")
@Table(name = "STAKEHOLDER")
public class Stakeholder {
    @Getter
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;

    @Getter
    @Column(name = "NAME", nullable = false)
    private String name;

    public Stakeholder() {

    }

    public Stakeholder(String name) {
        this.setName(name);
    }

    @Override
    public String toString() {
        return String.format(
                "Stakeholder[id=%s, name=%s]",
                this.id, this.name);
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null.");
        }
        this.name = name;
    }
}
