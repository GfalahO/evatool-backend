package com.evatool.analysis.model;

import com.evatool.analysis.enums.StakeholderLevel;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.UUID;

/**
 * @author fobaidi
 * @author MHallweg
 */
@Entity
@Table(name = "ANA_STAKEHOLDER")
public class Stakeholder {

    @Id
    @Getter
    @Setter
    private UUID stakeholderId = UUID.randomUUID();

    /**
     * Name of the Stakeholder {@link String}
     */
    @Getter
    private String stakeholderName;

    /**
     * Priority of the Stakeholder {@link int}
     */
    @Getter
    private Integer priority;

    /**
     * The Level of an stakeholder
     */
    @Getter
    @Enumerated(EnumType.STRING)
    private StakeholderLevel stakeholderLevel;

    @Getter
    @Setter
    @OneToOne
    private AnalysisImpacts analysisImpacts;


    public Stakeholder(String stakeholderName, Integer priority, StakeholderLevel stakeholderLevel) {
        this.stakeholderName = stakeholderName;
        this.priority = priority;
        this.stakeholderLevel = stakeholderLevel;
    }

    public Stakeholder() {
    }

    public void setStakeholderLevel(StakeholderLevel stakeholderLevel) {
        if (stakeholderLevel == null){
            throw new IllegalArgumentException("Level name cannot be null.");
        }
        this.stakeholderLevel = stakeholderLevel;
    }

    public void setStakeholderName(String stakeholderName) {
        if (stakeholderName == null){
            throw new IllegalArgumentException("Stakeholder name cannot be null.");
        }
        this.stakeholderName = stakeholderName;
    }

    public void setPriority(Integer priority) {
        if (priority == null){
            throw new IllegalArgumentException("priority name cannot be null.");
        }
        this.priority = priority;
    }
}
