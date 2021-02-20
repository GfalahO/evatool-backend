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
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public ResponseEntity<EntityModel<DimensionDto>> getDimension(@ApiParam("id") @PathVariable String id) {
        logger.info("GET " + DIMENSIONS_ID);
        var dimensionDto = dimensionService.findDimensionById(id);
        return new ResponseEntity<>(getDimensionWithLinks(dimensionDto), HttpStatus.OK);
    }

    @GetMapping(DIMENSIONS)
    @ApiOperation(value = "Read all dimensions")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All entities returned")})
    public ResponseEntity<List<EntityModel<DimensionDto>>> getAllDimensions(@ApiParam(value = "type", required = false) @RequestParam(value = "type", required = false) String type) {
        List<DimensionDto> dimensionDtoList;
        if (type == null) {
            logger.info("GET " + DIMENSIONS);
            dimensionDtoList = dimensionService.getAllDimensions();
        } else {
            logger.info("GET " + DIMENSIONS + "?type={}", type);
            dimensionDtoList = dimensionService.findDimensionsByType(type);
        }
        return new ResponseEntity<>(getDimensionsWithLinks(dimensionDtoList), HttpStatus.OK);
    }

    @PostMapping(DIMENSIONS)
    @ApiOperation(value = "Create a new dimension")
    @ApiResponses({
            @ApiResponse(code = 201, message = "The entity was inserted"),
            @ApiResponse(code = 400, message = "The entity was invalid"),
            @ApiResponse(code = 404, message = "The entity was not found")})
    public ResponseEntity<EntityModel<DimensionDto>> createDimension(@ApiParam("entity") @RequestBody DimensionDto dimensionDto) {
        logger.info("POST " + DIMENSIONS);
        var insertedDimensionDto = dimensionService.createDimension(dimensionDto);
        return new ResponseEntity<>(getDimensionWithLinks(insertedDimensionDto), HttpStatus.CREATED);
    }

    @PutMapping(DIMENSIONS)
    @ApiOperation(value = "Update a dimension")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The entity was updated"),
            @ApiResponse(code = 400, message = "The entity was invalid"),
            @ApiResponse(code = 404, message = "The entity was not found")})
    public ResponseEntity<EntityModel<DimensionDto>> updateDimension(@ApiParam("entity") @RequestBody DimensionDto dimensionDto) {
        logger.info("PUT " + DIMENSIONS);
        var updatedDimensionDto = dimensionService.updateDimension(dimensionDto);
        return new ResponseEntity<>(getDimensionWithLinks(updatedDimensionDto), HttpStatus.OK);
    }

    @DeleteMapping(DIMENSIONS_ID)
    @ApiOperation(value = "Delete dimension by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The entity was deleted"),
            @ApiResponse(code = 404, message = "The entity was not found")})
    public ResponseEntity<Void> deleteDimension(@ApiParam("id") @PathVariable String id) {
        logger.info("DELETE " + DIMENSIONS_ID);
        dimensionService.deleteDimensionById(id);
        return ResponseEntity.ok().build();
    }

    private EntityModel<DimensionDto> getDimensionWithLinks(DimensionDto dimensionDto) {
        logger.debug("Adding HATEOAS Rest Level 3 links");
        var entityModel = EntityModel.of(dimensionDto);
        entityModel.add(linkTo(methodOn(DimensionRestController.class).getDimension(dimensionDto.getId())).withSelfRel());
        entityModel.add(linkTo(methodOn(DimensionRestController.class).updateDimension(dimensionDto)).withRel(UPDATE_DIMENSION));
        entityModel.add(linkTo(methodOn(DimensionRestController.class).deleteDimension(dimensionDto.getId())).withRel(DELETE_DIMENSION));
        return entityModel;
    }

    private List<EntityModel<DimensionDto>> getDimensionsWithLinks(List<DimensionDto> dimensionDtoList) {
        var entityModelList = new ArrayList<EntityModel<DimensionDto>>();
        dimensionDtoList.forEach(dimensionDto -> entityModelList.add(getDimensionWithLinks(dimensionDto)));
        return entityModelList;
    }
}
