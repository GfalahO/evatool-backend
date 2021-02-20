package com.evatool.impact.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class ImpactDto {
    @Getter
    @Setter
    private UUID id;

    @Getter
    @Setter
    private ImpactStakeholderDto stakeholder;

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
