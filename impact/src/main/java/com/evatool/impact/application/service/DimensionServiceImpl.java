package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.application.dto.mapper.DimensionDtoMapper;
import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.common.exception.InvalidUuidException;
import com.evatool.impact.common.exception.PropertyViolationException;
import com.evatool.impact.domain.entity.Dimension;
import com.evatool.impact.domain.entity.SuperEntity;
import com.evatool.impact.domain.repository.DimensionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DimensionServiceImpl implements DimensionService {

    private static final Logger logger = LoggerFactory.getLogger(DimensionServiceImpl.class);

    private final DimensionRepository dimensionRepository;

    public DimensionServiceImpl(DimensionRepository dimensionRepository) {
        this.dimensionRepository = dimensionRepository;
    }

    @Override
    public DimensionDto findDimensionById(String id) {
        logger.info("Get Dimension");
        if (!SuperEntity.isValidUuid(id)) {
            logger.error("Invalid UUID");
            throw new InvalidUuidException(id);
        }
        var dimension = dimensionRepository.findById(UUID.fromString(id));
        if (dimension.isEmpty()) {
            logger.error("{} with id '{}' not found", Dimension.class.getSimpleName(), id);
            throw new EntityNotFoundException(Dimension.class, id);
        }
        return DimensionDtoMapper.toDto(dimension.get());
    }

    @Override
    public List<DimensionDto> getAllDimensions() {
        logger.info("Get Dimensions");
        var dimensions = dimensionRepository.findAll();
        var dimensionDtoList = new ArrayList<DimensionDto>();
        dimensions.forEach(s -> dimensionDtoList.add(DimensionDtoMapper.toDto(s)));
        return dimensionDtoList;
    }

    @Override
    public DimensionDto createDimension(DimensionDto dimensionDto) {
        logger.info("Create Dimension");
        if (dimensionDto.getId() != null) {
            logger.error("Id must be null");
            throw new PropertyViolationException(String.format("A newly created '%s' must have null id.", Dimension.class.getSimpleName()));
        }
        var dimension = dimensionRepository.save(DimensionDtoMapper.fromDto(dimensionDto));
        // TODO Fire DimensionCreatedEvent
        return DimensionDtoMapper.toDto(dimension);
    }

    @Override
    public DimensionDto updateDimension(DimensionDto dimensionDto) {
        logger.info("Update Dimension");
        this.findDimensionById(dimensionDto.getId());
        var dimension = DimensionDtoMapper.fromDto(dimensionDto);
        // TODO Fire DimensionUpdatedEvent
        return DimensionDtoMapper.toDto(dimensionRepository.save(dimension));
    }

    @Override
    public void deleteDimensionById(String id) {
        logger.info("Delete Dimension");
        var dimensionDto = this.findDimensionById(id);
        var dimension = DimensionDtoMapper.fromDto(dimensionDto);
        // TODO Fire DimensionDeletedEvent
        dimensionRepository.delete(dimension);
    }

    @Override
    public void deleteDimensions() {
        logger.info("Delete Dimensions");
        dimensionRepository.deleteAll();
    }
}
