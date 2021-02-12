package com.evatool.impact.application.controller;

import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.application.service.DimensionService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.evatool.impact.application.controller.util.DimensionRest.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(DIMENSION_REST_CONTROLLER_MAPPING)
@Api(DIMENSION_REST_CONTROLLER_MAPPING)
public class DimensionRestController {

    private static final Logger logger = LoggerFactory.getLogger(DimensionRestController.class);

    private final DimensionService dimensionService;

    public DimensionRestController(DimensionService dimensionService) {
        this.dimensionService = dimensionService;
    }

    @GetMapping(GET_DIMENSION_MAPPING)
    @ApiOperation(value = "Return a Dimension by its id", response = DimensionDto.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "The entity was found"),
            @ApiResponse(code = 404, message = "The entity was not found")})
    public ResponseEntity<DimensionDto> getDimension(@ApiParam("id") @PathVariable String id)  {
        var dimensionDto = dimensionService.findDimensionById(id);
        var entityModel = new EntityModel<>(dimensionDto);
        addLinks(entityModel);
        return new ResponseEntity(entityModel, HttpStatus.OK);
    }

    @GetMapping(GET_DIMENSIONS_MAPPING)
    @ApiOperation(value = "Return all Dimensions", response = List.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "All entities returned")})
    public ResponseEntity<List<DimensionDto>> getAllDimensions() {
        var dimensionDtoList = dimensionService.getAllDimensions();
        var entityModelList = new ArrayList<EntityModel>();
        dimensionDtoList.forEach(s -> entityModelList.add(new EntityModel<>(s)));
        entityModelList.forEach(this::addLinks);
        return new ResponseEntity(entityModelList, HttpStatus.OK);
    }

    @PostMapping(POST_DIMENSION_MAPPING)
    @ApiOperation(value = "Insert a new Dimension", response = DimensionDto.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "The entity was inserted"),
            @ApiResponse(code = 400, message = "The entity was invalid"),
            @ApiResponse(code = 404, message = "The entity was not found")})
    public ResponseEntity<DimensionDto> createDimension(@ApiParam("Entity") @RequestBody DimensionDto dimensionDto)  {
        var insertedDimensionDto = dimensionService.createDimension(dimensionDto);
        var entityModel = new EntityModel<>(insertedDimensionDto);
        addLinks(entityModel);
        return new ResponseEntity(entityModel, HttpStatus.CREATED);
    }

    @PutMapping(PUT_DIMENSION_MAPPING)
    @ApiOperation(value = "Update an existing Dimension", response = DimensionDto.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "The entity was updated"),
            @ApiResponse(code = 400, message = "The entity was invalid"),
            @ApiResponse(code = 404, message = "The entity was not found")})
    public ResponseEntity<DimensionDto> updateDimension(@ApiParam("Entity") @RequestBody DimensionDto dimensionDto)  {
        var updatedDimensionDto = dimensionService.updateDimension(dimensionDto);
        var entityModel = new EntityModel<>(updatedDimensionDto);
        addLinks(entityModel);
        return new ResponseEntity(entityModel, HttpStatus.OK);
    }

    @DeleteMapping(DELETE_DIMENSION_MAPPING)
    @ApiOperation(value = "Delete a Dimension")
    @ApiResponses({
            @ApiResponse(code = 200, message = "The entity was deleted"),
            @ApiResponse(code = 404, message = "The entity was not found")})
    public ResponseEntity<Void> deleteDimension(@ApiParam("id") @PathVariable String id)  {
        dimensionService.deleteDimensionById(id);
        return ResponseEntity.ok().build();
    }

    private void addLinks(EntityModel<DimensionDto> entityModel) {
        entityModel.add(linkTo(DimensionRestController.class).slash(DIMENSIONS).withRel(buildGetDimensionsRel()));
        entityModel.add(linkTo(DimensionRestController.class).slash(DIMENSIONS).withRel(buildPostDimensionRel()));
        if (entityModel.getContent().getId() != null) {
            entityModel.add(linkTo(DimensionRestController.class).slash(DIMENSIONS).slash(entityModel.getContent().getId()).withSelfRel());
            entityModel.add(linkTo(DimensionRestController.class).slash(DIMENSIONS).slash(entityModel.getContent().getId()).withRel(buildPutDimensionRel()));
            entityModel.add(linkTo(DimensionRestController.class).slash(DIMENSIONS).slash(entityModel.getContent().getId()).withRel(buildDeleteDimensionRel()));
        }
    }
}
