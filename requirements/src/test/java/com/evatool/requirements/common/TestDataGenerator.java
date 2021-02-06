package com.evatool.requirements.common;

import com.evatool.requirements.entity.Requirement;

public class TestDataGenerator {

    public static Requirement getRequirement() {
        return new Requirement("requirementTitle","descriptionTitle");
    }

}
