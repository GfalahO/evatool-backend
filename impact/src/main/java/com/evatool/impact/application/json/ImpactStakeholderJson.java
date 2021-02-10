package com.evatool.impact.application.json;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

public class ImpactStakeholderJson {
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String name;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
