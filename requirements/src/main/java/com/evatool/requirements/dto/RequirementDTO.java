package com.evatool.requirements.dto;

import java.util.*;

public class RequirementDTO {

    private Map<String,UUID> impactTitles = new HashMap<>();
    private String requirementTitle;
    private String requirementDescription;
    private Set dimensions = new HashSet();
    private Map<String,UUID> variantsTitle = new HashMap<>();
    private Map<String,Integer> requirementImpactPoints = new HashMap<>();

    public RequirementDTO() {

    }

    public String getRequirementTitle() {
        return requirementTitle;
    }

    public void setRequirementTitle(String requirementTitle) {
        this.requirementTitle = requirementTitle;
    }

    public String getRequirementDescription() {
        return requirementDescription;
    }

    public void setRequirementDescription(String requirementDescription) {
        this.requirementDescription = requirementDescription;
    }

    public Map<String, UUID> getImpactTitles() {
        return impactTitles;
    }

    public void setImpactTitles(Map<String, UUID> impactTitles) {
        this.impactTitles = impactTitles;
    }

    public Set getDimensions() {
        return dimensions;
    }

    public void setDimensions(Set dimensions) {
        this.dimensions = dimensions;
    }

    public Map<String, UUID> getVariantsTitle() {
        return variantsTitle;
    }

    public void setVariantsTitle(Map<String, UUID> variantsTitle) {
        this.variantsTitle = variantsTitle;
    }

    public Map<String, Integer> getRequirementImpactPoints() {
        return requirementImpactPoints;
    }

    public void setRequirementImpactPoints(Map<String, Integer> requirementImpactPoints) {
        this.requirementImpactPoints = requirementImpactPoints;
    }
}
