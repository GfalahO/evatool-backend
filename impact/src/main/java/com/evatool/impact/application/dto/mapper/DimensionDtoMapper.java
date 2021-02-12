package com.evatool.impact.application.dto.mapper;

import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.common.exception.PropertyViolationException;
import com.evatool.impact.domain.entity.Dimension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.UUID;

public class DimensionDtoMapper {

    private DimensionDtoMapper() {

    }

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Dimension fromDto(DimensionDto dimensionDto) {
        if (!Dimension.isValidType(dimensionDto.getType())) {
            throw new PropertyViolationException(String.format(
                    "Dimension type must be in %s.", Arrays.asList(Dimension.Type.values())));
        }
        var dimension = modelMapper.map(dimensionDto, Dimension.class);
        if (dimensionDto.getId() != null) {
            dimension.setId(UUID.fromString(dimensionDto.getId()));
        }
        return dimension;
    }

    public static DimensionDto toDto(Dimension dimension) {
        return modelMapper.map(dimension, DimensionDto.class);
    }
}
