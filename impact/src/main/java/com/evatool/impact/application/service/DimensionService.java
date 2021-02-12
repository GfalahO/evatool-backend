package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.common.exception.InvalidUuidException;
import com.evatool.impact.common.exception.PropertyViolationException;

import java.util.List;

public interface DimensionService {

    DimensionDto findDimensionById(String id) ;

    List<DimensionDto> getAllDimensions();

    DimensionDto createDimension(DimensionDto dimensionDto);

    DimensionDto updateDimension(DimensionDto dimensionDto);

    void deleteDimensionById(String id);

    void deleteDimensions();
}
