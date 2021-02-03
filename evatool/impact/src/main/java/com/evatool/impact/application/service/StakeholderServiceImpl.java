package com.evatool.impact.application.service;

import com.evatool.impact.common.Convert;
import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.common.exception.EntityNullException;
import com.evatool.impact.common.exception.IdNullException;
import com.evatool.impact.domain.entity.Dimension;
import com.evatool.impact.domain.entity.Stakeholder;
import com.evatool.impact.domain.repository.StakeholderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StakeholderServiceImpl implements StakeholderService {

    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Override
    public Stakeholder findStakeholderById(String id) throws EntityNotFoundException, IdNullException {
        if (id == null) {
            throw new IdNullException(Stakeholder.class);
        }

        var stakeholder = stakeholderRepository.findById(id);
        if (stakeholder.isEmpty()) {
            throw new EntityNotFoundException(Stakeholder.class, id);
        }
        return stakeholder.get();
    }

    @Override
    public List<Stakeholder> getAllStakeholders() {
        var stakeholders = stakeholderRepository.findAll();
        return Convert.iterableToList(stakeholders);
    }

    @Override
    public Stakeholder createStakeholder(Stakeholder stakeholder) {
        if (stakeholder == null) {
            throw new EntityNullException(Stakeholder.class);
        }
        return stakeholderRepository.save(stakeholder);
    }

    @Override
    public Stakeholder updateStakeholder(Stakeholder stakeholder) throws EntityNotFoundException, IdNullException {
        // TODO [hbuhl] not really
        if (stakeholder == null) {
            throw new EntityNullException(Stakeholder.class);
        }
        findStakeholderById(stakeholder.getId());
        return stakeholderRepository.save(stakeholder);
    }

    @Override
    public void deleteStakeholderById(String id) throws EntityNotFoundException, IdNullException {
        var stakeholder = this.findStakeholderById(id);
        stakeholderRepository.delete(stakeholder);
    }
}
