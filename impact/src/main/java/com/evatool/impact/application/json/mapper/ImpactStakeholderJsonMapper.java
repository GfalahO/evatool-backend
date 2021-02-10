package com.evatool.impact.application.json.mapper;

import com.evatool.impact.application.json.ImpactStakeholderJson;

public class ImpactStakeholderJsonMapper {
    public static ImpactStakeholderJson fromJson(String json) {
        return new ImpactStakeholderJson(json);
    }
}
