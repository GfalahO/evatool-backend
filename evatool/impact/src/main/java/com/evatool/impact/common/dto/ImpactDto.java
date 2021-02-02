package com.evatool.impact.common.dto;

import lombok.Getter;
import lombok.Setter;

public class ImpactDto {
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
