package com.FAE.EVATool.Analysis.API.Controller;

import com.FAE.EVATool.Analysis.API.Interfaces.StakeholderController;
import com.FAE.EVATool.Analysis.Enum.StakeholderRules;
import com.FAE.EVATool.Analysis.Model.Analysis;
import com.FAE.EVATool.Analysis.Model.Stakeholder;
import com.FAE.EVATool.Analysis.Repository.StakeholderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
public class StakeholderControllerImpl implements StakeholderController {

    @Autowired
    StakeholderRepository stakeholderRepository;

    @Override
    @PostMapping("/addStakeholder")
    public void addStakeholder(Stakeholder stakeholder) {
        stakeholder.setStakeholderName("test");
        stakeholder.setPriority(1);
        stakeholder.setStakeholderRule(StakeholderRules.naturalPerson);
    }

    @Override
    @GetMapping("/getStakeholderById/{id}")
    public Stakeholder getStakeholderById(@PathVariable String stakeholderId) {
        Optional<Stakeholder> currentStakeholder = stakeholderRepository.findById(stakeholderId);
        return currentStakeholder.get();
    }

}
