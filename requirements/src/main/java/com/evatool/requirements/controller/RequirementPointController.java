package com.evatool.requirements.controller;

import com.evatool.requirements.entity.RequirementsImpact;
import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.entity.RequirementPoint;
import com.evatool.requirements.repository.RequirementsImpactsRepository;
import com.evatool.requirements.repository.RequirementRepository;
import com.evatool.requirements.repository.RequirementGRRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class RequirementPointController {

	Logger logger = LoggerFactory.getLogger(RequirementPointController.class);

	@Autowired
	private RequirementGRRepository requirement_grRepository;

	@Autowired
	private RequirementRepository requirementRepository;

	@Autowired
	private RequirementsImpactsRepository requirementsImpactsRepository;

	@GetMapping("/requirement_gr")
	public List<RequirementPoint> getRequirement_grList() {
		logger.info("/requirement_gr");
		return requirement_grRepository.findAll();
	}

	@GetMapping("/requirement_gr/{id}")
	public Optional<RequirementPoint> getRequirement_grById(@PathVariable UUID id) {
		logger.info("/requirement_gr/[{}]",id);
		return requirement_grRepository.findById(id);
	}

	@GetMapping("/requirement_gr/{id}/impacts/requirement")
	public Collection<Requirement> getRequirement_grByImpact(@PathVariable UUID id) {
		logger.info("/requirement_gr/[{}]/inpacts/requirement",id);
		Optional<RequirementsImpact> inpacts = requirementsImpactsRepository.findById(id);
		if(inpacts.isEmpty()) return null;
		List<Requirement> requirementList = new ArrayList<>();
		requirement_grRepository.findByRequirementsImpact(inpacts.get()).forEach(e->requirementList.add(e.getRequirement()));
		return requirementList;
	}

	@GetMapping("/requirement_gr/{id}/requirement/impacts")
	public Collection<RequirementsImpact> getRequirement_grByRequirement(@PathVariable UUID id) {
		logger.info("/requirement_gr/[{}]/requirement/impacts",id);
		Optional<Requirement> requirement = requirementRepository.findById(id);
		if(requirement.isEmpty()) return null;
		List<RequirementsImpact> requirementsImpactList = new ArrayList<>();
		requirement_grRepository.findByRequirement(requirement.get()).forEach(e-> requirementsImpactList.add(e.getRequirementsImpact()));
		return requirementsImpactList;
	}

	public Collection<RequirementPoint> getRequirement_grByRequirementList(Requirement requirement, RequirementsImpact requirementsImpact)
	{
		logger.info("getRequirement_grByRequirementList [{}]",requirement, requirementsImpact);
		return requirement_grRepository.findByRequirementAndRequirementsImpact(requirement, requirementsImpact);
	}
}
