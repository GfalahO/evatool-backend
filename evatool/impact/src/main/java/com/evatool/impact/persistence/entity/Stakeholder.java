package com.evatool.impact.persistence.entity;

import com.evatool.impact.common.dto.StakeholderDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "STAKEHOLDER")
@Table(name = "STAKEHOLDER")
public class Stakeholder {
    @Getter
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID", updatable = false, nullable = false)
    private String id;

    @Getter
    @Column(name = "NAME", nullable = false)
    private String name;

    public Stakeholder() {

    }

    public Stakeholder(String name) {
        this();
        this.setName(name);
    }

    private Stakeholder(String id, String name) {
        this(name);
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format(
                "Stakeholder[id=%s, name=%s]",
                this.id, this.name);
    }

    public void setId(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        if (this.id != null) {
            throw new IllegalArgumentException("Cannot set existing id to null.");
        }
        //try {
        UUID.fromString(id);
        //} catch (IllegalArgumentException exception) {
        //    throw new IllegalArgumentException("Id must be a valid UUID.");
        //}
        this.id = id;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null.");
        }
        this.name = name;
    }

    public StakeholderDto toDto() {
        var stakeholderDto = new StakeholderDto();

        stakeholderDto.setId(this.getId());
        stakeholderDto.setName(this.getName());

        return stakeholderDto;
    }

    public static Stakeholder fromDto(StakeholderDto stakeholderDto) {
        var stakeholder = new Stakeholder();

        stakeholder.id = stakeholderDto.getId();
        stakeholder.setName(stakeholderDto.getName());

        return stakeholder;
    }
}
