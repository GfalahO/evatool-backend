package com.evatool.impact.domain.entity;

import com.evatool.impact.common.exception.PropertyViolationException;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "IMPACT_STAKEHOLDER")
@Table(name = "IMPACT_STAKEHOLDER")
public class ImpactStakeholder extends SuperEntity {
    @Getter
    @Column(name = "NAME", nullable = false)
    private String name;

    public ImpactStakeholder() {

    }

    public ImpactStakeholder(String name) {
        this();
        this.setName(name);
    }

    private ImpactStakeholder(String id, String name) {
        this(name);
        this.id = id;
    }

    @Override
    public String toString() {
        return "Stakeholder{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public void setName(String name) {
        if (name == null) {
            throw new PropertyViolationException("Name cannot be null.");
        }
        this.name = name;
    }
}
