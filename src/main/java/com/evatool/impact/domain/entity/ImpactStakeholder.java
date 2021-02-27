package com.evatool.impact.domain.entity;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity(name = "IMP_STAKEHOLDER")
@Table(name = "IMP_STAKEHOLDER")
public class ImpactStakeholder extends SuperEntity {

    private static final Logger logger = LoggerFactory.getLogger(ImpactStakeholder.class);

    @Getter
    @Column(name = "NAME", nullable = false)
    private String name;

    public ImpactStakeholder() {
        super();
        logger.debug("{} created", ImpactStakeholder.class.getSimpleName());
    }

    public ImpactStakeholder(String name) {
        this();
        this.setName(name);
    }

    @Override
    public String toString() {
        return "Stakeholder{" +
                "id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        var that = (ImpactStakeholder) o;
        return super.equals(that)
                && Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.name);
    }

    public void setName(String name) {
        logger.debug("Set Name");
        if (name == null) {
            logger.error("Attempted to set name to null");
            throw new IllegalArgumentException("Name cannot be null.");
        }
        this.name = name;
    }
}
