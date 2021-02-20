package com.evatool.impact.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ImpactDto {
    @Getter
    @Setter
    private UUID id;

    @Getter
    @Setter
    @NotNull
    private ImpactStakeholderDto stakeholder;

    @Getter
    @Setter
    @NotNull
    private DimensionDto dimension;

    @Getter
    @Setter
    @DecimalMin("-1.0")
    @DecimalMax("1.0")
    private double value;

    @Getter
    @Setter
    @NotNull
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
