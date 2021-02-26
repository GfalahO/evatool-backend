package com.evatool.requirements.controller;

import com.evatool.global.event.requirements.RequirementCreatedEvent;
import com.evatool.global.event.requirements.RequirementDeletedEvent;
import com.evatool.global.event.requirements.RequirementUpdatedEvent;
import com.evatool.requirements.dto.RequirementDTO;
import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.entity.RequirementsAnalysis;
import com.evatool.requirements.entity.RequirementsVariant;
import com.evatool.requirements.error.exceptions.EntityNotFoundException;
import com.evatool.requirements.events.RequirementEventPublisher;
import com.evatool.requirements.repository.RequirementAnalysisRepository;
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
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Api(description = "API-endpoint RequirementsController!")
public class RequirementsController {

	final Logger logger = LoggerFactory.getLogger(RequirementsController.class);

	@Autowired
	private RequirementRepository requirementRepository;

	@Autowired
	private RequirementsVariantsRepository requirementsVariantsRepository;

	@Autowired
	private RequirementAnalysisRepository requirementAnalysisRepository;

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
	public List<EntityModel<RequirementDTO>> getRequirementList() {
		logger.info("[GET] /requirements");
		List<Requirement> resultList = requirementRepository.findAll();
		if(resultList.size()==0){return Collections.emptyList();}
		return generateLinks(dtoService.findAll(resultList));
	}

	@GetMapping(value = "/requirements",params = "analysisId")
	@ApiOperation(value = "This method returns a list of all Requirements for an Analysis Id.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "All entities returned"),
			@ApiResponse(code = 400, message = "The id was invalid"),
			@ApiResponse(code = 404, message = "The entity was not found")})
	public List<EntityModel<RequirementDTO>> getRequirementListForAnalysis(@RequestParam("analysisId") UUID analysisId) {
		logger.info("[GET] /requirements?analysisId");
		Optional<RequirementsAnalysis> optionalRequirementsAnalysis = requirementAnalysisRepository.findById(analysisId);
		if(optionalRequirementsAnalysis.isEmpty()) throw new EntityNotFoundException(RequirementsAnalysis.class, analysisId);
		Collection<Requirement> resultList = requirementRepository.findByRequirementsAnalysis(optionalRequirementsAnalysis.get());
		if(resultList.size()==0){return Collections.emptyList();}
		return generateLinks(dtoService.findAll(resultList));
	}

	@GetMapping("/requirements/{id}")
	@ApiOperation(value = "Read requirement by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "The entity was found"),
			@ApiResponse(code = 400, message = "The id was invalid"),
			@ApiResponse(code = 404, message = "The entity was not found")})
	public EntityModel<RequirementDTO> getRequirementById(@PathVariable UUID id) {
		logger.info("[GET] /requirements/{id}");
		Optional<Requirement> requirement = requirementRepository.findById(id);
		if(requirement.isEmpty()) throw new EntityNotFoundException(Requirement.class, id);
		return generateLinks(dtoService.findId(requirement.get()));
	}

	@PostMapping("/requirements")
	@ApiOperation(value = "Create requirement")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "The entity was inserted"),
			@ApiResponse(code = 400, message = "The entity was invalid"),
			@ApiResponse(code = 404, message = "The entity was not found")})
	public ResponseEntity<EntityModel<RequirementDTO>> newRequirement(@RequestBody RequirementDTO requirementDTO) {
		logger.info("[POST] /requirements");
		Requirement requirement = requirementPointController.createPoints(dtoService.create(requirementDTO),requirementDTO);
		eventPublisher.publishEvent(new RequirementCreatedEvent(requirement.toJson()));
		return new ResponseEntity<>(getRequirementById(requirement.getId()), HttpStatus.CREATED);
	}

	@PutMapping("/requirements")
	@ApiOperation(value = "Update requirement by ID")
	@ApiResponses({
			@ApiResponse(code = 200, message = "The entity was deleted"),
			@ApiResponse(code = 404, message = "The entity was not found")})
	public EntityModel<RequirementDTO> updateRequirement(@RequestBody RequirementDTO requirementDTO) {
		logger.info("[PUT] /requirements/{id}");
		//eventPublisher.publishEvent(new RequirementUpdatedEvent(null));
		Optional<Requirement> requirementOptional = requirementRepository.findById(requirementDTO.getRootEntityId());
		if(requirementOptional.isEmpty()) throw new EntityNotFoundException(Requirement.class, requirementDTO.getRootEntityId());
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
		requirement = requirementRepository.save(requirement);
		eventPublisher.publishEvent(new RequirementUpdatedEvent(requirement.toString()));
		requirementPointController.updatePoints(requirement,requirementDTO);
		return getRequirementById(requirementDTO.getRootEntityId());
	}

	@DeleteMapping("/requirements/{id}")
	@ApiOperation(value = "Delete requirement by ID")
	@ApiResponses({
			@ApiResponse(code = 200, message = "The entity was updated"),
			@ApiResponse(code = 400, message = "The entity was invalid"),
			@ApiResponse(code = 404, message = "The entity was not found")})
	public ResponseEntity<Void> deleteRequirement(@PathVariable UUID id) {
		logger.info("[DELETE] /requirements/{id}");
		Optional<Requirement> requirementOptional = requirementRepository.findById(id);
		if(requirementOptional.isEmpty()) throw new EntityNotFoundException(Requirement.class, id);
		Requirement requirement = requirementOptional.get();
		requirementPointController.deletePointsForRequirement(requirement);
		requirementRepository.deleteById(id);
		//eventPublisher.publishEvent(new RequirementDeletedEvent(null));
		eventPublisher.publishEvent(new RequirementDeletedEvent(requirement.toString()));
		return ResponseEntity.ok().build();

	}

	private EntityModel<RequirementDTO> generateLinks(RequirementDTO requirementDTO){
		EntityModel<RequirementDTO> requirementDTOEntityModel = EntityModel.of(requirementDTO);
		requirementDTOEntityModel.add(linkTo(methodOn(RequirementsController.class).getRequirementById(requirementDTO.getRootEntityId())).withSelfRel());
		requirementDTOEntityModel.add(linkTo(methodOn(RequirementsController.class).deleteRequirement(requirementDTO.getRootEntityId())).withRel("delete"));
		requirementDTOEntityModel.add(linkTo(methodOn(RequirementsController.class).updateRequirement(requirementDTO)).withRel("update"));
		return requirementDTOEntityModel;
	}

	private List<EntityModel<RequirementDTO>> generateLinks(List<RequirementDTO> requirementDTOList){
		List<EntityModel<RequirementDTO>> returnList = new ArrayList<>();
		requirementDTOList.stream().forEach(e->returnList.add(generateLinks(e)));
		return returnList;
	}
}
