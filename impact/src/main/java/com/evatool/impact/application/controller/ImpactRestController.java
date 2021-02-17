package com.evatool.impact.application.controller;

import com.evatool.impact.application.dto.ImpactDto;
import com.evatool.impact.application.service.ImpactService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.evatool.impact.application.controller.UriUtil.IMPACTS;
import static com.evatool.impact.application.controller.UriUtil.IMPACTS_ID;

@RestController
public class ImpactRestController {

    private final ImpactService impactService;

    public ImpactRestController(ImpactService impactService) {
        this.impactService = impactService;
    }

    @ApiOperation(value = "Read impact by ID")
    @GetMapping(IMPACTS_ID)
    public ResponseEntity<ImpactDto> getImpact(@ApiParam("id") @PathVariable String id) {
        var impactDto = impactService.findImpactById(id);
        return new ResponseEntity<>(impactDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Read all impacts")
    @GetMapping(IMPACTS)
    public List<ImpactDto> getAllImpacts() {
        return impactService.getAllImpacts();
    }

    @ApiOperation(value = "Create a new impact")
    @PostMapping(IMPACTS)
    public ResponseEntity<ImpactDto> createImpact(@RequestBody ImpactDto impactDto) {
        var insertedImpactDto = impactService.createImpact(impactDto);
        return new ResponseEntity<>(insertedImpactDto, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update an impact")
    @PutMapping(IMPACTS)
    public ResponseEntity<ImpactDto> updateImpact(@RequestBody ImpactDto impactDto) {
        var updatedImpactDto = impactService.updateImpact(impactDto);
        return new ResponseEntity<>(updatedImpactDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete impact by ID")
    @DeleteMapping(IMPACTS_ID)
    public ResponseEntity<Void> deleteImpact(@PathVariable String id) {
        impactService.deleteImpactById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO [future feature] using HATEOAS
     */
    private void addLinks(ImpactDto impactDto) {
        /*
        impactDto.add(linkTo(DimensionRestController.class).slash(IMPACTS).withRel(buildGetImpactsRel()));
        impactDto.add(linkTo(DimensionRestController.class).slash(IMPACTS).withRel(buildPostImpactRel()));
        if (impactDto.getId() != null) {
            impactDto.add(linkTo(DimensionRestController.class).slash(IMPACTS).slash(impactDto.getId()).withSelfRel());
            impactDto.add(linkTo(DimensionRestController.class).slash(IMPACTS).slash(impactDto.getId()).withRel(buildPutImpactRel()));
            impactDto.add(linkTo(DimensionRestController.class).slash(IMPACTS).slash(impactDto.getId()).withRel(buildDeleteImpactRel()));
        }
        */
    }
}
