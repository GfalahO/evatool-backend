package com.FAE.EVATool.Analysis.Enum;

import com.sun.istack.NotNull;

/**
 * This Enum represents the Stakeholder Rules
 * @author fobaidi
 * @author MHallweg
 */
public enum StakeholderRules {


     naturalPerson("natural person"),
     organization ("organization"),
     society ("society");

     private String stakeholderrule;

    StakeholderRules(String stakeholderrule) {
        this.stakeholderrule = stakeholderrule;
    }

    @NotNull
    public String getStakeholderrule() {
        return this.stakeholderrule;
    }
}

