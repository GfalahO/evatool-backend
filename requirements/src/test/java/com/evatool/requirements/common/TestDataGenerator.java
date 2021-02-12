package com.evatool.requirements.common;

import com.evatool.requirements.dto.RequirementDTO;
import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.entity.RequirementGR;
import com.evatool.requirements.entity.RequirementsImpacts;
import com.evatool.requirements.entity.RequirementsVariants;

public class TestDataGenerator {

    public static Requirement getRequirement() {
        return new Requirement("requirementTitle","descriptionTitle");
    }

    public static RequirementsImpacts getRequirementsImpacts() {
        return new RequirementsImpacts("Title","Description",10, RequirementsImpacts.Dimension.PRIVAT);
    }

    public static RequirementsVariants getRequirementsVariants() {
        return new RequirementsVariants("Title","Description");
    }

    public static RequirementGR getRequirementGR(Requirement requirement, RequirementsImpacts requirementsImpacts) {

        return new RequirementGR(requirementsImpacts, requirement, 3);
    }

    public static RequirementGR getRequirementGR() {

        return new RequirementGR(getRequirementsImpacts(), getRequirement(), 3);
    }

    public static RequirementDTO getRequirementDTO() {
        var requirementDTO = new RequirementDTO();

        requirementDTO.setRequirementTitle("Title");
        requirementDTO.setRequirementDescription("Description");

        return requirementDTO;
    }
}
