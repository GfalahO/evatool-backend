package com.evatool.impact.service.impl.rest;

import com.evatool.impact.common.dto.StakeholderDto;
import com.evatool.impact.persistence.entity.Stakeholder;
import com.evatool.impact.persistence.repository.StakeholderRepository;
import com.evatool.impact.service.api.rest.StakeholderRestService;
import com.evatool.impact.service.impl.StakeholderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StakeholderRestServiceImpl implements StakeholderRestService {
    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Override
    public StakeholderDto getStakeholderById(String id) throws StakeholderNotFoundException {
        var stakeholder = stakeholderRepository.findById(id).orElse(null);
        if (stakeholder == null) {
            throw new StakeholderNotFoundException(String.format("Stakeholder with id '%s' not found.", id));
        }
        var stakeholderDto = stakeholder.toDto();
        return stakeholderDto;
    }

    @Override
    public List<StakeholderDto> getAllStakeholders() {
        var stakeholders = stakeholderRepository.findAll();
        var stakeholderDtos = new ArrayList<StakeholderDto>();
        stakeholders.forEach(s -> stakeholderDtos.add(s.toDto()));
        return stakeholderDtos;
    }

    @Override
    public StakeholderDto insertStakeholder(StakeholderDto stakeholderDto) {
        var stakeholder = Stakeholder.fromDto(stakeholderDto);
        return stakeholderRepository.save(stakeholder).toDto();
    }

    @Override
    public StakeholderDto updateStakeholder(StakeholderDto stakeholderDto) throws StakeholderNotFoundException {
        var stakeholder = Stakeholder.fromDto(stakeholderDto);
        if (stakeholder.getId() == null) {
            throw new StakeholderNotFoundException(String.format("Stakeholder with id '%s' not found.", stakeholder.getId()));
        }
        return stakeholderRepository.save(stakeholder).toDto();
    }

    @Override
    public void deleteStakeholderById(String id) throws StakeholderNotFoundException {
        var stakeholder = stakeholderRepository.findById(id).orElse(null);
        if (stakeholder == null) {
            throw new StakeholderNotFoundException(String.format("Stakeholder with id '%s' not found.", stakeholder.getId()));
        }
        stakeholderRepository.delete(stakeholder);
    }
}
