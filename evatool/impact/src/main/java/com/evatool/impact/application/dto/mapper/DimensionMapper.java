package com.evatool.impact.application.dto.mapper;

import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.domain.entity.Dimension;
import org.modelmapper.ModelMapper;

public class DimensionMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static Dimension fromDto(DimensionDto dimensionDto) {
        return modelMapper.map(dimensionDto, Dimension.class);
    }

    public static DimensionDto toDto(Dimension dimension) {
        return modelMapper.map(dimension, DimensionDto.class);
    }
}
