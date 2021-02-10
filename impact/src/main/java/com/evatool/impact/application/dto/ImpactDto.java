package com.evatool.impact.application.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

public class ImpactDto extends RepresentationModel<ImpactDto> {
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private StakeholderDto stakeholder;

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
