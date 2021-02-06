package com.evatool.requirements.controller;

import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.entity.RequirementGR;
import com.evatool.requirements.entity.RequirementsImpact;
import com.evatool.requirements.repository.RequirementGRRepository;
import com.evatool.requirements.repository.RequirementRepository;
import com.evatool.requirements.repository.RequirementsImpactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("requirements")
public class RequirementGRController {

    @Autowired
    private RequirementGRRepository requirementGRRepository;

    @Autowired
    private RequirementRepository requirementRepository;

    @Autowired
    private RequirementsImpactRepository requirementsImpactRepository;

    @GetMapping("/requirement_gr")
    public List<RequirementGR> getRequirementGrList() {
        return requirementGRRepository.findAll();
    }

    @GetMapping("/requirement_gr/{id}")
    public Optional<RequirementGR> getRequirementGrById(@PathVariable UUID id) {
        return requirementGRRepository.findById(id);
    }

    @GetMapping("/requirement_gr/{id}/impact/requirement")
    public Collection<Requirement> getRequirementGrByImpact(@PathVariable UUID id) {
        Optional<RequirementsImpact> impact = requirementsImpactRepository.findById(id);
        if (impact.get() == null) return null;
        List<Requirement> requirementList = new ArrayList<>();
        requirementGRRepository.findByRequirementsImpact(impact.get()).forEach(e -> requirementList.add(e.getRequirement()));
        return requirementList;
    }

    @GetMapping("/requirement_gr/{id}/requirement/impacts")
    public Collection<RequirementsImpact> getRequirementGRByRequirement(@PathVariable UUID id) {
        Optional<Requirement> requirement = requirementRepository.findById(id);
        if (requirement.get() == null) return null;
        List<RequirementsImpact> requirementsImpactList = new ArrayList<>();
        requirementGRRepository.findByRequirement(requirement.get()).forEach(e -> requirementsImpactList.add(e.getRequirementsImpact()));
        return requirementsImpactList;
    }
}
