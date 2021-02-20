package com.evatool.impact.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ImpactStakeholderDto {
    @Getter
    @Setter
    @JsonSerialize
    private UUID id;

    @Getter
    @Setter
    @NotNull
    private String name;

    @Override
    public String toString() {
        return "ImpactStakeholderDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
