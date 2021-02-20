package com.evatool.impact.application.controller;

import com.evatool.impact.application.dto.ImpactDto;
import com.evatool.impact.application.service.ImpactService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "Read impact by ID")
    @GetMapping(IMPACTS_ID)
    public ResponseEntity<EntityModel<ImpactDto>> getImpact(@ApiParam("id") @PathVariable String id) {
        logger.info("GET " + IMPACTS_ID);
        var impactDto = impactService.findImpactById(id);
        return new ResponseEntity<>(getImpactWithLinks(impactDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Read all impacts")
    @GetMapping(IMPACTS)
    public List<ImpactDto> getAllImpacts() {
        logger.info("GET " + IMPACTS);
        return impactService.getAllImpacts();
    }

    @ApiOperation(value = "Create a new impact")
    @PostMapping(IMPACTS)
    public ResponseEntity<EntityModel<ImpactDto>> createImpact(@RequestBody ImpactDto impactDto) {
        logger.info("POST " + IMPACTS);
        var insertedImpactDto = impactService.createImpact(impactDto);
        return new ResponseEntity<>(getImpactWithLinks(insertedImpactDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update an impact")
    @PutMapping(IMPACTS)
    public ResponseEntity<EntityModel<ImpactDto>> updateImpact(@RequestBody ImpactDto impactDto) {
        logger.info("PUT " + IMPACTS);
        var updatedImpactDto = impactService.updateImpact(impactDto);
        return new ResponseEntity<>(getImpactWithLinks(updatedImpactDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete impact by ID")
    @DeleteMapping(IMPACTS_ID)
    public ResponseEntity<Void> deleteImpact(@PathVariable String id) {
        logger.info("DELETE " + IMPACTS_ID);
        impactService.deleteImpactById(id);
        return ResponseEntity.ok().build();
    }

    private EntityModel<ImpactDto> getImpactWithLinks(ImpactDto impactDto) {
        logger.debug("Adding HATEOAS Rest Level 3 links");
        var entityModel = EntityModel.of(impactDto);
        entityModel.add(linkTo(methodOn(ImpactRestController.class).getImpact(impactDto.getId())).withSelfRel());
        entityModel.add(linkTo(ImpactRestController.class).slash(STAKEHOLDERS).slash(impactDto.getStakeholder().getId()).withRel(GET_STAKEHOLDER));
        entityModel.add(linkTo(DimensionRestController.class).slash(DIMENSIONS).slash(impactDto.getDimension().getId()).withRel(GET_DIMENSION));
        return entityModel;
    }
}
