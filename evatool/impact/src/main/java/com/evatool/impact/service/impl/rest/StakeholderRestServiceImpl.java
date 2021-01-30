package com.evatool.impact.service.impl.rest;

import com.evatool.impact.common.dto.StakeholderDto;
import com.evatool.impact.common.mapper.StakeholderMapper;
import com.evatool.impact.persistence.repository.StakeholderRepository;
import com.evatool.impact.service.api.rest.StakeholderRestService;
import com.evatool.impact.service.impl.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StakeholderRestServiceImpl implements StakeholderRestService {
    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Override
    public StakeholderDto getStakeholderById(String id) throws EntityNotFoundException {
        var stakeholderMapper = new StakeholderMapper();
        var stakeholder = stakeholderRepository.findById(id).orElse(null);
        if (stakeholder == null) {
            throw new EntityNotFoundException(String.format("Stakeholder with id '%s' not found.", id));
        }
        var stakeholderDto = stakeholderMapper.toDto(stakeholder);
        return stakeholderDto;
    }

    @Override
    public List<StakeholderDto> getAllStakeholders() {
        var stakeholderMapper = new StakeholderMapper();
        var stakeholders = stakeholderRepository.findAll();
        var stakeholderDtos = new ArrayList<StakeholderDto>();
        stakeholders.forEach(s -> stakeholderDtos.add(stakeholderMapper.toDto(s)));
        return stakeholderDtos;
    }

    @Override
    public StakeholderDto insertStakeholder(StakeholderDto stakeholderDto) {
        var stakeholderMapper = new StakeholderMapper();
        var stakeholder = stakeholderMapper.fromDto(stakeholderDto);
        return stakeholderMapper.toDto(stakeholderRepository.save(stakeholder));
    }

    @Override
    public StakeholderDto updateStakeholder(StakeholderDto stakeholderDto) throws EntityNotFoundException {
        var stakeholderMapper = new StakeholderMapper();
        var stakeholder = stakeholderMapper.fromDto(stakeholderDto);
        if (stakeholder.getId() == null) {
            throw new EntityNotFoundException(String.format("Stakeholder with id '%s' not found.", stakeholder.getId()));
        }
        return stakeholderMapper.toDto(stakeholderRepository.save(stakeholder));
    }

    @Override
    public void deleteStakeholderById(String id) throws EntityNotFoundException {
        var stakeholder = stakeholderRepository.findById(id).orElse(null);
        if (stakeholder == null) {
            throw new EntityNotFoundException(String.format("Stakeholder with id '%s' not found.", stakeholder.getId()));
        }
        stakeholderRepository.delete(stakeholder);
    }
}
