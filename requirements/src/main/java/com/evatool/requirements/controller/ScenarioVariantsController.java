package com.evatool.requirements.controller;

import com.evatool.requirements.entity.ScenarioVariants;
import com.evatool.requirements.repository.ScenarioRepository;
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
public class ScenarioVariantsController {

    @Autowired
    private ScenarioRepository scenarioRepository;

    @GetMapping("/variants")
    public List<ScenarioVariants> getScenarioVariants() {
        return scenarioRepository.findAll();
    }

    @GetMapping("/variant/{id}")
    public Optional<ScenarioVariants> getScenarioVariantById(@PathVariable UUID id) {
        return scenarioRepository.findById(id);
    }

}
