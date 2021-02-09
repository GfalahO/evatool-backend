package com.evatool.requirements.common;

import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.entity.RequirementGR;
import com.evatool.requirements.entity.RequirementsImpacts;
import com.evatool.requirements.entity.RequirementsVariants;

public class TestDataGenerator {

    public static Requirement getRequirement() {
        return new Requirement("requirementTitle","descriptionTitle");
    }

    public static RequirementsImpacts getImpact() {
        return new RequirementsImpacts("Title","Description",10, RequirementsImpacts.Dimension.PRIVAT);
    }

    public static RequirementsVariants getVariant() {
        return new RequirementsVariants("Title","Description");
    }

    public static RequirementGR getRequirementGR() {

        Requirement requirement = getRequirement();
        RequirementsImpacts requirementsImpacts = getImpact();
        return new RequirementGR(requirementsImpacts, requirement, 3);
    }
}
