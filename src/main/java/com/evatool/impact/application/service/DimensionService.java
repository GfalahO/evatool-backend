package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.domain.entity.Dimension;

import java.util.List;
import java.util.UUID;

public interface DimensionService {

    DimensionDto findById(UUID id);

    List<DimensionDto> findAllByType(Dimension.Type type);

    List<DimensionDto> findAll();

    List<Dimension.Type> findAllTypes();

    DimensionDto insert(DimensionDto dimensionDto);

    DimensionDto update(DimensionDto dimensionDto);

    void deleteById(UUID id);

    void deleteAll();
}
