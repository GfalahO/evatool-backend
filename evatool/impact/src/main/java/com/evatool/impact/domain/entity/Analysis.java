package com.evatool.impact.domain.entity;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ANALYSIS")
@Table(name = "ANALYSIS")
public class Analysis extends SuperEntity {
    @Getter
    @OneToMany
    private List<Impact> impacts = new ArrayList<>();

    public Analysis() {

    }

    @Override
    public String toString() {
        return "Analysis{" +
                "impacts=" + impacts +
                ", id='" + id + '\'' +
                '}';
    }
}
