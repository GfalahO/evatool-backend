package com.evatool.impact.application.dto;

import lombok.Getter;
import lombok.Setter;

public class StakeholderDto {
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
