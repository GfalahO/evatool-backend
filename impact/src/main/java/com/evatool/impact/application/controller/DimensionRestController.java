package com.evatool.impact.application.controller;

import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.application.service.DimensionService;
import com.evatool.impact.common.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.evatool.impact.application.controller.uri.DimensionRestUri.*;
import static com.evatool.impact.application.controller.uri.RestSettings.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(DIMENSION_REST_CONTROLLER_MAPPING)
public class DimensionRestController {
    @Autowired
    private DimensionService dimensionService;

    @GetMapping(GET_DIMENSION_MAPPING)
    public ResponseEntity<DimensionDto> getDimension(@PathVariable String id) throws EntityNotFoundException {
        var dimensionDto = dimensionService.findDimensionById(id);
        addLinks(dimensionDto);
        return new ResponseEntity(dimensionDto, HttpStatus.OK);
    }

    @GetMapping(GET_DIMENSIONS_MAPPING)
    public List<DimensionDto> getAllDimensions() {
        var dimensionDtoList = dimensionService.getAllDimensions();
        dimensionDtoList.forEach(s -> addLinks(s));
        return dimensionDtoList;
    }

    @PostMapping(POST_DIMENSION_MAPPING)
    public ResponseEntity<DimensionDto> createDimension(@RequestBody DimensionDto dimensionDto) {
        var insertedDimensionDto = dimensionService.createDimension(dimensionDto);
        addLinks(insertedDimensionDto);
        return new ResponseEntity(insertedDimensionDto, HttpStatus.CREATED);
    }

    @PutMapping(PUT_DIMENSION_MAPPING)
    public ResponseEntity<DimensionDto> updateDimension(@RequestBody DimensionDto dimensionDto) throws EntityNotFoundException {
        var updatedDimensionDto = dimensionService.updateDimension(dimensionDto);
        addLinks(updatedDimensionDto);
        return new ResponseEntity(updatedDimensionDto, HttpStatus.OK);
    }

    @DeleteMapping(DELETE_DIMENSION_MAPPING)
    public ResponseEntity<Void> deleteDimension(@PathVariable String id) throws EntityNotFoundException {
        dimensionService.deleteDimensionById(id);
        return ResponseEntity.ok().build();
    }

    private void addLinks(DimensionDto dimensionDto) {
        dimensionDto.add(linkTo(DimensionRestController.class).slash(GET_DIMENSIONS).withRel(GET_ALL_LINK));
        dimensionDto.add(linkTo(DimensionRestController.class).slash(POST_DIMENSION).withRel(POST_LINK));
        if (dimensionDto.getId() != null) {
            dimensionDto.add(linkTo(DimensionRestController.class).slash(GET_DIMENSION).slash(dimensionDto.getId()).withSelfRel());
            dimensionDto.add(linkTo(DimensionRestController.class).slash(PUT_DIMENSION).slash(dimensionDto.getId()).withRel(PUT_LINK));
            dimensionDto.add(linkTo(DimensionRestController.class).slash(DELETE_DIMENSION).slash(dimensionDto.getId()).withRel(DELETE_LINK));
        }
    }
}
