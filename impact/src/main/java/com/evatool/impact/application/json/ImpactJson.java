package com.evatool.impact.application.json;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

public class ImpactJson {
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String stakeholderId;

    @Getter
    @Setter
    private String dimensionId;

    @Getter
    @Setter
    private double value;

    @Getter
    @Setter
    private String description;

    @Override
    public String toString() {
        Gson gson = new Gson();
        var json = gson.toJson(this);
        return json;
    }
}
