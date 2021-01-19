package com.FAE.EVATool.Projects.Model;

import lombok.Getter;
import javax.persistence.*;

/**
 * @author fobaidi
 * @author MHallweg
 */
@Entity
@Table (name = "Project_Stakeholder")
public class Stakeholder{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long stakeholderId;

    /**
     * Name of the Stakeholder @link{String}
     */
    @Getter
    private String stakeholderName;

    /**
     * Priority of the Stakeholder @link{int}
     */
    @Getter
    private int priority;

    // TODO Requirements? Goals and Risks?
}