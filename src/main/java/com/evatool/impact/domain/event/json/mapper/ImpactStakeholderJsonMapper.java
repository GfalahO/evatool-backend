package com.evatool.impact.domain.event.json.mapper;

import com.evatool.impact.common.exception.EventPayloadInvalidException;
import com.evatool.impact.domain.entity.ImpactStakeholder;
import com.evatool.impact.domain.event.json.ImpactStakeholderJson;
import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImpactStakeholderJsonMapper {

    private ImpactStakeholderJsonMapper() {

    }

    private static final Logger logger = LoggerFactory.getLogger(ImpactStakeholderJsonMapper.class);

    private static final ModelMapper modelMapper = new ModelMapper();

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
            var id = jsonObject.isNull("id") ? null : jsonObject.getString("id");
            var name = jsonObject.isNull("name") ? null : jsonObject.getString("name");

            var impactStakeholderJson = new ImpactStakeholderJson(id, name);
            return modelMapper.map(impactStakeholderJson, ImpactStakeholder.class);
        } catch (JSONException | IllegalArgumentException ex) {
            throw new EventPayloadInvalidException(json, ex);
        }
    }
}
