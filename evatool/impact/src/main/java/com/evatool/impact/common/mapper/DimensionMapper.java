package com.evatool.impact.common.mapper;

import com.evatool.impact.common.dto.DimensionDto;
import com.evatool.impact.persistence.entity.Dimension;
import org.modelmapper.ModelMapper;

public class DimensionMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public Dimension fromDto(DimensionDto dimensionDto) {
        return modelMapper.map(dimensionDto, Dimension.class);
    }

    public DimensionDto toDto(Dimension dimension) {
        return modelMapper.map(dimension, DimensionDto.class);
    }
}
