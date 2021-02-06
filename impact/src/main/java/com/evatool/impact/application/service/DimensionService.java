package com.evatool.impact.application.service;

import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.domain.entity.Dimension;

import java.util.List;

public interface DimensionService {

    Dimension findDimensionById(String id) throws EntityNotFoundException;

    List<Dimension> getAllDimensions();

    Dimension createDimension(Dimension dimension);

    Dimension updateDimension(Dimension dimension) throws EntityNotFoundException;

    void deleteDimensionById(String id) throws EntityNotFoundException;
}
