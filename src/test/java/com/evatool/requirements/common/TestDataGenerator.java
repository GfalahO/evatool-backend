package com.evatool.requirements.common;

import com.evatool.requirements.dto.RequirementDTO;
import com.evatool.requirements.entity.*;

import java.util.ArrayList;
import java.util.Collection;

public class TestDataGenerator {

    public static RequirementDimension getRequirementDimension(){
        return new RequirementDimension("DimensionTitle");
    }

    public static Requirement getRequirement(RequirementsAnalysis requirementsAnalysis, Collection<RequirementsVariant> variants) {
        return new Requirement("requirementTitle","descriptionTitle",requirementsAnalysis,variants);
    }

    public static RequirementsImpact getRequirementsImpacts(RequirementDimension requirementDimension) {
        return new RequirementsImpact("Title","Description",10, requirementDimension);
    }

    public static RequirementsVariant getRequirementsVariant() {
        return new RequirementsVariant("Title","Description");
    }

    public static RequirementsAnalysis getRequirementsAnalysis()
    {
        return new RequirementsAnalysis();
    }

    public static Collection<RequirementsVariant> getRequirementsVariants(){
        Collection<RequirementsVariant> requirementsVariants = new ArrayList<>();
        requirementsVariants.add(new RequirementsVariant("Title1","Description1"));
        requirementsVariants.add(new RequirementsVariant("Title2","Description2"));
        requirementsVariants.add(new RequirementsVariant("Title3","Description3"));

        return requirementsVariants;
    }

    public static RequirementPoint getRequirementGR(Requirement requirement, RequirementsImpact requirementsImpact) {

        return new RequirementPoint(requirementsImpact, requirement, 3);
    }

    public static RequirementPoint getRequirementGR(RequirementDimension requirementDimension,RequirementsAnalysis requirementsAnalysis, Collection<RequirementsVariant> variants) {

        return new RequirementPoint(getRequirementsImpacts(requirementDimension), getRequirement(requirementsAnalysis,variants), 3);
    }

    public static RequirementDTO getRequirementDTO() {
        var requirementDTO = new RequirementDTO();

        requirementDTO.setRequirementTitle("Title");
        requirementDTO.setRequirementDescription("Description");

        return requirementDTO;
    }
}
