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
        var dimension = new Dimension();
        if (dimensionDto.getId() != null) {
            dimension.setId(dimensionDto.getId());
        }
        dimension.setName(dimensionDto.getName());
        dimension.setType(dimensionDto.getType());
        dimension.setDescription(dimensionDto.getDescription());
        return dimension;
    }

    public static DimensionDto toDto(Dimension dimension) {
        logger.info("Mapping Entity to Dto");
        return modelMapper.map(dimension, DimensionDto.class);
    }
}
