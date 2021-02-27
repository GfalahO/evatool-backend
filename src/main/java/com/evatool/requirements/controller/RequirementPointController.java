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
		logger.debug("/requirement_gr/[{}]",id);
		return requirementPointRepository.findById(id);
	}
*/

	public void newRequirementPoint(Collection<RequirementPoint> requirementPointList) {
		logger.debug("newRequirementPoint [{}]",requirementPointList);
		requirementPointRepository.saveAll(requirementPointList);
	}

	public RequirementPoint updateRequirementPoint(RequirementPoint requirementPoint) {
		logger.debug("updateRequirementPoint [{}]",requirementPoint);
		return requirementPointRepository.save(requirementPoint);
	}

	public void deleteRequirementPoint(RequirementPoint requirementPoint) {
		logger.debug("deleteRequirementPoint [{}]",requirementPoint);
		requirementPointRepository.delete(requirementPoint);
	}

	public Collection<RequirementsImpact> getRequirementImpactByRequirement(@PathVariable UUID id) {
		logger.debug("getRequirementImpactByRequirement [{}]",id);
		Optional<Requirement> requirement = requirementRepository.findById(id);
		if(requirement.isEmpty()) return null;
		List<RequirementsImpact> requirementsImpactList = new ArrayList<>();
		requirementPointRepository.findByRequirement(requirement.get()).forEach(e-> requirementsImpactList.add(e.getRequirementsImpact()));
		return requirementsImpactList;
	}

	public RequirementPoint getRequirementPointByRequirementAndRequirementsImpact(Requirement requirement, RequirementsImpact requirementsImpact)
	{
		logger.debug("getRequirementPointByRequirementAndRequirementsImpact [{}]",requirement, requirementsImpact);
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
		logger.debug("updatePoints [{}]",requirement,requirementDTO);
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
		logger.debug("deletePointsForRequirement [{}]",requirement);
		Collection<RequirementPoint> collection = requirementPointRepository.findByRequirement(requirement);
		requirementPointRepository.deleteAll(collection);
	}
}
