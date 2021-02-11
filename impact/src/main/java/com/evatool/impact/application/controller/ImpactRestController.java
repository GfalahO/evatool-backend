package com.evatool.impact.application.controller;

import com.evatool.impact.application.dto.ImpactDto;
import com.evatool.impact.application.service.ImpactService;
import com.evatool.impact.common.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.evatool.impact.application.controller.util.ImpactRest.*;

@RestController
@RequestMapping(IMPACT_REST_CONTROLLER_MAPPING)
public class ImpactRestController {

    private final ImpactService impactService;

    public ImpactRestController(ImpactService impactService) {
        this.impactService = impactService;
    }

    @GetMapping(GET_IMPACT_MAPPING)
    public ResponseEntity<ImpactDto> getImpact(@PathVariable String id) throws EntityNotFoundException {
        var impactDto = impactService.findImpactById(id);
        return new ResponseEntity<>(impactDto, HttpStatus.OK);
    }

    @GetMapping(GET_IMPACTS_MAPPING)
    public List<ImpactDto> getAllImpacts() {
        return impactService.getAllImpacts();
    }

    @PostMapping(POST_IMPACT_MAPPING)
    public ResponseEntity<ImpactDto> createImpact(@RequestBody ImpactDto impactDto) {
        var insertedImpactDto = impactService.createImpact(impactDto);
        return new ResponseEntity<>(insertedImpactDto, HttpStatus.CREATED);
    }

    @PutMapping(PUT_IMPACT_MAPPING)
    public ResponseEntity<ImpactDto> updateImpact(@RequestBody ImpactDto impactDto) throws EntityNotFoundException {
        var updatedImpactDto = impactService.updateImpact(impactDto);
        return new ResponseEntity<>(updatedImpactDto, HttpStatus.OK);
    }

    @DeleteMapping(DELETE_IMPACT_MAPPING)
    public ResponseEntity<Void> deleteImpact(@PathVariable String id) throws EntityNotFoundException {
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
