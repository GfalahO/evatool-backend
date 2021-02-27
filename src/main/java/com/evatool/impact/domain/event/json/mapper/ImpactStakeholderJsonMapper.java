package com.evatool.impact.domain.event.json.mapper;

import com.evatool.impact.common.exception.EventPayloadInvalidException;
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

    /**
     * Converts a json string to a Stakeholder entity.
     *
     * @param json of the stakeholder.
     * @return the stakeholder entity.
     * @throws EventPayloadInvalidException if the json is invalid or contains invalid values.
     */
    public static ImpactStakeholder fromJson(String json) {
        logger.info("Mapping Json to Entity");
        try {
            var jsonObject = new JSONObject(json);
            var stakeholderName = jsonObject.isNull("name") ? null : jsonObject.getString("name");
            var stakeholderId = jsonObject.getString("id");

            var impactStakeholder = new ImpactStakeholder(stakeholderName);
            impactStakeholder.setId(UUID.fromString(stakeholderId));
            return impactStakeholder;
        } catch (JSONException | IllegalArgumentException ex) {
            throw new EventPayloadInvalidException(json, ex);
        }
    }
}
