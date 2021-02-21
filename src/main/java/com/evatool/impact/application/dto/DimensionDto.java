package com.evatool.impact.application.dto;

import com.evatool.impact.domain.entity.Dimension;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DimensionDto that = (DimensionDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && type == that.type && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, description);
    }
}
