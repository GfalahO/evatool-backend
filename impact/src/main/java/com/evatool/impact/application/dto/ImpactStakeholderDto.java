package com.evatool.impact.application.dto;

import lombok.Getter;
import lombok.Setter;

public class ImpactStakeholderDto {
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String name;

    @Override
    public String toString() {
        return "ImpactStakeholderDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
