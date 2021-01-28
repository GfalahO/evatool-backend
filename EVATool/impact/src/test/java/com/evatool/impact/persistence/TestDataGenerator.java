package com.evatool.impact.persistence;

import com.evatool.impact.persistence.entity.*;

public class TestDataGenerator {

    public static Dimension getDimension() {
        return new Dimension("dimension", "dimension");
    }

    public static Stakeholder getStakeholder() {
        return new Stakeholder("person");
    }

    public static Requirement getRequirement() {
        return new Requirement();
    }

    public static Impact getImpact() {
        return new Impact(0.0, "impact", getDimension(), getStakeholder());
    }

    public static Analysis getAnalysis() {
        return new Analysis();
    }
}
