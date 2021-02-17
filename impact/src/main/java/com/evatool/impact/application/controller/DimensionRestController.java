package com.evatool.impact.application.controller;

import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.application.service.DimensionService;
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

@RestController
public class DimensionRestController {

    private static final Logger logger = LoggerFactory.getLogger(DimensionRestController.class);

    private final DimensionService dimensionService;

    public DimensionRestController(DimensionService dimensionService) {
        this.dimensionService = dimensionService;
    }

    @GetMapping(DIMENSIONS_ID)
    @ApiOperation(value = "Read dimension by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The entity was found"),
            @ApiResponse(code = 404, message = "The entity was not found")})
    public ResponseEntity<DimensionDto> getDimension(@ApiParam("Id") @PathVariable String id) {
        logger.info(DIMENSIONS_ID);
        var dimensionDto = dimensionService.findDimensionById(id);
        var entityModel = EntityModel.of(dimensionDto);
        addLinks(entityModel);
        return new ResponseEntity(entityModel, HttpStatus.OK);
    }

    @GetMapping(DIMENSIONS)
    @ApiOperation(value = "Read all dimensions")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All entities returned")})
    public ResponseEntity<List<DimensionDto>> getAllDimensions(@ApiParam(value = "Type", required = false) @RequestParam(value = "type", required = false) String type) {
        logger.info(DIMENSIONS);
        List<DimensionDto> dimensionDtoList;
        if (type != null) {
            dimensionDtoList = dimensionService.findDimensionsByType(type);
        } else {
            dimensionDtoList = dimensionService.getAllDimensions();
        }
        var entityModelList = new ArrayList<EntityModel>();
        dimensionDtoList.forEach(s -> entityModelList.add(EntityModel.of(s)));
        entityModelList.forEach(this::addLinks);
        return new ResponseEntity(entityModelList, HttpStatus.OK);
    }

    @PostMapping(DIMENSIONS)
    @ApiOperation(value = "Create a new dimension")
    @ApiResponses({
            @ApiResponse(code = 201, message = "The entity was inserted"),
            @ApiResponse(code = 400, message = "The entity was invalid"),
            @ApiResponse(code = 404, message = "The entity was not found")})
    public ResponseEntity<DimensionDto> createDimension(@ApiParam("Entity") @RequestBody DimensionDto dimensionDto) {
        logger.info(DIMENSIONS);
        var insertedDimensionDto = dimensionService.createDimension(dimensionDto);
        var entityModel = EntityModel.of(insertedDimensionDto);
        addLinks(entityModel);
        return new ResponseEntity(entityModel, HttpStatus.CREATED);
    }

    @PutMapping(DIMENSIONS)
    @ApiOperation(value = "Update a dimension")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The entity was updated"),
            @ApiResponse(code = 400, message = "The entity was invalid"),
            @ApiResponse(code = 404, message = "The entity was not found")})
    public ResponseEntity<DimensionDto> updateDimension(@ApiParam("Entity") @RequestBody DimensionDto dimensionDto) {
        logger.info(DIMENSIONS);
        var updatedDimensionDto = dimensionService.updateDimension(dimensionDto);
        var entityModel = EntityModel.of(updatedDimensionDto);
        addLinks(entityModel);
        return new ResponseEntity(entityModel, HttpStatus.OK);
    }

    @DeleteMapping(DIMENSIONS_ID)
    @ApiOperation(value = "Delete dimension by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The entity was deleted"),
            @ApiResponse(code = 404, message = "The entity was not found")})
    public ResponseEntity<Void> deleteDimension(@ApiParam("Id") @PathVariable String id) {
        logger.info(DIMENSIONS_ID);
        dimensionService.deleteDimensionById(id);
        return ResponseEntity.ok().build();
    }

    private void addLinks(EntityModel<DimensionDto> entityModel) {
        logger.debug("Adding HATEOAS Rest Level 3 links.");
        entityModel.add(linkTo(DimensionRestController.class).slash(DIMENSIONS).withRel(GET_DIMENSIONS));
        entityModel.add(linkTo(DimensionRestController.class).slash(_DIMENSIONS).withRel(CREATE_DIMENSIONS));
        entityModel.add(linkTo(DimensionRestController.class).slash(_DIMENSIONS).withRel(UPDATE_DIMENSIONS));
        if (entityModel.getContent().getId() != null) {
            entityModel.add(linkTo(DimensionRestController.class).slash(_DIMENSIONS).slash(entityModel.getContent().getId()).withSelfRel());
            entityModel.add(linkTo(DimensionRestController.class).slash(_DIMENSIONS).slash(entityModel.getContent().getId()).withRel(DELETE_DIMENSIONS));
        }
    }
}
