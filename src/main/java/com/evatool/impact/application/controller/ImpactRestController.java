package com.evatool.impact.application.controller;

import com.evatool.impact.application.dto.ImpactDto;
import com.evatool.impact.application.service.ImpactService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.evatool.impact.application.controller.UriUtil.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ImpactRestController {

    private static final Logger logger = LoggerFactory.getLogger(ImpactRestController.class);

    private final ImpactService impactService;

    public ImpactRestController(ImpactService impactService) {
        this.impactService = impactService;
    }

    @GetMapping(IMPACTS_ID)
    @ApiOperation(value = "Read impact by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The entity was found"),
            @ApiResponse(code = 404, message = "The entity was not found")})
    public ResponseEntity<EntityModel<ImpactDto>> getImpact(@ApiParam("id") @PathVariable String id) {
        logger.info("GET " + IMPACTS_ID);
        var impactDto = impactService.findImpactById(id);
        return new ResponseEntity<>(getImpactWithLinks(impactDto), HttpStatus.OK);
    }

    @GetMapping(IMPACTS)
    @ApiOperation(value = "Read all impacts")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All entities returned")})
    public ResponseEntity<List<EntityModel<ImpactDto>>> getAllImpacts() {
        logger.info("GET " + IMPACTS);
        return new ResponseEntity<>(getImpactsWithLinks(impactService.getAllImpacts()), HttpStatus.OK);
    }

    @PostMapping(IMPACTS)
    @ApiOperation(value = "Create a new impact")
    @ApiResponses({
            @ApiResponse(code = 201, message = "The entity was inserted"),
            @ApiResponse(code = 400, message = "The entity was invalid"),
            @ApiResponse(code = 404, message = "The entity was not found")})
    public ResponseEntity<EntityModel<ImpactDto>> createImpact(@ApiParam("entity") @RequestBody ImpactDto impactDto) {
        logger.info("POST " + IMPACTS);
        var insertedImpactDto = impactService.createImpact(impactDto);
        return new ResponseEntity<>(getImpactWithLinks(insertedImpactDto), HttpStatus.CREATED);
    }

    @PutMapping(IMPACTS)
    @ApiOperation(value = "Update an impact")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The entity was updated"),
            @ApiResponse(code = 400, message = "The entity was invalid"),
            @ApiResponse(code = 404, message = "The entity was not found")})
    public ResponseEntity<EntityModel<ImpactDto>> updateImpact(@ApiParam("entity") @RequestBody ImpactDto impactDto) {
        logger.info("PUT " + IMPACTS);
        var updatedImpactDto = impactService.updateImpact(impactDto);
        return new ResponseEntity<>(getImpactWithLinks(updatedImpactDto), HttpStatus.OK);
    }

    @DeleteMapping(IMPACTS_ID)
    @ApiOperation(value = "Delete impact by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The entity was deleted"),
            @ApiResponse(code = 404, message = "The entity was not found")})
    public ResponseEntity<Void> deleteImpact(@ApiParam("id") @PathVariable String id) {
        logger.info("DELETE " + IMPACTS_ID);
        impactService.deleteImpactById(id);
        return ResponseEntity.ok().build();
    }

    private EntityModel<ImpactDto> getImpactWithLinks(ImpactDto impactDto) {
        logger.debug("Adding HATEOAS Rest Level 3 links");
        var entityModel = EntityModel.of(impactDto);
        entityModel.add(linkTo(methodOn(ImpactRestController.class).getImpact(impactDto.getId())).withSelfRel());
        entityModel.add(linkTo(methodOn(ImpactRestController.class).updateImpact(impactDto)).withRel(UPDATE_IMPACT));
        entityModel.add(linkTo(methodOn(ImpactRestController.class).deleteImpact(impactDto.getId())).withRel(DELETE_IMPACT));
        entityModel.add(linkTo(ImpactRestController.class).slash(STAKEHOLDERS).slash(impactDto.getStakeholder().getId()).withRel(GET_STAKEHOLDER));
        entityModel.add(linkTo(DimensionRestController.class).slash(DIMENSIONS).slash(impactDto.getDimension().getId()).withRel(GET_DIMENSION));
        return entityModel;
    }

    private List<EntityModel<ImpactDto>> getImpactsWithLinks(List<ImpactDto> impactDtoList) {
        var entityModelList = new ArrayList<EntityModel<ImpactDto>>();
        impactDtoList.forEach(impactDto -> entityModelList.add(getImpactWithLinks(impactDto)));
        return entityModelList;
    }
}
