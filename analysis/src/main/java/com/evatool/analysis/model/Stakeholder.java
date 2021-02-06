package com.evatool.analysis.model;

import com.evatool.analysis.enums.StakeholderRules;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author fobaidi
 * @author MHallweg
 */
@Entity
public class Stakeholder {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",
            strategy = "uuid")
    @Getter
    private String stakeholderId;

    /**
     * Name of the Stakeholder {@link String}
     */
    @Getter
    @Setter
    private String stakeholderName;

    /**
     * Priority of the Stakeholder {@link int}
     */
    @Getter
    @Setter
    private Integer priority;

    /**
     * The role of the stakeholder
     */
    @Getter
    @Setter
    private StakeholderRules stakeholderRule;

}
