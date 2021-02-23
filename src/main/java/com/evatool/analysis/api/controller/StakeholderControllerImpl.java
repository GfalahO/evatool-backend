package com.evatool.analysis.api.controller;

import com.evatool.analysis.api.interfaces.StakeholderController;
import com.evatool.analysis.enums.StakeholderLevel;
import com.evatool.analysis.model.Analysis;
import com.evatool.analysis.model.Stakeholder;
import com.evatool.analysis.repository.StakeholderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class StakeholderControllerImpl implements StakeholderController {

    @Autowired
    private StakeholderRepository stakeholderRepository;
    Logger logger = LoggerFactory.getLogger(StakeholderControllerImpl.class);


    @Override
    public List<Stakeholder> getStakeholderList() {
        return (List<Stakeholder>) stakeholderRepository.findAll();
    }

    @Override
    public Optional<Stakeholder> getStakeholderById(UUID id) {
        return stakeholderRepository.findById(id);
    }

    @Override
    public Stakeholder addStakeholder(Stakeholder stakeholder) {
        logger.info("/addStakeholder");
        return stakeholderRepository.save(stakeholder);
    }

    @Override
    public Stakeholder updateStakeholder(Stakeholder stakeholder) {
        logger.info("/stakeholder");
        return stakeholderRepository.save(stakeholder);
    }

    @Override
    public void deleteStakeholder(UUID id) {
        logger.info("/stakeholder");
        stakeholderRepository.deleteById(id);
    }


}
