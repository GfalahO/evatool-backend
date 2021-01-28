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
    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @Getter
    @Column(name = "DESCRIPTION", nullable = false)
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

    public void setName(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null.");
        }
        this.name = name;
    }

    public void setDescription(String description) throws IllegalArgumentException {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null.");
        }
        this.description = description;
    }
}
