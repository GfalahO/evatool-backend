package com.evatool.analysis.enums;



/**
 * This Enum represents the Stakeholder Level
 *
 * @author fobaidi
 * @author MHallweg
 */
public enum StakeholderLevel {

    NATURALPERSON("natural person"),
    ORGANIZATION("organization"),
    SOCIETY("society");

    private String stakeholder;

    StakeholderLevel(String stakeholder) {
        this.stakeholder = stakeholder;
    }


    public String getStakeholder() {
        return this.stakeholder;
    }
}

