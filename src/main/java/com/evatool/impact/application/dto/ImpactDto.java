package com.evatool.impact.application.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class ImpactDto {

    @Getter
    @Setter
    private UUID id;

    @Getter
    @Setter
    @DecimalMin("-1.0")
    @DecimalMax("1.0")
    private double value;

    @Getter
    @Setter
    @NotNull
    private String description;

    @Getter
    @Setter
    @NotNull
    private ImpactStakeholderDto stakeholder;

    @Getter
    @Setter
    @NotNull
    private DimensionDto dimension;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImpactDto impactDto = (ImpactDto) o;
        return Double.compare(impactDto.value, value) == 0 && Objects.equals(id, impactDto.id) && Objects.equals(description, impactDto.description) && Objects.equals(stakeholder, impactDto.stakeholder) && Objects.equals(dimension, impactDto.dimension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, description, stakeholder, dimension);
    }
}
