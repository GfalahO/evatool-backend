package com.evatool.impact.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class ImpactStakeholderDto {
    @Getter
    @Setter
    private UUID id;

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
