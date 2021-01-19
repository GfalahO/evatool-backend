package com.FAE.EVATool.Projects.Model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

/**
 * @author fobaidi
 * @author MHallweg
 */
@Entity
@Getter
@Setter
@Table(name = "project_analysis")
public class Analysis   {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long analysisId;

    /**
     * Name of the Analysis @link{String}
     */
    private String analysisName;

    /**
     * The description of the Analysis @link{String}
     */
    private String description;

    /**
     * The User of the Analysis @link{List<User>/>}
     * Referenz zum User
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> analysisUserId;

    /**
     * The Stakeholder of the Analysis @link{List<Stakeholder>}
     * Referenz zum Stakholder
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Stakeholder> analysisStakeholderId;

}