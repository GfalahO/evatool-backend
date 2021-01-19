package com.FAE.EVATool.Projects.Model;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

/**
 * This class represents a User
 * @author fobaidi
 * @author MHallweg
 */
@Entity
@Table (name = "Project_User")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    /**
     * Name of the user @link{String}
     */
    @Getter
    @Setter
    @Column
    private String name;

    /**
     * The userPassword @link{String}
     */
    @Getter
    @Setter
    private String userPassword;

    /**
     * The Email @link{String}
     */
    @Getter
    @Setter
    private String email;

}