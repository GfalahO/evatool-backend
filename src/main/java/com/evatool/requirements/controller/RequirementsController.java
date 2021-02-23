package com.evatool.requirements.controller;

import com.evatool.requirements.dto.RequirementDTO;
import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.entity.RequirementsVariant;
import com.evatool.requirements.events.RequirementEventPublisher;
import com.evatool.requirements.repository.RequirementRepository;
import com.evatool.requirements.repository.RequirementsVariantsRepository;
import com.evatool.requirements.service.RequirementDTOService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@Api(description = "API-endpoint RequirementsController!")
public class RequirementsController {

	Logger logger = LoggerFactory.getLogger(RequirementsController.class);

	@Autowired
	private RequirementRepository requirementRepository;

	@Autowired
	private RequirementsVariantsRepository requirementsVariantsRepository;

	@Autowired
	private RequirementPointController requirementPointController;

	@Autowired
	private RequirementDTOService dtoService;

	@Autowired
	private RequirementEventPublisher eventPublisher;


	@GetMapping("/requirements")
	@ApiOperation(value = "This method returns a list of all Requirements.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "All entities returned")})
	public List<RequirementDTO> getRequirementList() {
		logger.info("/requirements");
		List<Requirement> resultList = requirementRepository.findAll();
		if(resultList.size()==0){return Arrays.asList();}
		return dtoService.findAll(resultList);
	}

	@GetMapping("/requirements/{id}")
	@ApiOperation(value = "Read requirement by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "The entity was found"),
			@ApiResponse(code = 400, message = "The id was invalid"),
			@ApiResponse(code = 404, message = "The entity was not found")})
	public RequirementDTO getRequirementById(@PathVariable UUID id) {
		logger.info("/requirements/[{}]",id);
		Optional<Requirement> requirement = requirementRepository.findById(id);
		if(requirement.isEmpty()) return null;
		return dtoService.findId(requirement.get());
	}

	@PostMapping("/requirements")
	@ApiOperation(value = "Create requirement")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "The entity was inserted"),
			@ApiResponse(code = 400, message = "The entity was invalid"),
			@ApiResponse(code = 404, message = "The entity was not found")})
	public RequirementDTO newRequirement(@RequestBody RequirementDTO requirementDTO) {
		logger.info("/requirements");
		//eventPublisher.publishEvent(new RequirementCreatedEvent(null));
		Requirement requirement = requirementRepository.save(dtoService.create(requirementDTO));
		requirementPointController.createPoints(requirement,requirementDTO);
		return getRequirementById(requirement.getId());
	}

	@PutMapping("/requirements/{id}")
	@ApiOperation(value = "Update requirement by ID")
	@ApiResponses({
			@ApiResponse(code = 200, message = "The entity was deleted"),
			@ApiResponse(code = 404, message = "The entity was not found")})
	public RequirementDTO updateRequirement(@RequestBody RequirementDTO requirementDTO) {
		logger.info("/requirements");
		//eventPublisher.publishEvent(new RequirementUpdatedEvent(null));
		Optional<Requirement> requirementOptional = requirementRepository.findById(requirementDTO.getRootEntityId());
		Requirement requirement = requirementOptional.get();
		requirement.setDescription(requirementDTO.getRequirementDescription());
		requirement.setTitle(requirementDTO.getRequirementTitle());
		Collection<RequirementsVariant> requirementsVariantCollectionDTO = requirementsVariantsRepository.findAllById(requirementDTO.getVariantsTitle().keySet());
		//Remove the Variants which are removed
		Collection<RequirementsVariant> newCollection = requirement.getVariants().stream().filter(e-> requirementsVariantCollectionDTO.contains(e)).collect(Collectors.toList());
		//Add the new Variants
		requirementsVariantCollectionDTO.stream().forEach(e->{
			if(!newCollection.contains(e)){
				newCollection.add(e);
			}
		});
		requirement.setVariants(newCollection);
		requirementRepository.save(requirement);

		requirementPointController.updatePoints(requirement,requirementDTO);
		return getRequirementById(requirementDTO.getRootEntityId());
	}

	@DeleteMapping("/requirements/{id}")
	@ApiOperation(value = "Delete requirement by ID")
	@ApiResponses({
			@ApiResponse(code = 200, message = "The entity was updated"),
			@ApiResponse(code = 400, message = "The entity was invalid"),
			@ApiResponse(code = 404, message = "The entity was not found")})
	public void deleteRequirement(@PathVariable UUID id) {
		logger.info("/requirements");
		Optional<Requirement> requirementOptional = requirementRepository.findById(id);
		Requirement requirement = requirementOptional.get();
		requirementPointController.deletePointsForRequirement(requirement);
		requirementRepository.deleteById(id);
		//eventPublisher.publishEvent(new RequirementDeletedEvent(null));

	}
}
