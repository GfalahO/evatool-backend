package com.evatool.requirements.controller;

import com.evatool.requirements.entity.RequirementsImpact;
import com.evatool.requirements.repository.RequirementsImpactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("requirements")
public class ImpactController {

    @Autowired
    private RequirementsImpactRepository requirementsImpactRepository;

    @GetMapping("/impacts")
    public List<RequirementsImpact> getRequirementsImpacts() {
        return requirementsImpactRepository.findAll();
    }

    @GetMapping("/impact/{id}")
    public Optional<RequirementsImpact> getRequirementsImpactById(@PathVariable UUID id) {
        return requirementsImpactRepository.findById(id);
    }
}
