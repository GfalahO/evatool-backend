package com.evatool.impact.domain.entity;

import lombok.Getter;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "REQUIREMENT")
@Table(name = "REQUIREMENT")
public class Requirement extends SuperEntity {
    @Getter
    @ManyToMany
    private List<Impact> impacts = new ArrayList<>();

    public Requirement() {

    }

    @Override
    public String toString() {
        return "Requirement{" +
                "impacts=" + impacts +
                ", id='" + id + '\'' +
                '}';
    }
}
