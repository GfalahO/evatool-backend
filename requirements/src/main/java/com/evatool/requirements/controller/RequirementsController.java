package com.evatool.requirements.controller;

import com.evatool.requirements.dto.RequirementDTO;
import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.repository.RequirementRepository;
import com.evatool.requirements.service.RequirementDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class RequirementsController {

	@Autowired
	private RequirementRepository requirementRepository;

	@Autowired
	private RequirementGRController requirement_grController;

	@Autowired
	RequirementDTOService dtoService;

	@GetMapping("/requirements")
	public List<RequirementDTO> getRequirementList() {
		List<Requirement> resultList = requirementRepository.findAll();
		if(resultList.size()==0){return Arrays.asList();}
		return dtoService.findAll(resultList);
	}

	@GetMapping("/requirements/{id}")
	public Optional<Requirement> getRequirementById(@PathVariable UUID id) {
		return requirementRepository.findById(id);
	}


	@PostMapping("/requirements")
	Requirement newRequirement(@RequestBody Requirement requirement) {
		return requirementRepository.save(requirement);
	}

	@PutMapping("/requirements")
	Requirement updateRequirement(@RequestBody Requirement requirement) {
		return requirementRepository.save(requirement);
	}

	@DeleteMapping("/requirements")
	void deleteRequirement(@RequestBody Requirement requirement) {
		requirementRepository.delete(requirement);
	}
}
