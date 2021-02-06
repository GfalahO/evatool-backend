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
    private String stakeholderId;

    @Getter
    @Setter
    private String dimensionId;

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
                ", value=" + value +
                ", description='" + description + '\'' +
                ", dimensionId='" + dimensionId + '\'' +
                ", stakeholderId='" + stakeholderId + '\'' +
                '}';
    }
}
