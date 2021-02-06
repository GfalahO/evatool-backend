package com.evatool.requirements.controller;

import com.evatool.requirements.entity.RequirementsImpacts;
import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.entity.RequirementGR;
import com.evatool.requirements.repository.InpactsRepository;
import com.evatool.requirements.repository.RequirementRepository;
import com.evatool.requirements.repository.RequirementGRRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class RequirementGRController {

	@Autowired
	private RequirementGRRepository requirement_grRepository;

	@Autowired
	private RequirementRepository requirementRepository;

	@Autowired
	private InpactsRepository inpactsRepository;

	@GetMapping("/requirement_gr")
	public List<RequirementGR> getRequirement_grList() {
		return requirement_grRepository.findAll();
	}



	@GetMapping("/requirement_gr/{id}")
	public Optional<RequirementGR> getRequirement_grById(@PathVariable UUID id) {
		return requirement_grRepository.findById(id);
	}

	@GetMapping("/requirement_gr/{id}/inpacts/requirement")
	public Collection<Requirement> getRequirement_grByInpact(@PathVariable UUID id) {
		Optional<RequirementsImpacts> inpacts = inpactsRepository.findById(id);
		if(inpacts.get()==null) return null;
		List<Requirement> requirementList = new ArrayList<>();
		requirement_grRepository.findByRequirementsImpacts(inpacts.get()).forEach(e->requirementList.add(e.getRequirement()));
		return requirementList;
	}

	@GetMapping("/requirement_gr/{id}/requirement/inpacts")
	public Collection<RequirementsImpacts> getRequirement_grByRequirement(@PathVariable UUID id) {
		Optional<Requirement> requirement = requirementRepository.findById(id);
		if(requirement.get()==null) return null;
		List<RequirementsImpacts> requirementsImpactsList = new ArrayList<>();
		requirement_grRepository.findByRequirement(requirement.get()).forEach(e-> requirementsImpactsList.add(e.getRequirementsImpacts()));
		return requirementsImpactsList;
	}

	public Collection<RequirementGR> getRequirement_grByRequirementList(Requirement requirement, RequirementsImpacts requirementsImpacts)
	{
		return requirement_grRepository.findByRequirementAndRequirementsImpacts(requirement, requirementsImpacts);
	}
}
