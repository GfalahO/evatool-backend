package com.evatool.impact.persistence.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "IMPACT")
@Table(name = "IMPACT")
public class Impact {
    @Getter
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;

    @Getter
    @NotNull
    @Column(name = "VALUE")
    private double value;

    @Getter
    @NotNull
    @Column(name = "REASON")
    private String reason;

    @Getter
    @ManyToOne
    @NotNull
    private Dimension dimension;

    @Getter
    @ManyToOne
    private Stakeholder stakeholder; // Add to .toString, add to constructor and implement setStakeholder method + tests for it.

    @Getter
    @ManyToMany
    private List<Requirement> requirements = new ArrayList<>();

    public Impact() {

    }

    public Impact(double value, String reason, Dimension dimension, Stakeholder stakeholder) {
        this.setValue(value);
        this.setReason(reason);
        this.setDimension(dimension);
        this.setStakeholder(stakeholder);
    }

    @Override
    public String toString() {
        return String.format(
                "Impact[id=%s, value=%f, reason=%s, dimension=%s, stakeholder=%s, #requirements=%d]",
                this.id, this.value, this.reason, this.dimension.toString(), stakeholder.toString(), requirements.size());
    }

    public void setValue(double value) {
        if (value < -1.0 || value > 1.0) {
            throw new IllegalArgumentException("Value must be in range [-1, 1]");
        }
        this.value = value;
    }

    public void setReason(String reason) {
        if (reason == null) {
            throw new IllegalArgumentException("Reason cannot be null.");
        }
        this.reason = reason;
    }

    public void setDimension(Dimension dimension) {
        if (dimension == null) {
            throw new IllegalArgumentException("Dimension cannot be null.");
        }
        this.dimension = dimension;
    }

    public void setStakeholder(Stakeholder stakeholder) {
        if (stakeholder == null) {
            throw new IllegalArgumentException("Stakeholder cannot be null.");
        }
        this.stakeholder = stakeholder;
    }
}
