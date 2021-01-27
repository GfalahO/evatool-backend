package com.fae.evatool.impact.persistence.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Impact {
    @Getter
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Getter
    @NotNull
    private double value;

    @Getter
    @NotNull
    private String reason;

    @Getter
    @ManyToOne
    @NotNull
    private Dimension dimension;

    @Getter
    @ManyToMany
    private List<Requirement> requirements = new ArrayList<>();

    @Getter
    @ManyToOne
    private Stakeholder stakeholders;

    public Impact() {

    }

    public Impact(double value, String reason, Dimension dimension) {
        this.setValue(value);
        this.setReason(reason);
        this.setDimension(dimension);
    }

    @Override
    public String toString() {
        return String.format(
                "Impact[id=%s, value=%f, reason=%s, dimension=%s, #requirements=%d, #stakeholders=%d]",
                this.id, this.value, this.reason, this.dimension.toString(), requirements.size(), stakeholders.toString());
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
}
