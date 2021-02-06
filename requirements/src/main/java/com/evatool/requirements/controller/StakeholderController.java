package com.evatool.requirements.controller;

import com.evatool.requirements.entity.RequirementsStakeholder;
import com.evatool.requirements.repository.RequirementsStakeholderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("requirements")
public class StakeholderController {

    @Autowired
    private RequirementsStakeholderRepository stakeholderRepository;

    @GetMapping("/stakeholder")
    public List<RequirementsStakeholder> getScenarioVariants() {
        return stakeholderRepository.findAll();
    }
}
