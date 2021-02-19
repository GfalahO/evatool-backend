package com.evatool.impact.application.json.mapper;

import com.evatool.impact.application.json.DimensionJson;
import com.evatool.impact.domain.entity.Dimension;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DimensionJsonMapper {

    private DimensionJsonMapper() {

    }

    private static final Logger logger = LoggerFactory.getLogger(DimensionJsonMapper.class);

    private static final ModelMapper modelMapper = new ModelMapper();

    public static DimensionJson toJson(Dimension dimension) {
        logger.info("Mapping Entity to Json");
        return modelMapper.map(dimension, DimensionJson.class);
    }
}
