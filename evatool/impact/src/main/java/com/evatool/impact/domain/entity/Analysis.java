package com.evatool.impact.domain.entity;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ANALYSIS")
@Table(name = "ANALYSIS")
public class Analysis {
    @Getter
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;

    @Getter
    @OneToMany
    private List<Impact> impacts = new ArrayList<>();

    public Analysis() {

    }

    @Override
    public String toString() {
        return String.format(
                "Project[id=%s, #impacts=%d]",
                this.id, this.impacts.size());
    }
}
