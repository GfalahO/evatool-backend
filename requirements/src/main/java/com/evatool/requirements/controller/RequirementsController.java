package com.evatool.requirements.controller;

import com.evatool.global.events.RequirementCreatedEvent;
import com.evatool.requirements.dto.RequirementDTO;
import com.evatool.requirements.entity.Requirement;

import com.evatool.requirements.events.RequirementEventPublisher;

import com.evatool.requirements.repository.RequirementRepository;
import com.evatool.requirements.service.RequirementDTOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class RequirementsController {

	Logger logger = LoggerFactory.getLogger(RequirementsController.class);

	@Autowired
	private RequirementRepository requirementRepository;

	@Autowired
	private RequirementGRController requirement_grController;

	@Autowired
	private RequirementDTOService dtoService;

	@Autowired
	private RequirementEventPublisher eventPublisher;

	@GetMapping("/requirements")
	public List<RequirementDTO> getRequirementList() {
		logger.info("/requirements");
		List<Requirement> resultList = requirementRepository.findAll();
		eventPublisher.publishEvent(new RequirementCreatedEvent("TEST EVENT"));
		if(resultList.size()==0){return Arrays.asList();}
		return dtoService.findAll(resultList);
	}

	@GetMapping("/requirements/{id}")
	public Optional<Requirement> getRequirementById(@PathVariable UUID id) {
		logger.info("/requirements/[{}]",id);
		return requirementRepository.findById(id);
	}

	@PostMapping("/requirements")
	public Requirement newRequirement(@RequestBody Requirement requirement) {
		logger.info("/requirements");
		//eventPublisher.publishEvent(new RequirementCreatedEvent(null));
		return requirementRepository.save(requirement);
	}

	@PutMapping("/requirements/{id}")
	public Requirement updateRequirement(@RequestBody Requirement requirement) {
		logger.info("/requirements");
		//eventPublisher.publishEvent(new RequirementUpdatedEvent(null));
		return requirementRepository.save(requirement);
	}

	@DeleteMapping("/requirements/{id}")
	public void deleteRequirement(@PathVariable UUID id) {
		logger.info("/requirements");
		requirementRepository.deleteById(id);
		//eventPublisher.publishEvent(new RequirementDeletedEvent(null));

	}
}
