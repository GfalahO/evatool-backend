package com.evatool.requirements.controller;

import com.evatool.requirements.entity.Inpacts;
import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.entity.Requirement_GR;
import com.evatool.requirements.repository.InpactsRepository;
import com.evatool.requirements.repository.RequirementRepository;
import com.evatool.requirements.repository.Requirement_GRRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("requirements")
public class Requirement_GRController {

    @Autowired
    private Requirement_GRRepository requirement_grRepository;

    @Autowired
    private RequirementRepository requirementRepository;

    @Autowired
    private InpactsRepository inpactsRepository;

    @GetMapping("/requirement_gr")
    public List<Requirement_GR> getRequirement_grList() {
        return requirement_grRepository.findAll();
    }

    @GetMapping("/requirement_gr/{id}")
    public Optional<Requirement_GR> getRequirement_grById(@PathVariable UUID id) {
        return requirement_grRepository.findById(id);
    }

    @GetMapping("/requirement_gr/{id}/inpacts/requirement")
    public Collection<Requirement> getRequirement_grByInpact(@PathVariable UUID id) {
        Optional<Inpacts> inpacts = inpactsRepository.findById(id);
        if (inpacts.get() == null) return null;
        List<Requirement> requirementList = new ArrayList<>();
        requirement_grRepository.findByInpacts(inpacts.get()).forEach(e -> requirementList.add(e.getRequirement()));
        return requirementList;
    }

    @GetMapping("/requirement_gr/{id}/requirement/inpacts")
    public Collection<Inpacts> getRequirement_grByRequirement(@PathVariable UUID id) {
        Optional<Requirement> requirement = requirementRepository.findById(id);
        if (requirement.get() == null) return null;
        List<Inpacts> inpactsList = new ArrayList<>();
        requirement_grRepository.findByRequirement(requirement.get()).forEach(e -> inpactsList.add(e.getInpacts()));
        return inpactsList;
    }
}
