package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.DimensionDto;

import java.util.List;

public interface DimensionService {

    DimensionDto findDimensionById(String id);

    // TODO [tzaika or hbuhl] findDimensionByType(String type);

    List<DimensionDto> getAllDimensions();

    DimensionDto createDimension(DimensionDto dimensionDto);

    DimensionDto updateDimension(DimensionDto dimensionDto);

    void deleteDimensionById(String id);

    void deleteDimensions();
}
