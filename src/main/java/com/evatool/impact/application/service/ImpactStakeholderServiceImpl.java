package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.ImpactStakeholderDto;
import com.evatool.impact.application.dto.mapper.ImpactStakeholderDtoMapper;
import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.domain.entity.ImpactStakeholder;
import com.evatool.impact.domain.entity.SuperEntity;
import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImpactStakeholderServiceImpl implements ImpactStakeholderService {

    private static final Logger logger = LoggerFactory.getLogger(ImpactStakeholderServiceImpl.class);

    private final ImpactStakeholderRepository stakeholderRepository;

    public ImpactStakeholderServiceImpl(ImpactStakeholderRepository stakeholderRepository) {
        this.stakeholderRepository = stakeholderRepository;
    }

    @Override
    public ImpactStakeholderDto findStakeholderById(String id) {
        logger.info("Get Stakeholder");
        SuperEntity.probeExistingId(id);
        var stakeholder = stakeholderRepository.findById(UUID.fromString(id));
        if (stakeholder.isEmpty()) {
            logger.error("Entity not found");
            throw new EntityNotFoundException(ImpactStakeholder.class, id);
        }
        return ImpactStakeholderDtoMapper.toDto(stakeholder.get());
    }

    @Override
    public List<ImpactStakeholderDto> getAllStakeholders() {
        logger.info("Get Stakeholders");
        var stakeholders = stakeholderRepository.findAll();
        var stakeholderDtoList = new ArrayList<ImpactStakeholderDto>();
        stakeholders.forEach(stakeholder -> stakeholderDtoList.add(ImpactStakeholderDtoMapper.toDto(stakeholder)));
        return stakeholderDtoList;
    }

    @Override
    public ImpactStakeholderDto createStakeholder(ImpactStakeholderDto impactStakeholderDto) {
        logger.info("Create Stakeholder");
        SuperEntity.probeNonExistingId(impactStakeholderDto.getId());
        var stakeholder = stakeholderRepository.save(ImpactStakeholderDtoMapper.fromDto(impactStakeholderDto));
        return ImpactStakeholderDtoMapper.toDto(stakeholder);
    }

    @Override
    public ImpactStakeholderDto updateStakeholder(ImpactStakeholderDto impactStakeholderDto) {
        logger.info("Update Stakeholder");
        this.findStakeholderById(impactStakeholderDto.getId());
        var stakeholder = ImpactStakeholderDtoMapper.fromDto(impactStakeholderDto);
        return ImpactStakeholderDtoMapper.toDto(stakeholderRepository.save(stakeholder));
    }

    @Override
    public void deleteStakeholderById(String id) {
        logger.info("Delete Stakeholder");
        var stakeholderDto = this.findStakeholderById(id);
        var stakeholder = ImpactStakeholderDtoMapper.fromDto(stakeholderDto);
        stakeholderRepository.delete(stakeholder);
    }

    @Override
    public void deleteStakeholders() {
        logger.info("Delete Stakeholders");
        stakeholderRepository.deleteAll();
    }
}
