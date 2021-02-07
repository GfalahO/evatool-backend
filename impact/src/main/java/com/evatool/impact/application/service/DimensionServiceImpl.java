package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.application.dto.mapper.DimensionMapper;
import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.common.exception.PropertyViolationException;
import com.evatool.impact.domain.entity.Dimension;
import com.evatool.impact.domain.entity.ImpactStakeholder;
import com.evatool.impact.domain.repository.DimensionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DimensionServiceImpl implements DimensionService {
    @Autowired
    private DimensionRepository dimensionRepository;

    @Override
    public DimensionDto findDimensionById(String id) throws EntityNotFoundException {
        if (id == null) {
            throw new EntityNotFoundException(Dimension.class, "null");
        }
        var dimension = dimensionRepository.findById(id);
        if (dimension.isEmpty()) {
            throw new EntityNotFoundException(Dimension.class, id);
        }
        var dimensionDto = DimensionMapper.toDto(dimension.get());
        return dimensionDto;
    }

    @Override
    public List<DimensionDto> getAllDimensions() {
        var dimensions = dimensionRepository.findAll();
        var dimensionDtoList = new ArrayList<DimensionDto>();
        dimensions.forEach(s -> dimensionDtoList.add(DimensionMapper.toDto(s)));
        return dimensionDtoList;
    }

    @Override
    public DimensionDto createDimension(DimensionDto dimensionDto) {
        if (dimensionDto.getId() != null) {
            throw new PropertyViolationException(String.format("A newly created '%s' must have null id.", Dimension.class.getSimpleName()));
        }
        var dimension = dimensionRepository.save(DimensionMapper.fromDto(dimensionDto));
        return DimensionMapper.toDto(dimension);
    }

    @Override
    public DimensionDto updateDimension(DimensionDto dimensionDto) throws EntityNotFoundException {
        this.findDimensionById(dimensionDto.getId());
        var dimension = DimensionMapper.fromDto(dimensionDto);
        return DimensionMapper.toDto(dimensionRepository.save(dimension));
    }

    @Override
    public void deleteDimensionById(String id) throws EntityNotFoundException {
        var dimension = dimensionRepository.findById(id);
        if (dimension.isEmpty()) {
            throw new EntityNotFoundException(Dimension.class, id);
        }
        dimensionRepository.delete(dimension.get());
    }

    @Override
    public void deleteDimensions() {
        dimensionRepository.deleteAll();
    }
}
