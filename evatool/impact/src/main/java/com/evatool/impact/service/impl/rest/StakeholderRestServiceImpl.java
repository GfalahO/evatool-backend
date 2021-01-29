package com.evatool.impact.service.impl.rest;

import com.evatool.impact.common.controller.StakeholderRestController;
import com.evatool.impact.persistence.entity.Stakeholder;
import com.evatool.impact.persistence.repository.StakeholderRepository;
import com.evatool.impact.service.api.rest.StakeholderRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Arrays;
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
    public List<Stakeholder> getAllStakeholders() {
        var stakeholders = stakeholderRepository.findAll();
        var stakeholdersList = new ArrayList();
        for (var stakeholder : stakeholders) {
            stakeholdersList.add(stakeholder);
        }
        return stakeholdersList;
    }
}
