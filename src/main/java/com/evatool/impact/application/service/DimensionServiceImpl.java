package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.application.dto.mapper.DimensionDtoMapper;
import com.evatool.impact.common.DimensionType;
import com.evatool.impact.common.exception.EntityIdMustBeNullException;
import com.evatool.impact.common.exception.EntityIdRequiredException;
import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.domain.entity.Dimension;
import com.evatool.impact.domain.event.DimensionEventPublisher;
import com.evatool.impact.domain.repository.DimensionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class DimensionServiceImpl implements DimensionService {

    private static final Logger logger = LoggerFactory.getLogger(DimensionServiceImpl.class);

    private final DimensionRepository dimensionRepository;

    private final DimensionEventPublisher dimensionEventPublisher;

    public DimensionServiceImpl(DimensionRepository dimensionRepository, DimensionEventPublisher dimensionEventPublisher) {
        this.dimensionRepository = dimensionRepository;
        this.dimensionEventPublisher = dimensionEventPublisher;
    }

    @Override
    public DimensionDto findById(UUID id) {
        logger.info("Get Dimension");
        if (id == null) {
            throw new EntityIdRequiredException(Dimension.class.getSimpleName());
        }
        var dimension = dimensionRepository.findById(id);
        if (dimension.isEmpty()) {
            throw new EntityNotFoundException(Dimension.class, id);
        }
        return DimensionDtoMapper.toDto(dimension.get());
    }

    @Override
    public List<DimensionDto> findAllByType(DimensionType type) {
        logger.info("Get Dimensions by type");
        var dimensions = dimensionRepository.findAllByType(type);
        var dimensionDtoList = new ArrayList<DimensionDto>();
        dimensions.forEach(dimension -> dimensionDtoList.add(DimensionDtoMapper.toDto(dimension)));
        return dimensionDtoList;
    }

    @Override
    public List<DimensionDto> findAll() {
        logger.info("Get Dimensions");
        var dimensions = dimensionRepository.findAll();
        var dimensionDtoList = new ArrayList<DimensionDto>();
        dimensions.forEach(dimension -> dimensionDtoList.add(DimensionDtoMapper.toDto(dimension)));
        return dimensionDtoList;
    }

    @Override
    public List<DimensionType> findAllTypes() {
        logger.info("Get Dimension Types");
        return Arrays.asList(DimensionType.values());
    }

    @Override
    public DimensionDto create(DimensionDto dimensionDto) {
        logger.info("Create Dimension");
        if (dimensionDto.getId() != null) {
            throw new EntityIdMustBeNullException(Dimension.class.getSimpleName());
        }
        var dimension = dimensionRepository.save(DimensionDtoMapper.fromDto(dimensionDto));
        dimensionEventPublisher.publishDimensionCreated(dimension);
        return DimensionDtoMapper.toDto(dimension);
    }

    @Override
    public DimensionDto update(DimensionDto dimensionDto) {
        logger.info("Update Dimension");
        this.findById(dimensionDto.getId());
        var dimension = dimensionRepository.save(DimensionDtoMapper.fromDto(dimensionDto));
        dimensionEventPublisher.publishDimensionUpdated(dimension);
        return DimensionDtoMapper.toDto(dimension);
    }

    @Override
    public void deleteById(UUID id) {
        logger.info("Delete Dimension");
        var dimensionDto = this.findById(id);
        var dimension = DimensionDtoMapper.fromDto(dimensionDto);
        dimensionRepository.delete(dimension);
        dimensionEventPublisher.publishDimensionDeleted(dimension);
    }

    @Override
    public void deleteAll() {
        logger.info("Delete Dimensions");
        dimensionRepository.deleteAll();
    }
}
