package com.evatool.impact.application.json.mapper;

import com.evatool.impact.domain.entity.ImpactStakeholder;
import org.json.JSONException;
import org.json.JSONObject;

public class ImpactStakeholderJsonMapper {
    public static ImpactStakeholder fromJson(String json) throws JSONException {
        var jsonObject = new JSONObject(json);
        var impactStakeholder = new ImpactStakeholder();
        impactStakeholder.setId(jsonObject.getString("id"));
        impactStakeholder.setName(jsonObject.getString("name"));
        return impactStakeholder;
    }
}
