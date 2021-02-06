package com.evatool.impact.domain.entity;

import com.evatool.impact.common.exception.PropertyViolationException;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "IMPACT")
@Table(name = "IMPACT")
public class Impact extends SuperEntity {
    @Getter
    @Column(name = "VALUE", nullable = false)
    private double value;

    @Getter
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Getter
    @ManyToOne
    private Dimension dimension;

    @Getter
    //@ManyToOne(optional = false, fetch = FetchType.EAGER) // Do this? Change tests if yes...
    @ManyToOne
    private ImpactStakeholder stakeholder;

    public Impact() {

    }

    public Impact(double value, String description, Dimension dimension, ImpactStakeholder stakeholder) {
        this.setValue(value);
        this.setDescription(description);
        this.setDimension(dimension);
        this.setStakeholder(stakeholder);
    }

    @Override
    public String toString() {
        return "Impact{" +
                "value=" + value +
                ", description='" + description + '\'' +
                ", dimension=" + dimension +
                ", stakeholder=" + stakeholder +
                ", id='" + id + '\'' +
                '}';
    }

    public void setValue(double value) {
        if (value < -1.0 || value > 1.0) {
            throw new PropertyViolationException("Value must be in range [-1, 1]");
        }
        this.value = value;
    }

    public void setDescription(String description) {
        if (description == null) {
            throw new PropertyViolationException("Description cannot be null.");
        }
        this.description = description;
    }

    public void setDimension(Dimension dimension) {
        if (dimension == null) {
            throw new PropertyViolationException("Dimension cannot be null.");
        }
        this.dimension = dimension;
    }

    public void setStakeholder(ImpactStakeholder stakeholder) {
        if (stakeholder == null) {
            throw new PropertyViolationException("Stakeholder cannot be null.");
        }
        this.stakeholder = stakeholder;
    }
}
