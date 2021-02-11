package com.evatool.impact.application.controller;

import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.application.service.DimensionService;
import com.evatool.impact.common.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.evatool.impact.application.controller.util.DimensionRest.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(DIMENSION_REST_CONTROLLER_MAPPING)
public class DimensionRestController {

    // TODO [tzaika] should we use logging?
    private static Logger logger = LoggerFactory.getLogger(DimensionRestController.class);

    private final DimensionService dimensionService;

    public DimensionRestController(DimensionService dimensionService) {
        this.dimensionService = dimensionService;
    }

    @GetMapping(GET_DIMENSION_MAPPING)
    public ResponseEntity<DimensionDto> getDimension(@PathVariable String id) throws EntityNotFoundException {
        var dimensionDto = dimensionService.findDimensionById(id);
        addLinks(dimensionDto);
        logger.info("Dimension with id {} retrieved.", id);
        return new ResponseEntity<>(dimensionDto, HttpStatus.OK);
    }

    @GetMapping(GET_DIMENSIONS_MAPPING)
    public List<DimensionDto> getAllDimensions() {
        var dimensionDtoList = dimensionService.getAllDimensions();
        dimensionDtoList.forEach(this::addLinks);
        return dimensionDtoList;
    }

    @PostMapping(POST_DIMENSION_MAPPING)
    public ResponseEntity<DimensionDto> createDimension(@RequestBody DimensionDto dimensionDto) {
        var insertedDimensionDto = dimensionService.createDimension(dimensionDto);
        addLinks(insertedDimensionDto);
        return new ResponseEntity<>(insertedDimensionDto, HttpStatus.CREATED);
    }

    @PutMapping(PUT_DIMENSION_MAPPING)
    public ResponseEntity<DimensionDto> updateDimension(@RequestBody DimensionDto dimensionDto) throws EntityNotFoundException {
        var updatedDimensionDto = dimensionService.updateDimension(dimensionDto);
        addLinks(updatedDimensionDto);
        return new ResponseEntity<>(updatedDimensionDto, HttpStatus.OK);
    }

    @DeleteMapping(DELETE_DIMENSION_MAPPING)
    public ResponseEntity<Void> deleteDimension(@PathVariable String id) throws EntityNotFoundException {
        dimensionService.deleteDimensionById(id);
        return ResponseEntity.ok().build();
    }

    private void addLinks(DimensionDto dimensionDto) {
        dimensionDto.add(linkTo(DimensionRestController.class).slash(DIMENSIONS).withRel(buildGetDimensionsRel()));
        dimensionDto.add(linkTo(DimensionRestController.class).slash(DIMENSIONS).withRel(buildPostDimensionRel()));
        if (dimensionDto.getId() != null) {
            dimensionDto.add(linkTo(DimensionRestController.class).slash(DIMENSIONS).slash(dimensionDto.getId()).withSelfRel());
            dimensionDto.add(linkTo(DimensionRestController.class).slash(DIMENSIONS).slash(dimensionDto.getId()).withRel(buildPutDimensionRel()));
            dimensionDto.add(linkTo(DimensionRestController.class).slash(DIMENSIONS).slash(dimensionDto.getId()).withRel(buildDeleteDimensionRel()));
        }
    }
}
