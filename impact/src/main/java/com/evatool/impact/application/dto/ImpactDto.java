package com.evatool.impact.application.dto;

import lombok.Getter;
import lombok.Setter;

// TODO [future feature] using HATEOAS
// extends RepresentationModel<ImpactDto>
public class ImpactDto {
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private StakeholderDto stakeholder; // Is using nested Dtos ok? Why not use stakeholderId and stakeholderName?

    @Getter
    @Setter
    private DimensionDto dimension;

    @Getter
    @Setter
    private double value;

    @Getter
    @Setter
    private String description;

    @Override
    public String toString() {
        return "ImpactDto{" +
                "id='" + id + '\'' +
                ", stakeholder=" + stakeholder +
                ", dimension=" + dimension +
                ", value=" + value +
                ", description='" + description + '\'' +
                '}';
    }
}
