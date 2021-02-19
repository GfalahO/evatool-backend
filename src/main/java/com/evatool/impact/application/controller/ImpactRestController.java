package com.evatool.impact.application.controller;

import com.evatool.impact.application.dto.ImpactDto;
import com.evatool.impact.application.service.ImpactService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    private final ImpactService impactService;

    public ImpactRestController(ImpactService impactService) {
        this.impactService = impactService;
    }

    @ApiOperation(value = "Read com.evatool.impact by ID")
    @GetMapping(IMPACTS_ID)
    public ResponseEntity<EntityModel<ImpactDto>> getImpact(@ApiParam("id") @PathVariable String id) {
        var impactDto = impactService.findImpactById(id);
        return new ResponseEntity<>(getImpactWithLinks(impactDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Read all impacts")
    @GetMapping(IMPACTS)
    public List<ImpactDto> getAllImpacts() {
        return impactService.getAllImpacts();
    }

    @ApiOperation(value = "Create a new com.evatool.impact")
    @PostMapping(IMPACTS)
    public ResponseEntity<EntityModel<ImpactDto>> createImpact(@RequestBody ImpactDto impactDto) {
        var insertedImpactDto = impactService.createImpact(impactDto);
        return new ResponseEntity<>(getImpactWithLinks(insertedImpactDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update an com.evatool.impact")
    @PutMapping(IMPACTS)
    public ResponseEntity<EntityModel<ImpactDto>> updateImpact(@RequestBody ImpactDto impactDto) {
        var updatedImpactDto = impactService.updateImpact(impactDto);
        return new ResponseEntity<>(getImpactWithLinks(updatedImpactDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete com.evatool.impact by ID")
    @DeleteMapping(IMPACTS_ID)
    public ResponseEntity<Void> deleteImpact(@PathVariable String id) {
        impactService.deleteImpactById(id);
        return ResponseEntity.ok().build();
    }

    private EntityModel<ImpactDto> getImpactWithLinks(ImpactDto impactDto) {
        var impact = EntityModel.of(impactDto);
        impact.add(linkTo(methodOn(ImpactRestController.class).getImpact(impactDto.getId())).withSelfRel());
        impact.add(linkTo(ImpactRestController.class).slash(STAKEHOLDERS).slash(impactDto.getStakeholder().getId()).withRel(GET_STAKEHOLDER));
        return impact;
    }
}
