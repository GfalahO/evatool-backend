package com.evatool.impact.application.json.mapper;

import com.evatool.impact.application.json.ImpactStakeholderJson;
import com.evatool.impact.domain.entity.ImpactStakeholder;
import org.json.JSONException;
import org.json.JSONObject;

public class ImpactStakeholderJsonMapper {
    public static ImpactStakeholderJson fromJson(String json) throws JSONException {
        var jsonObject = new JSONObject(json);
        var impactStakeholderJson = new ImpactStakeholderJson();
        impactStakeholderJson.setId(jsonObject.getString("name"));
        impactStakeholderJson.setName(jsonObject.getString("id"));
        return impactStakeholderJson;
    }

    public static ImpactStakeholder toEntity(ImpactStakeholderJson impactStakeholderJson) {
        var stakeholder = new ImpactStakeholder(impactStakeholderJson.getName());
        stakeholder.setId(impactStakeholderJson.getId());
        return stakeholder;
    }
}
