package com.fae.evatool.impact.persistence.entity;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Analysis {
    @Getter
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Getter
    @OneToMany
    private List<Impact> impacts = new ArrayList<>();

    public Analysis() {
        
    }

    @Override
    public String toString() {
        //return String.format("Project[id=%s, #impacts=%d]", this.id, this.impacts.size());
        return String.format("Project[id=%s]", this.id);
    }

    public void addImpact(Impact impact) {
        this.impacts.add(impact);
    }
}
