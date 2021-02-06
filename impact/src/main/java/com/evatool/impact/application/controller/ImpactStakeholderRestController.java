package com.evatool.impact.application.controller;

import com.evatool.impact.application.controller.uri.RestSettings;
import com.evatool.impact.application.controller.uri.StakeholderRestUri;
import com.evatool.impact.application.dto.StakeholderDto;
import com.evatool.impact.application.service.ImpactStakeholderService;
import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.common.exception.IdNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(StakeholderRestUri.IMPACT_STAKEHOLDER_REST_CONTROLLER_MAPPING)
public class ImpactStakeholderRestController {
    @Autowired
    private ImpactStakeholderService stakeholderService;

    @GetMapping(StakeholderRestUri.GET_STAKEHOLDER_MAPPING)
    public ResponseEntity<StakeholderDto> getStakeholder(@PathVariable String id) throws EntityNotFoundException, IdNullException {
        var stakeholderDto = stakeholderService.findStakeholderById(id);
        stakeholderDto.add(linkTo(ImpactStakeholderRestController.class).slash(StakeholderRestUri.GET_STAKEHOLDER).slash(id).withSelfRel());
        stakeholderDto.add(linkTo(ImpactStakeholderRestController.class).slash(StakeholderRestUri.GET_STAKEHOLDERS).withRel(RestSettings.GET_ALL_LINK));
        stakeholderDto.add(linkTo(ImpactStakeholderRestController.class).slash(StakeholderRestUri.POST_STAKEHOLDER).withRel(RestSettings.POST_LINK));
        stakeholderDto.add(linkTo(ImpactStakeholderRestController.class).slash(StakeholderRestUri.PUT_STAKEHOLDER).slash(id).withRel(RestSettings.PUT_LINK));
        stakeholderDto.add(linkTo(ImpactStakeholderRestController.class).slash(StakeholderRestUri.DELETE_STAKEHOLDER).slash(id).withRel(RestSettings.DELETE_LINK));
        return new ResponseEntity<>(stakeholderDto, HttpStatus.OK);
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<StakeholderDto> getStakeholder_RestLevel3(@PathVariable String id) {
        var stakeholderDto = new StakeholderDto();
        stakeholderDto.setId(UUID.randomUUID().toString());
        stakeholderDto.setName("name");

        stakeholderDto.add(linkTo(ImpactStakeholderRestController.class).slash(StakeholderRestUri.GET_STAKEHOLDER).slash(id).withSelfRel());
        stakeholderDto.add(linkTo(ImpactStakeholderRestController.class).slash(StakeholderRestUri.GET_STAKEHOLDERS).withRel(RestSettings.GET_ALL_LINK));
        stakeholderDto.add(linkTo(ImpactStakeholderRestController.class).slash(StakeholderRestUri.POST_STAKEHOLDER).withRel(RestSettings.POST_LINK));
        stakeholderDto.add(linkTo(ImpactStakeholderRestController.class).slash(StakeholderRestUri.PUT_STAKEHOLDER).slash(id).withRel(RestSettings.PUT_LINK));
        stakeholderDto.add(linkTo(ImpactStakeholderRestController.class).slash(StakeholderRestUri.DELETE_STAKEHOLDER).slash(id).withRel(RestSettings.DELETE_LINK));

        var responseEntity = new ResponseEntity(stakeholderDto, HttpStatus.OK);
        System.out.println(responseEntity);
        return responseEntity;
    }

    @GetMapping(StakeholderRestUri.GET_STAKEHOLDERS_MAPPING)
    public List<StakeholderDto> getAllStakeholders() {
        var stakeholderDtoList = stakeholderService.getAllStakeholders();
        return stakeholderDtoList;
    }

    @PostMapping(StakeholderRestUri.POST_STAKEHOLDER_MAPPING)
    public ResponseEntity<StakeholderDto> createStakeholder(@RequestBody StakeholderDto stakeholderDto) {
        return new ResponseEntity<>(stakeholderService.createStakeholder(stakeholderDto), HttpStatus.CREATED);
    }

    @PutMapping(StakeholderRestUri.PUT_STAKEHOLDER_MAPPING)
    public ResponseEntity<StakeholderDto> updateStakeholder(@RequestBody StakeholderDto stakeholderDto) throws EntityNotFoundException, IdNullException {
        return new ResponseEntity<>(stakeholderService.updateStakeholder(stakeholderDto), HttpStatus.OK);
    }

    @DeleteMapping(StakeholderRestUri.DELETE_STAKEHOLDER_MAPPING)
    public ResponseEntity<Void> deleteStakeholder(@PathVariable String id) throws EntityNotFoundException, IdNullException {
        stakeholderService.deleteStakeholderById(id);
        return ResponseEntity.ok().build();
    }
}
