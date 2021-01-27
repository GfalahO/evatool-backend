package com.evatool.impact.persistence.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * A class representing the entity and object Dimension.
 */
@Entity(name = "DIMENSION")
@Table(name = "DIMENSION")
public class Dimension {
    /**
     * The id of the object.
     */
    @Getter
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;

    /**
     * The name of the object.
     */
    @Getter
    @NotNull
    @Column(name = "NAME", unique = true)
    private String name;

    /**
     * The description of the object.
     */
    @Getter
    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     *
     */
    public Dimension() {

    }

    /**
     * @param name:        The name of the object
     * @param description: The description of the object.
     */
    public Dimension(String name, String description) {
        this.setName(name);
        this.setDescription(description);
    }

    /**
     * @return a string describing the object.
     */
    @Override
    public String toString() {
        return String.format(
                "Dimension[id=%s, name=%s, description=%s]",
                this.id, this.name, this.description);
    }

    /**
     * @param name: The new name of the object
     * @throws IllegalArgumentException if argument is null.
     */
    public void setName(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null.");
        }
        this.name = name;
    }

    /**
     * @param description: The new description of the object
     * @throws IllegalArgumentException if argument is null.
     */
    public void setDescription(String description) throws IllegalArgumentException {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null.");
        }
        this.description = description;
    }
}
