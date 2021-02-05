package com.evatool.analysis.api.controller;

import com.evatool.analysis.api.interfaces.StakeholderController;
import com.evatool.analysis.enums.StakeholderRules;
import com.evatool.analysis.model.Stakeholder;
import com.evatool.analysis.repository.StakeholderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("analysis")
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
    @GetMapping("/stakeholder/{id}")
    public Stakeholder getStakeholderById(@PathVariable String id) {
        Optional<Stakeholder> stakeholder = stakeholderRepository.findById(id);
        // stakeholder.get() // for tests
        var dummy = new Stakeholder();
        dummy.setStakeholderName("Dummy");
        dummy.setPriority(1);
        dummy.setStakeholderRule(StakeholderRules.naturalPerson);
        return dummy;
    }

}
