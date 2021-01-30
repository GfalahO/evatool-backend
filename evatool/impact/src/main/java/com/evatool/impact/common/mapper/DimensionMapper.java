package com.evatool.impact.common.mapper;

import com.evatool.impact.common.dto.DimensionDto;
import com.evatool.impact.persistence.entity.Dimension;

public class DimensionMapper {

    public DimensionMapper() {

    }

    public Dimension fromDimension(DimensionDto dimensionDto) {
        var dimension = new Dimension();

        dimension.setId(dimensionDto.getId());
        dimension.setName(dimensionDto.getName());
        dimension.setDescription(dimensionDto.getDescription());

        return dimension;
    }

    public DimensionDto toDimensionDto(Dimension dimension) {
        var dimensionDto = new DimensionDto();

        dimensionDto.setId(dimension.getId());
        dimensionDto.setName(dimension.getName());
        dimensionDto.setDescription(dimension.getDescription());

        return dimensionDto;
    }
}
