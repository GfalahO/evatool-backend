package com.evatool.impact.persistence.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "DIMENSION")
@Table(name = "DIMENSION")
public class Dimension {
    @Getter
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;

    @Getter
    @NotNull
    @Column(name = "NAME", unique = true)
    private String name;

    @Getter
    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    public Dimension() {

    }

    public Dimension(String name, String description) {
        this.setName(name);
        this.setDescription(description);
    }

    @Override
    public String toString() {
        return String.format(
                "Dimension[id=%s, name=%s, description=%s]",
                this.id, this.name, this.description);
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null.");
        }
        this.name = name;
    }

    public void setDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null.");
        }
        this.description = description;
    }
}
