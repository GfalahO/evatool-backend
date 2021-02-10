package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.StakeholderDto;
import com.evatool.impact.application.dto.mapper.StakeholderDtoMapper;
import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.common.exception.PropertyViolationException;
import com.evatool.impact.domain.entity.ImpactStakeholder;
import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImpactStakeholderServiceImpl implements ImpactStakeholderService {
    @Autowired
    private ImpactStakeholderRepository stakeholderRepository;

    @Override
    public StakeholderDto findStakeholderById(String id) throws EntityNotFoundException {
        if (id == null) {
            throw new EntityNotFoundException(ImpactStakeholder.class, "null");
        }
        var stakeholder = stakeholderRepository.findById(id);
        if (stakeholder.isEmpty()) {
            throw new EntityNotFoundException(ImpactStakeholder.class, id);
        }
        var stakeholderDto = StakeholderDtoMapper.toDto(stakeholder.get());
        return stakeholderDto;
    }

    @Override
    public List<StakeholderDto> getAllStakeholders() {
        var stakeholders = stakeholderRepository.findAll();
        var stakeholderDtoList = new ArrayList<StakeholderDto>();
        stakeholders.forEach(s -> stakeholderDtoList.add(StakeholderDtoMapper.toDto(s)));
        return stakeholderDtoList;
    }

    @Override
    public StakeholderDto createStakeholder(StakeholderDto stakeholderDto) {
        if (stakeholderDto.getId() != null) {
            throw new PropertyViolationException(String.format("A newly created '%s' must have null id.", ImpactStakeholder.class.getSimpleName()));
        }
        var stakeholder = stakeholderRepository.save(StakeholderDtoMapper.fromDto(stakeholderDto));
        return StakeholderDtoMapper.toDto(stakeholder);
    }

    @Override
    public StakeholderDto updateStakeholder(StakeholderDto stakeholderDto) throws EntityNotFoundException {
        this.findStakeholderById(stakeholderDto.getId());
        var stakeholder = StakeholderDtoMapper.fromDto(stakeholderDto);
        return StakeholderDtoMapper.toDto(stakeholderRepository.save(stakeholder));
    }

    @Override
    public void deleteStakeholderById(String id) throws EntityNotFoundException {
        var stakeholderDto = this.findStakeholderById(id);
        var stakeholder = StakeholderDtoMapper.fromDto(stakeholderDto);
        stakeholderRepository.delete(stakeholder);
    }

    @Override
    public void deleteStakeholders() {
        stakeholderRepository.deleteAll();
    }
}
