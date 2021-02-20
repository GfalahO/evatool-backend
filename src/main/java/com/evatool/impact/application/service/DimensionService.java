package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.domain.entity.Dimension;

import java.util.List;
import java.util.UUID;

public interface DimensionService {

    DimensionDto findDimensionById(UUID id);

    List<DimensionDto> findDimensionsByType(Dimension.Type type);

    List<DimensionDto> getAllDimensions();

    List<Dimension.Type> getAllDimensionTypes();

    DimensionDto createDimension(DimensionDto dimensionDto);

    DimensionDto updateDimension(DimensionDto dimensionDto);

    void deleteDimensionById(UUID id);

    void deleteDimensions();
}
