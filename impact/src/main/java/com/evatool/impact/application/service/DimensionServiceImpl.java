package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.application.dto.mapper.DimensionDtoMapper;
import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.common.exception.PropertyViolationException;
import com.evatool.impact.domain.entity.Dimension;
import com.evatool.impact.domain.repository.DimensionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DimensionServiceImpl implements DimensionService {

    Logger logger = LoggerFactory.getLogger(DimensionServiceImpl.class);

    private final DimensionRepository dimensionRepository;

    public DimensionServiceImpl(DimensionRepository dimensionRepository) {
        this.dimensionRepository = dimensionRepository;
    }

    @Override
    public DimensionDto findDimensionById(String id) throws EntityNotFoundException {
        if (id == null) {
            throw new EntityNotFoundException(Dimension.class, "null");
        }
        var dimension = dimensionRepository.findById(id);
        if (dimension.isEmpty()) {
            throw new EntityNotFoundException(Dimension.class, id);
        }
        return DimensionDtoMapper.toDto(dimension.get());
    }

    @Override
    public List<DimensionDto> getAllDimensions() {
        var dimensions = dimensionRepository.findAll();
        var dimensionDtoList = new ArrayList<DimensionDto>();
        dimensions.forEach(s -> dimensionDtoList.add(DimensionDtoMapper.toDto(s)));
        return dimensionDtoList;
    }

    @Override
    public DimensionDto createDimension(DimensionDto dimensionDto) {
        if (dimensionDto.getId() != null) {
            throw new PropertyViolationException(String.format("A newly created '%s' must have null id.", Dimension.class.getSimpleName()));
        }
        var dimension = dimensionRepository.save(DimensionDtoMapper.fromDto(dimensionDto));
        return DimensionDtoMapper.toDto(dimension);
    }

    @Override
    public DimensionDto updateDimension(DimensionDto dimensionDto) throws EntityNotFoundException {
        this.findDimensionById(dimensionDto.getId());
        var dimension = DimensionDtoMapper.fromDto(dimensionDto);
        return DimensionDtoMapper.toDto(dimensionRepository.save(dimension));
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
