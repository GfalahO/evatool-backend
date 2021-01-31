package com.evatool.impact.service.impl.rest;

import com.evatool.impact.persistence.entity.Dimension;
import com.evatool.impact.persistence.repository.DimensionRepository;
import com.evatool.impact.persistence.repository.StakeholderRepository;
import com.evatool.impact.service.EntityNotFoundException;
import com.evatool.impact.service.api.rest.DimensionRestService;
import com.evatool.impact.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DimensionRestServiceImpl implements DimensionRestService {
    @Autowired
    private DimensionRepository dimensionRepository;

    @Override
    public Dimension getDimensionById(String id) throws EntityNotFoundException {
        var dimension = dimensionRepository.findById(id).orElse(null);
        if (dimension == null) {
            throw new EntityNotFoundException(String.format("Dimension with id '%s' not found.", id));
        }
        return dimension;
    }

    @Override
    public List<Dimension> getAllDimensions() {
        var dimensions = dimensionRepository.findAll();
        return Convert.iterableToList(dimensions);
    }

    @Override
    public Dimension insertDimension(Dimension dimension) {
        return dimensionRepository.save(dimension);
    }

    @Override
    public Dimension updateDimension(Dimension dimension) throws EntityNotFoundException {
        if (dimension.getId() == null) {
            throw new EntityNotFoundException(String.format("Dimension with id '%s' not found.", dimension.getId()));
        }
        return dimensionRepository.save(dimension);
    }

    @Override
    public void deleteDimensionById(String id) throws EntityNotFoundException {
        var dimension = dimensionRepository.findById(id).orElse(null);
        if (dimension == null) {
            throw new EntityNotFoundException(String.format("Dimension with id '%s' not found.", dimension.getId()));
        }
        dimensionRepository.delete(dimension);
    }
}
