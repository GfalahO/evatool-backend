package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.application.dto.mapper.DimensionDtoMapper;
import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.domain.entity.Dimension;
import com.evatool.impact.domain.entity.SuperEntity;
import com.evatool.impact.domain.event.dimension.DimensionCreatedEventPublisher;
import com.evatool.impact.domain.event.dimension.DimensionDeletedEventPublisher;
import com.evatool.impact.domain.event.dimension.DimensionUpdatedEventPublisher;
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

    private final DimensionCreatedEventPublisher dimensionCreatedEventPublisher;

    private final DimensionUpdatedEventPublisher dimensionUpdatedEventPublisher;

    private final DimensionDeletedEventPublisher dimensionDeletedEventPublisher;

    public DimensionServiceImpl(DimensionRepository dimensionRepository, DimensionCreatedEventPublisher dimensionCreatedEventPublisher, DimensionUpdatedEventPublisher dimensionupdatedEventPublisher, DimensionDeletedEventPublisher dimensionDeletedEventPublisher) {
        this.dimensionRepository = dimensionRepository;
        this.dimensionCreatedEventPublisher = dimensionCreatedEventPublisher;
        this.dimensionUpdatedEventPublisher = dimensionupdatedEventPublisher;
        this.dimensionDeletedEventPublisher = dimensionDeletedEventPublisher;
    }

    @Override
    public DimensionDto findDimensionById(String id) {
        logger.info("Get Dimension");
        SuperEntity.probeExistingId(id);
        var dimension = dimensionRepository.findById(UUID.fromString(id));
        if (dimension.isEmpty()) {
            logger.error("Entity not found");
            throw new EntityNotFoundException(Dimension.class, id);
        }
        return DimensionDtoMapper.toDto(dimension.get());
    }

    @Override
    public List<DimensionDto> findDimensionsByType(String type) {
        logger.info("Get Dimensions by type");
        var dimensions = dimensionRepository.findDimensionsByType(type);
        var dimensionDtoList = new ArrayList<DimensionDto>();
        dimensions.forEach(s -> dimensionDtoList.add(DimensionDtoMapper.toDto(s)));
        return dimensionDtoList;
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
        SuperEntity.probeNonExistingId(dimensionDto.getId());
        var dimension = dimensionRepository.save(DimensionDtoMapper.fromDto(dimensionDto));
        dimensionCreatedEventPublisher.onDimensionCreated(dimension);
        return DimensionDtoMapper.toDto(dimension);
    }

    @Override
    public DimensionDto updateDimension(DimensionDto dimensionDto) {
        logger.info("Update Dimension");
        this.findDimensionById(dimensionDto.getId());
        var dimension = dimensionRepository.save(DimensionDtoMapper.fromDto(dimensionDto));
        dimensionUpdatedEventPublisher.onDimensionUpdated(dimension);
        return DimensionDtoMapper.toDto(dimension);
    }

    @Override
    public void deleteDimensionById(String id) {
        logger.info("Delete Dimension");
        var dimensionDto = this.findDimensionById(id);
        var dimension = DimensionDtoMapper.fromDto(dimensionDto);
        dimensionRepository.delete(dimension);
        dimensionDeletedEventPublisher.onDimensionDeleted(dimension);
    }

    @Override
    public void deleteDimensions() {
        logger.info("Delete Dimensions");
        dimensionRepository.deleteAll();
    }
}
