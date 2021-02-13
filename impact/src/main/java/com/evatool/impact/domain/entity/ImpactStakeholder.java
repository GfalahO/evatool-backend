package com.evatool.impact.domain.entity;

import com.evatool.impact.common.exception.PropertyViolationException;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "IMPACT_STAKEHOLDER")
@Table(name = "IMPACT_STAKEHOLDER")
public class ImpactStakeholder extends SuperEntity {

    private static final Logger logger = LoggerFactory.getLogger(ImpactStakeholder.class);

    @Getter
    @Column(name = "NAME", nullable = false)
    private String name;

    public ImpactStakeholder() {
        super();
    }

    public ImpactStakeholder(String name) {
        this();
        this.setName(name);
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
            logger.error("Attempted to set name to null.");
            throw new PropertyViolationException("Name cannot be null.");
        }
        this.name = name;
    }
}
