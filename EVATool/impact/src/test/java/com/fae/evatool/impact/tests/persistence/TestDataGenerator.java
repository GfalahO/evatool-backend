package com.fae.evatool.impact.tests.persistence;

import com.fae.evatool.impact.persistence.entity.*;

public class TestDataGenerator {

    public static Dimension getDimension() {
        return new Dimension("safety", "...");
    }

    public static Stakeholder getStakeholder(){
        return new Stakeholder("person");
    }

    public static Requirement getRequirement(){
        return new Requirement();
    }

    public static Impact getImpact(){
        return new Impact(0.0, "", getDimension());
    }

    public static Analysis getAnalysis(){
        return new Analysis();
    }
}
