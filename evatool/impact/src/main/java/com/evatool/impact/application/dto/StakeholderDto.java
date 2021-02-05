package com.evatool.impact.application.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

public class StakeholderDto extends RepresentationModel<StakeholderDto> {
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String name;

    @Override
    public String toString() {
        return "StakeholderDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
