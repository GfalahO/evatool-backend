package com.FAE.EVATool.Analysis.Model;

import com.FAE.EVATool.Analysis.Enum.StakeholderRules;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author fobaidi
 * @author MHallweg
 */
@Entity
public class Stakeholder{

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",
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