package com.evatool.impact.application.service;

import com.evatool.impact.common.Convert;
import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.common.exception.EntityNullException;
import com.evatool.impact.common.exception.IdNullException;
import com.evatool.impact.domain.entity.ImpactStakeholder;
import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpactStakeholderServiceImpl implements ImpactStakeholderService {

    @Autowired
    private ImpactStakeholderRepository stakeholderRepository;

    @Override
    public ImpactStakeholder findStakeholderById(String id) throws EntityNotFoundException, IdNullException {
        if (id == null) {
            throw new IdNullException(ImpactStakeholder.class);
        }
        var stakeholder = stakeholderRepository.findById(id);
        if (stakeholder.isEmpty()) {
            throw new EntityNotFoundException(ImpactStakeholder.class, id);
        }
        return stakeholder.get();
    }

    @Override
    public List<ImpactStakeholder> getAllStakeholders() {
        var stakeholders = stakeholderRepository.findAll();
        return Convert.iterableToList(stakeholders);
    }

    @Override
    public ImpactStakeholder createStakeholder(ImpactStakeholder stakeholder) {
        if (stakeholder == null) {
            throw new EntityNullException(ImpactStakeholder.class);
        }
        return stakeholderRepository.save(stakeholder);
    }

    @Override
    public ImpactStakeholder updateStakeholder(ImpactStakeholder stakeholder) throws EntityNotFoundException, IdNullException {
        if (stakeholder == null) {
            throw new EntityNullException(ImpactStakeholder.class);
        }
        findStakeholderById(stakeholder.getId());
        return stakeholderRepository.save(stakeholder);
    }

    @Override
    public void deleteStakeholderById(String id) throws EntityNotFoundException, IdNullException {
        var stakeholder = this.findStakeholderById(id);
        stakeholderRepository.delete(stakeholder);
    }

    // TODO: [hbuhl] Write tests?
    @Override
    public void deleteStakeholders() {
        stakeholderRepository.deleteAll();
    }
}
