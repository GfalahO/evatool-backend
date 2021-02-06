package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.StakeholderDto;
import com.evatool.impact.application.dto.mapper.StakeholderMapper;
import com.evatool.impact.common.exception.EntityNotFoundException;
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
        if(id == null){
            throw new EntityNotFoundException(ImpactStakeholder.class, "null");
        }
        var stakeholder = stakeholderRepository.findById(id);
        if (stakeholder.isEmpty()) {
            throw new EntityNotFoundException(ImpactStakeholder.class, id);
        }
        var stakeholderDto = StakeholderMapper.toDto(stakeholder.get());
        return stakeholderDto;
    }

    @Override
    public List<StakeholderDto> getAllStakeholders() {
        var stakeholders = stakeholderRepository.findAll();
        var stakeholderDtoList = new ArrayList<StakeholderDto>();
        stakeholders.forEach(s -> stakeholderDtoList.add(StakeholderMapper.toDto(s)));
        return stakeholderDtoList;
    }

    @Override
    public StakeholderDto createStakeholder(StakeholderDto stakeholderDto) {
        var stakeholder = stakeholderRepository.save(StakeholderMapper.fromDto(stakeholderDto));
        return StakeholderMapper.toDto(stakeholder);
    }

    @Override
    public StakeholderDto updateStakeholder(StakeholderDto stakeholderDto) throws EntityNotFoundException {
        this.findStakeholderById(stakeholderDto.getId());
        var stakeholder = StakeholderMapper.fromDto(stakeholderDto);
        return StakeholderMapper.toDto(stakeholderRepository.save(stakeholder));
    }

    @Override
    public void deleteStakeholderById(String id) throws EntityNotFoundException {
        var stakeholderDto = this.findStakeholderById(id);
        var stakeholder = StakeholderMapper.fromDto(stakeholderDto);
        stakeholderRepository.delete(stakeholder);
    }

    @Override
    public void deleteStakeholders() {
        stakeholderRepository.deleteAll();
    }
}
