package com.evatool.impact.application.controller;

import com.evatool.impact.application.dto.StakeholderDto;
import com.evatool.impact.application.service.ImpactStakeholderService;
import com.evatool.impact.common.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.evatool.impact.application.controller.util.StakeholderRest.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(IMPACT_STAKEHOLDER_REST_CONTROLLER_MAPPING)
public class ImpactStakeholderRestController {
    @Autowired
    private ImpactStakeholderService stakeholderService;

    @GetMapping("/test/{id}")
    public ResponseEntity<StakeholderDto> getStakeholder_RestLevel3(@PathVariable String id) {
        var stakeholderDto = new StakeholderDto();
        stakeholderDto.setId(UUID.randomUUID().toString());
        stakeholderDto.setName("name");
        addLinks(stakeholderDto);
        var responseEntity = new ResponseEntity(stakeholderDto, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping(GET_STAKEHOLDER_MAPPING)
    public ResponseEntity<StakeholderDto> getStakeholder(@PathVariable String id) throws EntityNotFoundException {
        var stakeholderDto = stakeholderService.findStakeholderById(id);
        addLinks(stakeholderDto);
        return new ResponseEntity(stakeholderDto, HttpStatus.OK);
    }

    @GetMapping(GET_STAKEHOLDERS_MAPPING)
    public List<StakeholderDto> getAllStakeholders() {
        var stakeholderDtoList = stakeholderService.getAllStakeholders();
        stakeholderDtoList.forEach(s -> addLinks(s));
        return stakeholderDtoList;
    }

    @PostMapping(POST_STAKEHOLDER_MAPPING)
    public ResponseEntity<StakeholderDto> createStakeholder(@RequestBody StakeholderDto stakeholderDto) {
        var insertedStakeholderDto = stakeholderService.createStakeholder(stakeholderDto);
        addLinks(insertedStakeholderDto);
        return new ResponseEntity(insertedStakeholderDto, HttpStatus.CREATED);
    }

    @PutMapping(PUT_STAKEHOLDER_MAPPING)
    public ResponseEntity<StakeholderDto> updateStakeholder(@RequestBody StakeholderDto stakeholderDto) throws EntityNotFoundException {
        var updatedStakeholderDto = stakeholderService.updateStakeholder(stakeholderDto);
        addLinks(updatedStakeholderDto);
        return new ResponseEntity(updatedStakeholderDto, HttpStatus.OK);
    }

    @DeleteMapping(DELETE_STAKEHOLDER_MAPPING)
    public ResponseEntity<Void> deleteStakeholder(@PathVariable String id) throws EntityNotFoundException {
        stakeholderService.deleteStakeholderById(id);
        return ResponseEntity.ok().build();
    }

    public static void addLinks(StakeholderDto stakeholderDto) {
        stakeholderDto.add(linkTo(DimensionRestController.class).slash(GET_STAKEHOLDERS).withRel(buildGetAllStakeholdersRel()));
        stakeholderDto.add(linkTo(DimensionRestController.class).slash(POST_STAKEHOLDER).withRel(buildPostStakeholderRel()));
        if (stakeholderDto.getId() != null) {
            stakeholderDto.add(linkTo(DimensionRestController.class).slash(GET_STAKEHOLDER).slash(stakeholderDto.getId()).withSelfRel());
            stakeholderDto.add(linkTo(DimensionRestController.class).slash(PUT_STAKEHOLDER).slash(stakeholderDto.getId()).withRel(buildPutStakeholderRel()));
            stakeholderDto.add(linkTo(DimensionRestController.class).slash(DELETE_STAKEHOLDER).slash(stakeholderDto.getId()).withRel(buildDeleteStakeholderRel()));
        }
    }
}
