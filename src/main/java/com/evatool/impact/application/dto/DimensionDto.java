package com.evatool.impact.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class DimensionDto {
    @Getter
    @Setter
    private UUID id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private String description;

    @Override
    public String toString() {
        return "DimensionDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
