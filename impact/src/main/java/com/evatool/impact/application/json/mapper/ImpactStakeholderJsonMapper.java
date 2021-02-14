package com.evatool.impact.application.json.mapper;

import com.evatool.impact.common.exception.InvalidEventJsonPayloadException;
import com.evatool.impact.domain.entity.ImpactStakeholder;
import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImpactStakeholderJsonMapper {

    private static final Logger logger = LoggerFactory.getLogger(ImpactStakeholderJsonMapper.class);

    private static final ModelMapper modelMapper = new ModelMapper();

    public static ImpactStakeholder fromJson(String json) {
        logger.info("Mapping Json to Entity");
        var impactStakeholder = new ImpactStakeholder();
        try {
            var jsonObject = new JSONObject(json);
            impactStakeholder.setId(jsonObject.getString("id"));
            impactStakeholder.setName(jsonObject.getString("name"));
        } catch (JSONException jex) {
            logger.error("Invalid Json", jex); // TODO Should this be fatal?
            throw new InvalidEventJsonPayloadException(json, jex);
        }
        return impactStakeholder;
    }
}
