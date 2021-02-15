package com.evatool.requirements.common;

import com.evatool.requirements.dto.RequirementDTO;
import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.entity.RequirementPoint;
import com.evatool.requirements.entity.RequirementsImpact;
import com.evatool.requirements.entity.RequirementsVariant;

public class TestDataGenerator {

    public static Requirement getRequirement() {
        return new Requirement("requirementTitle","descriptionTitle");
    }

    public static RequirementsImpact getRequirementsImpacts() {
        return new RequirementsImpact("Title","Description",10, RequirementsImpact.Dimension.PRIVAT);
    }

    public static RequirementsVariant getRequirementsVariants() {
        return new RequirementsVariant("Title","Description");
    }

    public static RequirementPoint getRequirementGR(Requirement requirement, RequirementsImpact requirementsImpact) {

        return new RequirementPoint(requirementsImpact, requirement, 3);
    }

    public static RequirementPoint getRequirementGR() {

        return new RequirementPoint(getRequirementsImpacts(), getRequirement(), 3);
    }

    public static RequirementDTO getRequirementDTO() {
        var requirementDTO = new RequirementDTO();

        requirementDTO.setRequirementTitle("Title");
        requirementDTO.setRequirementDescription("Description");

        return requirementDTO;
    }
}
