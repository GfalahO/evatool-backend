package com.evatool.impact.application.controller;

import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.application.service.DimensionService;
import com.evatool.impact.domain.entity.Dimension;

import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    @ApiOperation(value = "Get Dimension by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")})
    public ResponseEntity<EntityModel<DimensionDto>> getDimension(@ApiParam("Dimension ID") @Valid @PathVariable UUID id) {
        logger.info("GET " + DIMENSIONS_ID);
        var dimensionDto = dimensionService.findDimensionById(id);
        return new ResponseEntity<>(getDimensionWithLinks(dimensionDto), HttpStatus.OK);
    }

    @GetMapping(DIMENSIONS)
    @ApiOperation(value = "Get all Dimensions")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request")})
    public ResponseEntity<List<EntityModel<DimensionDto>>> getAllDimensions(@ApiParam(value = "Dimension Type") @Valid @RequestParam(value = "type", required = false) Dimension.Type type) {
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
    @ApiOperation(value = "Create a new Dimension")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 422, message = "Unprocessable")})
    public ResponseEntity<EntityModel<DimensionDto>> createDimension(@ApiParam("Dimension") @Valid @RequestBody DimensionDto dimensionDto) {
        logger.info("POST " + DIMENSIONS);
        var insertedDimensionDto = dimensionService.createDimension(dimensionDto);
        return new ResponseEntity<>(getDimensionWithLinks(insertedDimensionDto), HttpStatus.CREATED);
    }

    @PutMapping(DIMENSIONS)
    @ApiOperation(value = "Update a Dimension")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Updated"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 422, message = "Unprocessable")})
    public ResponseEntity<EntityModel<DimensionDto>> updateDimension(@ApiParam("Dimension") @Valid @RequestBody DimensionDto dimensionDto) {
        logger.info("PUT " + DIMENSIONS);
        var updatedDimensionDto = dimensionService.updateDimension(dimensionDto);
        return new ResponseEntity<>(getDimensionWithLinks(updatedDimensionDto), HttpStatus.OK);
    }

    @DeleteMapping(DIMENSIONS_ID)
    @ApiOperation(value = "Delete Dimension by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Deleted"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")})
    public ResponseEntity<Void> deleteDimension(@ApiParam("Dimension ID") @Valid @PathVariable UUID id) {
        logger.info("DELETE " + DIMENSIONS_ID);
        dimensionService.deleteDimensionById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(DIMENSION_TYPES)
    @ApiOperation(value = "Get all Dimension Types")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")})
    public ResponseEntity<List<Dimension.Type>> getDimensionTypes() {
        logger.info("GET " + DIMENSION_TYPES);
        return new ResponseEntity<>(dimensionService.getAllDimensionTypes(), HttpStatus.OK);
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
