package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.ImpactDto;
import com.evatool.impact.application.dto.mapper.ImpactDtoMapper;
import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.common.exception.PropertyViolationException;
import com.evatool.impact.domain.entity.Impact;
import com.evatool.impact.domain.entity.SuperEntity;
import com.evatool.impact.domain.repository.DimensionRepository;
import com.evatool.impact.domain.repository.ImpactRepository;
import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImpactServiceImpl implements ImpactService {

    private static final Logger logger = LoggerFactory.getLogger(ImpactServiceImpl.class);

    private final ImpactRepository impactRepository;

    private final ImpactStakeholderRepository impactStakeholderRepository;

    private final DimensionRepository dimensionRepository;

    public ImpactServiceImpl(ImpactRepository impactRepository, ImpactStakeholderRepository impactStakeholderRepository, DimensionRepository dimensionRepository) {
        this.impactRepository = impactRepository;
        this.impactStakeholderRepository = impactStakeholderRepository;
        this.dimensionRepository = dimensionRepository;
    }

    @Override
    public ImpactDto findImpactById(String id) {
        logger.info("Get Impact");
        SuperEntity.probeExistingId(id);
        var impact = impactRepository.findById(UUID.fromString(id));
        if (impact.isEmpty()) {
            logger.error("Entity not found");
            throw new EntityNotFoundException(Impact.class, id);
        }
        return ImpactDtoMapper.toDto(impact.get());
    }

    @Override
    public List<ImpactDto> getAllImpacts() {
        logger.info("Get Impacts");
        var impacts = impactRepository.findAll();
        var impactDtoList = new ArrayList<ImpactDto>();
        impacts.forEach(impact -> impactDtoList.add(ImpactDtoMapper.toDto(impact)));
        return impactDtoList;
    }

    @Override
    public ImpactDto createImpact(ImpactDto impactDto) {
        logger.info("Create Impact");
        SuperEntity.probeNonExistingId(impactDto.getId());
        var impact = ImpactDtoMapper.fromDto(impactDto, dimensionRepository, impactStakeholderRepository);
        // TODO Fire ImpactCreatedEvent
        return ImpactDtoMapper.toDto(impactRepository.save(impact));
    }

    @Override
    public ImpactDto updateImpact(ImpactDto impactDto) {
        logger.info("Update Impact");
        this.findImpactById(impactDto.getId());
        var impact = ImpactDtoMapper.fromDto(impactDto, dimensionRepository, impactStakeholderRepository);
        // TODO Fire ImpactUpdatedEvent
        return ImpactDtoMapper.toDto(impactRepository.save(impact));
    }

    @Override
    public void deleteImpactById(String id) {
        logger.info("Delete Impact");
        // TODO Fire ImpactDeletedEvent
        impactRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public void deleteImpacts() {
        logger.info("Delete Impacts");
        impactRepository.deleteAll();
    }
}
