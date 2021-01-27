package com.evatool.impact.persistence.entity;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Requirement {
    @Getter
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
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
