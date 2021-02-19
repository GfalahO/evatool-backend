package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.DimensionDto;

import java.util.List;

public interface DimensionService {

    DimensionDto findDimensionById(String id);

    List<DimensionDto> findDimensionsByType(String type);

    List<DimensionDto> getAllDimensions();

    DimensionDto createDimension(DimensionDto dimensionDto);

    DimensionDto updateDimension(DimensionDto dimensionDto);

    void deleteDimensionById(String id);

    void deleteDimensions();
}
