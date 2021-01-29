package com.evatool.impact.service.impl.rest;

import com.evatool.impact.persistence.entity.Stakeholder;
import com.evatool.impact.persistence.repository.StakeholderRepository;
import com.evatool.impact.service.api.rest.StakeholderRestService;
import com.evatool.impact.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StakeholderRestServiceImpl implements StakeholderRestService {
    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Override
    public Stakeholder getStakeholderById(String id) {
        return stakeholderRepository.findById(id).orElse(null);
    }

    @Override
    public Stakeholder saveStakeholder(Stakeholder stakeholder) {
        return stakeholderRepository.save(stakeholder);
    }

    @Override
    public void deleteStakeholderById(String id) {
        stakeholderRepository.delete(stakeholderRepository.findById(id).orElse(null));
    }

    @Override
    public List<Stakeholder> getAllStakeholders() {
        var stakeholders = stakeholderRepository.findAll();
        return Convert.iterableToList(stakeholders);
    }
}
