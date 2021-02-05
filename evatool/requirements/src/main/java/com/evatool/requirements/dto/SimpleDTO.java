package com.evatool.requirements.dto;

import java.util.*;

public class SimpleDTO {

    private Map<String,String> impactTitles = new HashMap<>();
    private String requirementTitle;
    private String requirementDescription;
    private Map<String,String> dimensions = new HashMap<>();
    private Set<String> variantsTitle = new HashSet<>();
    private Set<String> stakeholderName = new HashSet<>();
    private List<Integer> requirementImpactPoints = new ArrayList<>();

    public SimpleDTO() {

    }

    public Map<String, String> getImpactTitles() {
        return impactTitles;
    }

    public void setImpactTitles(Map<String, String> impactTitles) {
        this.impactTitles = impactTitles;
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

    public Map<String, String> getDimensions() {
        return dimensions;
    }

    public void setDimensions(Map<String, String> dimensions) {
        this.dimensions = dimensions;
    }

    public Set<String> getVariantsTitle() {
        return variantsTitle;
    }

    public void setVariantsTitle(Set<String> variantsTitle) {
        this.variantsTitle = variantsTitle;
    }

    public Set<String> getStakeholderName() {
        return stakeholderName;
    }

    public void setStakeholderName(Set<String> stakeholderName) {
        this.stakeholderName = stakeholderName;
    }

    public List<Integer> getRequirementImpactPoints() {
        return requirementImpactPoints;
    }

    public void setRequirementImpactPoints(List<Integer> requirementImpactPoints) {
        this.requirementImpactPoints = requirementImpactPoints;
    }
}
