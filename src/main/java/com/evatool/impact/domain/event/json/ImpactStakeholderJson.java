package com.evatool.impact.domain.event.json;

import lombok.Getter;

import java.util.UUID;

public class ImpactStakeholderJson {

    @Getter
    private UUID id;

    @Getter
    private String name;

    public ImpactStakeholderJson(String id, String name){
        this.setId(id);
        this.setName(name);
    }

    public void setId(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        this.id = UUID.fromString(id);
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null.");
        }
        this.name = name;
    }
}
