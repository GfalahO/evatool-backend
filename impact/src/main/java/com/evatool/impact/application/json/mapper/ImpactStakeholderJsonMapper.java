package com.evatool.impact.application.json.mapper;

import com.evatool.impact.common.exception.PropertyViolationException;
import com.evatool.impact.domain.entity.ImpactStakeholder;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class ImpactStakeholderJsonMapper {

    private ImpactStakeholderJsonMapper() {

    }

    public static ImpactStakeholder fromJson(String json) throws JSONException {
        var jsonObject = new JSONObject(json);
        var impactStakeholder = new ImpactStakeholder();
        impactStakeholder.setId(jsonObject.getString("id"));
        impactStakeholder.setName(jsonObject.getString("name")); // null is converted to "null".
        return impactStakeholder;
    }
}
