package com.evatool.impact.application.json.mapper;

import com.evatool.impact.application.json.ImpactJson;
import com.evatool.impact.domain.entity.Impact;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImpactJsonMapper {

    private ImpactJsonMapper() {

    }

    private static final Logger logger = LoggerFactory.getLogger(ImpactJsonMapper.class);

    private static final ModelMapper modelMapper = new ModelMapper();

    public static ImpactJson toJson(Impact impact) {
        logger.info("Mapping Entity to Json");
        return modelMapper.map(impact, ImpactJson.class);
    }
}
