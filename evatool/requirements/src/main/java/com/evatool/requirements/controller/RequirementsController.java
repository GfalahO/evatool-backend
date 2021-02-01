package com.evatool.requirements.controller;

import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.repository.RequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class RequirementsController {

	@Autowired
	private RequirementRepository requirementRepository;

	@Autowired
	private Requirement_GRController requirement_grController;

	@GetMapping("/requirement")
	public List<Requirement> getRequirementList() {
		return requirementRepository.findAll();
	}

	@GetMapping("/requirement/{id}")
	public Optional<Requirement> getRequirementById(@PathVariable UUID id) {
		return requirementRepository.findById(id);
	}


	@PostMapping("/requirement")
	Requirement newRequirement(@RequestBody Requirement requirement) {
		return requirementRepository.save(requirement);
	}

	@PutMapping("/requirement")
	Requirement updateRequirement(@RequestBody Requirement requirement) {
		return requirementRepository.save(requirement);
	}
}
