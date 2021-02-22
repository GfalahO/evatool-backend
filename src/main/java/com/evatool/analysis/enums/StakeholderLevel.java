package com.evatool.analysis.enums;

import com.sun.istack.NotNull;

/**
 * This Enum represents the Stakeholder Level
 *
 * @author fobaidi
 * @author MHallweg
 */
public enum StakeholderLevel {

    naturalPerson("natural person"),
    organization("organization"),
    society("society");

    private String stakeholderLevel;

    StakeholderLevel(String stakeholderLevel) {
        this.stakeholderLevel = stakeholderLevel;
    }

    @NotNull
    public String getStakeholderLevel() {
        return this.stakeholderLevel;
    }
}

