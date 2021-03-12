package com.evatool.analysis.enums;

import com.sun.istack.NotNull;

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

    @NotNull
    public String getStakeholder() {
        return this.stakeholder;
    }
}

