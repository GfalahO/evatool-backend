package com.evatool.impact.domain.event.json;

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
}
