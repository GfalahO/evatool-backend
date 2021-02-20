package com.evatool.impact.application.dto;

import com.evatool.impact.domain.entity.Dimension;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class DimensionDto {
    @Getter
    @Setter
    private UUID id;

    @Getter
    @Setter
    @NotNull
    private String name;

    @Getter
    @Setter
    @NotNull
    private Dimension.Type type;

    @Getter
    @Setter
    @NotNull
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
