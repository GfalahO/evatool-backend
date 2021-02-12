package com.evatool.impact.application.json.mapper;

import com.evatool.impact.application.json.DimensionJson;
import com.evatool.impact.domain.entity.Dimension;
import org.modelmapper.ModelMapper;

public class DimensionJsonMapper {

    private DimensionJsonMapper() {

    }

    private static final ModelMapper modelMapper = new ModelMapper();

    public static DimensionJson toJson(Dimension dimension) {
        return modelMapper.map(dimension, DimensionJson.class);
    }
}
