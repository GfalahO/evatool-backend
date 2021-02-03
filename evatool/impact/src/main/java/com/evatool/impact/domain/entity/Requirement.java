package com.evatool.impact.domain.entity;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "REQUIREMENT")
@Table(name = "REQUIREMENT")
public class Requirement {
    @Getter
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;

    @Getter
    @ManyToMany
    private List<Impact> impacts = new ArrayList<>();

    public Requirement() {

    }

    @Override
    public String toString() {
        return String.format(
                "Requirement[id=%s, #impacts=%d]",
                this.id, this.impacts.size());
    }
}
