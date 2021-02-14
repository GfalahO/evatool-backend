package com.evatool.impact.application.dto.mapper;

import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.common.exception.PropertyViolationException;
import com.evatool.impact.domain.entity.Dimension;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class DimensionDtoMapper {

    private static final Logger logger = LoggerFactory.getLogger(DimensionDtoMapper.class);

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Dimension fromDto(DimensionDto dimensionDto) {
        logger.info("Mapping Dto to Entity");
        if (!Dimension.isValidType(dimensionDto.getType())) { // TODO remove
            logger.error("Invalid Dimension Type");
            throw new PropertyViolationException(String.format(
                    "Dimension type must be in %s.", Arrays.asList(Dimension.Type.values())));
        }
        var dimension = modelMapper.map(dimensionDto, Dimension.class);
        if (dimensionDto.getId() != null) {
            dimension.setId(dimensionDto.getId());
        }
        return dimension;
    }

    public static DimensionDto toDto(Dimension dimension) {
        logger.info("Mapping Entity to Dto");
        return modelMapper.map(dimension, DimensionDto.class);
    }
}
