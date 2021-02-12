package com.evatool.impact.application.json.mapper;

import com.evatool.impact.application.json.ImpactJson;
import com.evatool.impact.domain.entity.Impact;
import org.modelmapper.ModelMapper;

public class ImpactJsonMapper {

    private ImpactJsonMapper() {

    }

    private static final ModelMapper modelMapper = new ModelMapper();

    public static ImpactJson toJson(Impact impact) {
        return modelMapper.map(impact, ImpactJson.class);
    }
}
