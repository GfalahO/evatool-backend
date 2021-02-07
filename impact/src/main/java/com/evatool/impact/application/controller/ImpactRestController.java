package com.evatool.impact.application.controller;

import com.evatool.impact.application.dto.ImpactDto;
import com.evatool.impact.application.service.ImpactService;
import com.evatool.impact.common.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.evatool.impact.application.controller.uri.ImpactRestUri.*;
import static com.evatool.impact.application.controller.uri.ImpactRestUri.PLURAL;
import static com.evatool.impact.application.controller.uri.ImpactRestUri.SINGULAR;
import static com.evatool.impact.application.controller.uri.RestLevel3LinkRel.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(IMPACT_REST_CONTROLLER_MAPPING)
public class ImpactRestController {
    @Autowired
    private ImpactService impactService;

    @GetMapping(GET_IMPACT_MAPPING)
    public ResponseEntity<ImpactDto> getImpact(@PathVariable String id) throws EntityNotFoundException {
        var impactDto = impactService.findImpactById(id);
        addLinks(impactDto);
        return new ResponseEntity(impactDto, HttpStatus.OK);
    }

    @GetMapping(GET_IMPACTS_MAPPING)
    public List<ImpactDto> getAllImpacts() {
        var impactDtoList = impactService.getAllImpacts();
        impactDtoList.forEach(s -> addLinks(s));
        return impactDtoList;
    }

    @PostMapping(POST_IMPACT_MAPPING)
    public ResponseEntity<ImpactDto> createImpact(@RequestBody ImpactDto impactDto) {
        var insertedImpactDto = impactService.createImpact(impactDto);
        addLinks(insertedImpactDto);
        return new ResponseEntity(insertedImpactDto, HttpStatus.CREATED);
    }

    @PutMapping(PUT_IMPACT_MAPPING)
    public ResponseEntity<ImpactDto> updateImpact(@RequestBody ImpactDto impactDto) throws EntityNotFoundException {
        var updatedImpactDto = impactService.updateImpact(impactDto);
        addLinks(updatedImpactDto);
        return new ResponseEntity(updatedImpactDto, HttpStatus.OK);
    }

    @DeleteMapping(DELETE_IMPACT_MAPPING)
    public ResponseEntity<Void> deleteImpact(@PathVariable String id) throws EntityNotFoundException {
        impactService.deleteImpactById(id);
        return ResponseEntity.ok().build();
    }

    private void addLinks(ImpactDto impactDto) {
        impactDto.add(linkTo(DimensionRestController.class).slash(GET_IMPACTS).withRel(buildGetAllRel(PLURAL)));
        impactDto.add(linkTo(DimensionRestController.class).slash(POST_IMPACT).withRel(buildPostRel(SINGULAR)));
        if (impactDto.getId() != null) {
            impactDto.add(linkTo(DimensionRestController.class).slash(GET_IMPACT).slash(impactDto.getId()).withSelfRel());
            impactDto.add(linkTo(DimensionRestController.class).slash(PUT_IMPACT).slash(impactDto.getId()).withRel(buildPutRel(SINGULAR)));
            impactDto.add(linkTo(DimensionRestController.class).slash(DELETE_IMPACT).slash(impactDto.getId()).withRel(buildDeleteRel(SINGULAR)));
        }
    }
}
