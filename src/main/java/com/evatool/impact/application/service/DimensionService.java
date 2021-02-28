package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.common.DimensionType;

import java.util.List;
import java.util.UUID;

public interface DimensionService {

    DimensionDto findById(UUID id);

    List<DimensionDto> findAllByType(DimensionType type);

    List<DimensionDto> findAll();

    List<DimensionType> findAllTypes();

    DimensionDto create(DimensionDto dimensionDto);

    DimensionDto update(DimensionDto dimensionDto);

    void deleteById(UUID id);

    void deleteAll();
}
