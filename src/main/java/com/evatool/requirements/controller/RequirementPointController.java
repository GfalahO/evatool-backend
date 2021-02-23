package com.evatool.requirements.controller;

import com.evatool.requirements.dto.RequirementDTO;
import com.evatool.requirements.entity.RequirementsImpact;
import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.entity.RequirementPoint;
import com.evatool.requirements.entity.RequirementsVariant;
import com.evatool.requirements.repository.RequirementsImpactsRepository;
import com.evatool.requirements.repository.RequirementRepository;
import com.evatool.requirements.repository.RequirementPointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class RequirementPointController {

	Logger logger = LoggerFactory.getLogger(RequirementPointController.class);

	@Autowired
	private RequirementPointRepository requirementPointRepository;

	@Autowired
	private RequirementRepository requirementRepository;

	@Autowired
	private RequirementsImpactsRepository requirementsImpactsRepository;

/*
	public Optional<RequirementPoint> getRequirement_grById(@PathVariable UUID id) {
		logger.info("/requirement_gr/[{}]",id);
		return requirementPointRepository.findById(id);
	}
*/

	public void newRequirementPoint(Collection<RequirementPoint> requirementPointList) {
		logger.info("/requirement_gr/[{}]",requirementPointList);
		requirementPointRepository.saveAll(requirementPointList);
	}

	public RequirementPoint updateRequirementPoint(RequirementPoint requirementPoint) {
		logger.info("/requirement_gr/[{}]",requirementPoint);
		return requirementPointRepository.save(requirementPoint);
	}

	public void deleteRequirementPoint(RequirementPoint requirementPoint) {
		logger.info("/requirement_gr/[{}]",requirementPoint);
		requirementPointRepository.delete(requirementPoint);
	}

/*
	public Collection<Requirement> getRequirement_grByImpact(@PathVariable UUID id) {
		logger.info("/requirement_gr/[{}]/inpacts/requirement",id);
		Optional<RequirementsImpact> inpacts = requirementsImpactsRepository.findById(id);
		if(inpacts.isEmpty()) return null;
		List<Requirement> requirementList = new ArrayList<>();
		requirement_grRepository.findByRequirementsImpact(inpacts.get()).forEach(e->requirementList.add(e.getRequirement()));
		return requirementList;
	}
*/

	public Collection<RequirementsImpact> getRequirement_grByRequirement(@PathVariable UUID id) {
		logger.info("/requirement_gr/[{}]/requirement/impacts",id);
		Optional<Requirement> requirement = requirementRepository.findById(id);
		if(requirement.isEmpty()) return null;
		List<RequirementsImpact> requirementsImpactList = new ArrayList<>();
		requirementPointRepository.findByRequirement(requirement.get()).forEach(e-> requirementsImpactList.add(e.getRequirementsImpact()));
		return requirementsImpactList;
	}

	public RequirementPoint getRequirement_grByRequirementList(Requirement requirement, RequirementsImpact requirementsImpact)
	{
		logger.info("getRequirement_grByRequirementList [{}]",requirement, requirementsImpact);
		return requirementPointRepository.findByRequirementAndRequirementsImpact(requirement, requirementsImpact);
	}

	public void createPoints(Requirement requirement, RequirementDTO requirementDTO) {
		Collection<RequirementPoint> requirementPointCollection = new ArrayList<>();
		for( Map.Entry<UUID, Integer> entry:requirementDTO.getRequirementImpactPoints().entrySet()) {
			RequirementPoint requirementPoint = new RequirementPoint();
			requirementPoint.setRequirement(requirement);
			requirementPoint.setPoints(entry.getValue());
			Optional<RequirementsImpact> requirementsImpact = requirementsImpactsRepository.findById(entry.getKey());
			requirementPoint.setRequirementsImpact(requirementsImpact.get());
			requirementPointCollection.add(requirementPoint);
		}
		this.newRequirementPoint(requirementPointCollection);
	}

	public void updatePoints(Requirement requirement, RequirementDTO requirementDTO) {
		Collection<RequirementPoint> requirementPointCollectionFromEntity = requirementPointRepository.findByRequirement(requirement);
		Map<UUID, Integer> requirementImpactPointsMap=requirementDTO.getRequirementImpactPoints();
		for (RequirementPoint requirementPoint:requirementPointCollectionFromEntity){
			if(requirementImpactPointsMap.get(requirementPoint.getId())==null) {
				this.deleteRequirementPoint(requirementPoint);
			}
			else if(requirementImpactPointsMap.get(requirementPoint.getId())!=requirementPoint.getPoints()){
				requirementPoint.setPoints(requirementImpactPointsMap.get(requirementPoint.getId()));
				this.updateRequirementPoint(requirementPoint);
				requirementDTO.getRequirementImpactPoints().remove(requirementPoint.getId());
			}
		}
		this.createPoints(requirement,requirementDTO);
	}

	public void deletePointsForRequirement(Requirement requirement) {
		requirementPointRepository.deleteAllByRequirement(requirement);
	}
}
