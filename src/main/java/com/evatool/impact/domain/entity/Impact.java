package com.evatool.impact.domain.entity;

import com.evatool.impact.common.exception.PropertyViolationException;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Table(name = "IMP_IMPACT")
@Entity(name = "IMP_IMPACT")
public class Impact extends SuperEntity {

    private static final Logger logger = LoggerFactory.getLogger(Impact.class);

    @Column(name = "VALUE", nullable = false)
    @Getter
    private double value;

    @Column(name = "DESCRIPTION", nullable = false)
    @Getter
    private String description;

    @Getter
    @ManyToOne(optional = false)
    private Dimension dimension;

    @Getter
    @ManyToOne(optional = false)
    private ImpactStakeholder stakeholder;

    public Impact() {
        super();
        logger.debug("{} created", Impact.class.getSimpleName());
    }

    public Impact(double value, String description, Dimension dimension, ImpactStakeholder stakeholder) {
        this();
        this.setValue(value);
        this.setDescription(description);
        this.setDimension(dimension);
        this.setStakeholder(stakeholder);
    }

    @Override
    public String toString() {
        return "Impact{" + "value=" + value + ", description='" + description + '\'' + ", dimension=" + dimension
                + ", stakeholder=" + stakeholder + ", id='" + id + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Impact impact = (Impact) o;
        return Double.compare(impact.value, value) == 0 && Objects.equals(description, impact.description)
                && Objects.equals(dimension, impact.dimension) && Objects.equals(stakeholder, impact.stakeholder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, description, dimension, stakeholder);
    }

    public void setValue(double value) {
        logger.debug("Set Value");
        if (value < -1.0 || value > 1.0) {
            logger.error("Attempted to set value outside its valid range");
            throw new PropertyViolationException("Value must be in range [-1, 1]");
        }
        this.value = value;
    }

    public void setDescription(String description) {
        logger.debug("Set Description");
        if (description == null) {
            logger.error("Attempted to set description to null");
            throw new PropertyViolationException("Description cannot be null.");
        }
        this.description = description;
    }

    public void setDimension(Dimension dimension) {
        logger.debug("Set Dimension");
        if (dimension == null) {
            logger.error("Attempted to set dimension description to null");
            throw new PropertyViolationException("Dimension cannot be null.");
        }
        this.dimension = dimension;
    }

    public void setStakeholder(ImpactStakeholder stakeholder) {
        logger.debug("Set Stakeholder");
        if (stakeholder == null) {
            logger.error("Attempted to set stakeholder to null");
            throw new PropertyViolationException("Stakeholder cannot be null.");
        }
        this.stakeholder = stakeholder;
    }
}

// TODO [hbuhl & ztaika] Relation constraints
//  Throw error or allow setting relation to null?
//  How to deal with deleted stakeholder event in Impact?

// TODO [tzaika] Use new wireframe in impact domain model wiki
// TODO [tzaika] Finish impact domain model decision
// TODO [tzaika] h2 console on remote server
