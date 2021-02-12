package com.evatool.requirements.dto;

import java.util.*;

public class RequirementDTO {

    private Map<UUID,String> impactTitles = new HashMap<>();
    private UUID rootEntityId;
    private String requirementTitle;
    private String requirementDescription;
    private Set dimensions = new HashSet();
    private String variantsTitle;
    private UUID variantsUUID;
    private Map<UUID,Integer> requirementImpactPoints = new HashMap<>();

    public RequirementDTO() {

    }

    public UUID getRootEntityId() {
        return rootEntityId;
    }

    public void setRootEntityId(UUID rootEntityId) {
        this.rootEntityId = rootEntityId;
    }

    public UUID getVariantsUUID() {
        return variantsUUID;
    }

    public void setVariantsUUID(UUID variantsUUID) {
        this.variantsUUID = variantsUUID;
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

    public Map<UUID,String> getImpactTitles() {
        return impactTitles;
    }

    public void setImpactTitles(Map<UUID,String> impactTitles) {
        this.impactTitles = impactTitles;
    }

    public Set getDimensions() {
        return dimensions;
    }

    public void setDimensions(Set dimensions) {
        this.dimensions = dimensions;
    }

    public String getVariantsTitle() {
        return variantsTitle;
    }

    public void setVariantsTitle(String variantsTitle) {
        this.variantsTitle = variantsTitle;
    }

    public Map<UUID, Integer> getRequirementImpactPoints() {
        return requirementImpactPoints;
    }

    public void setRequirementImpactPoints(Map<UUID, Integer> requirementImpactPoints) {
        this.requirementImpactPoints = requirementImpactPoints;
    }
}
