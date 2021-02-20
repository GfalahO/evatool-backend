package com.evatool.impact.application.json.mapper;

import com.evatool.impact.common.exception.InvalidEventJsonPayloadException;
import com.evatool.impact.domain.entity.ImpactStakeholder;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class ImpactStakeholderJsonMapper {

    private ImpactStakeholderJsonMapper() {

    }

    private static final Logger logger = LoggerFactory.getLogger(ImpactStakeholderJsonMapper.class);

    public static ImpactStakeholder fromJson(String json) {
        logger.info("Mapping Json to Entity");
        ImpactStakeholder impactStakeholder;
        try {
            var jsonObject = new JSONObject(json);
            impactStakeholder = new ImpactStakeholder(
                    jsonObject.getString("name")
            );
            impactStakeholder.setId(UUID.fromString(jsonObject.getString("id")));
        } catch (JSONException jex) {
            //logger.error("Exception: [{}]", jex.getMessage()); // TODO What to do here? throw or handle? But how can this be handled? data will be inconsistent with other module.
            throw new InvalidEventJsonPayloadException(json, jex);
        }
        return impactStakeholder;
    }
}
