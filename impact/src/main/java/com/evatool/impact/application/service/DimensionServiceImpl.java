package com.evatool.impact.application.service;

import com.evatool.impact.common.Convert;
import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.domain.entity.Dimension;
import com.evatool.impact.domain.repository.DimensionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DimensionServiceImpl implements DimensionService {

    @Autowired
    private DimensionRepository dimensionRepository;

    @Override
    public Dimension findDimensionById(String id) throws EntityNotFoundException {
        var dimension = dimensionRepository.findById(id);
        if (dimension.isEmpty()) {
            throw new EntityNotFoundException(Dimension.class, id);
        }
        return dimension.get();
    }

    @Override
    public List<Dimension> getAllDimensions() {
        var dimensions = dimensionRepository.findAll();
        return Convert.iterableToList(dimensions);
    }

    @Override
    public Dimension createDimension(Dimension dimension) {
        return dimensionRepository.save(dimension);
    }

    @Override
    public Dimension updateDimension(Dimension dimension) throws EntityNotFoundException {
        if (dimensionRepository.findById(dimension.getId()).isEmpty()) {
            throw new EntityNotFoundException(Dimension.class, dimension.getId());
        }
        return dimensionRepository.save(dimension);
    }

    @Override
    public void deleteDimensionById(String id) throws EntityNotFoundException {
        var dimension = dimensionRepository.findById(id);
        if (dimension.isEmpty()) {
            throw new EntityNotFoundException(Dimension.class, id);
        }
        dimensionRepository.delete(dimension.get());
    }
}
