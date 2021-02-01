package com.evatool.impact.service.impl.rest;

import com.evatool.impact.exception.EntityNullException;
import com.evatool.impact.persistence.entity.Stakeholder;
import com.evatool.impact.persistence.repository.StakeholderRepository;
import com.evatool.impact.exception.IdNullException;
import com.evatool.impact.service.api.rest.StakeholderRestService;
import com.evatool.impact.exception.EntityNotFoundException;
import com.evatool.impact.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StakeholderRestServiceImpl implements StakeholderRestService {
    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Override
    public Stakeholder getStakeholderById(String id) throws EntityNotFoundException, IdNullException {
        if (id == null || id.equals("null")) { // DELETE Rest call requires id.equals.
            throw new IdNullException(Stakeholder.class);
        }
        // TODO: Throw new exception: IdInvalidException?
        var stakeholder = stakeholderRepository.findById(id).orElse(null);
        if (stakeholder == null) {
            throw new EntityNotFoundException(Stakeholder.class, id);
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
        if (stakeholder == null) {
            throw new EntityNullException(Stakeholder.class);
        }
        return stakeholderRepository.save(stakeholder);
    }

    @Override
    public Stakeholder updateStakeholder(Stakeholder stakeholder) throws EntityNotFoundException, IdNullException {
        if (stakeholder == null) {
            throw new EntityNullException(Stakeholder.class);
        }
        getStakeholderById(stakeholder.getId());
        return stakeholderRepository.save(stakeholder);
    }

    @Override
    public void deleteStakeholderById(String id) throws EntityNotFoundException, IdNullException {
        var stakeholder = this.getStakeholderById(id);
        stakeholderRepository.delete(stakeholder);
    }
}
