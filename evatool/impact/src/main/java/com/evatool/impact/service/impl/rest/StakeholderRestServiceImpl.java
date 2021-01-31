package com.evatool.impact.service.impl.rest;

import com.evatool.impact.persistence.entity.Stakeholder;
import com.evatool.impact.persistence.repository.StakeholderRepository;
import com.evatool.impact.service.api.rest.StakeholderRestService;
import com.evatool.impact.service.EntityNotFoundException;
import com.evatool.impact.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StakeholderRestServiceImpl implements StakeholderRestService {
    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Override
    public Stakeholder getStakeholderById(String id) throws EntityNotFoundException {
        // TODO: Throw error when id is null or not valid UUID?
        var stakeholder = stakeholderRepository.findById(id).orElse(null);
        if (stakeholder == null) {
            throw new EntityNotFoundException(String.format("Stakeholder with id '%s' not found.", id));
        }
        return stakeholder;
    }

    @Override
    public List<Stakeholder> getAllStakeholders() {
        var stakeholders = stakeholderRepository.findAll();
        return Convert.iterableToList(stakeholders);
    }

    @Override
    public Stakeholder insertStakeholder(Stakeholder stakeholder) {
        return stakeholderRepository.save(stakeholder);
    }

    @Override
    public Stakeholder updateStakeholder(Stakeholder stakeholder) throws EntityNotFoundException {
        if (stakeholder.getId() == null) {
            throw new EntityNotFoundException(String.format("Stakeholder with id '%s' not found.", stakeholder.getId()));
        }
        return stakeholderRepository.save(stakeholder);
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
