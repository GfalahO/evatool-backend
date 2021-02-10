package com.evatool.impact.application.dto.mapper;

import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.common.exception.PropertyViolationException;
import com.evatool.impact.domain.entity.Dimension;
import com.evatool.impact.domain.entity.DimensionType;
import org.modelmapper.ModelMapper;

import java.util.Arrays;

public class DimensionDtoMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static Dimension fromDto(DimensionDto dimensionDto) {
        if (!Dimension.isDimensionType(dimensionDto.getType())) {
            throw new PropertyViolationException(String.format(
                    "Dimension type must be in %s (not case sensitive).", Arrays.asList(DimensionType.values())));
        }
        return modelMapper.map(dimensionDto, Dimension.class);
    }

    public static DimensionDto toDto(Dimension dimension) {
        return modelMapper.map(dimension, DimensionDto.class);
    }
}
