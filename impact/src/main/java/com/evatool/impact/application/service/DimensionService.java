package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.common.exception.EntityNotFoundException;

import java.util.List;

public interface DimensionService {
    DimensionDto findDimensionById(String id) throws EntityNotFoundException;

    List<DimensionDto> getAllDimensions();

    DimensionDto createDimension(DimensionDto dimensionDto);

    DimensionDto updateDimension(DimensionDto dimensionDto) throws EntityNotFoundException;

    void deleteDimensionById(String id) throws EntityNotFoundException;

    void deleteDimensions();
}
