package com.evatool.impact.application.controller;

import com.evatool.impact.application.controller.uri.StakeholderRestUri;
import com.evatool.impact.application.dto.StakeholderDto;
import com.evatool.impact.application.service.ImpactStakeholderService;
import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.common.exception.IdNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(StakeholderRestUri.IMPACT_STAKEHOLDER_REST_CONTROLLER_URI)
public class ImpactStakeholderRestController {
    @Autowired
    private ImpactStakeholderService stakeholderService;

    @GetMapping(StakeholderRestUri.GET_STAKEHOLDER_URI)
    public ResponseEntity<StakeholderDto> getStakeholder(@PathVariable String id) throws EntityNotFoundException, IdNullException {
        var stakeholderDto = stakeholderService.findStakeholderById(id);
        stakeholderDto.add(linkTo(ImpactStakeholderRestController.class).slash(stakeholderDto.getId()).withSelfRel());
        return new ResponseEntity<>(stakeholderDto, HttpStatus.OK);
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<StakeholderDto> getStakeholder_RestLevel3(@PathVariable String id) {
        var stakeholderDto = new StakeholderDto();
        stakeholderDto.setId(UUID.randomUUID().toString());
        stakeholderDto.setName("name");

        var selfLink = linkTo(ImpactStakeholderRestController.class).slash(stakeholderDto.getId()).withSelfRel();

        System.out.println(selfLink);
        stakeholderDto.add(selfLink);
        var responseEntity = new ResponseEntity(stakeholderDto, HttpStatus.OK);
        System.out.println(responseEntity);
        return responseEntity;
    }

    @GetMapping(StakeholderRestUri.GET_ALL_STAKEHOLDERS_URI)
    public List<StakeholderDto> getAllStakeholders() {
        var stakeholderDtoList = stakeholderService.getAllStakeholders();
        return stakeholderDtoList;
    }

    @PostMapping(StakeholderRestUri.CREATE_STAKEHOLDER_URI)
    public ResponseEntity<StakeholderDto> createStakeholder(@RequestBody StakeholderDto stakeholderDto) {
        return new ResponseEntity<>(stakeholderService.createStakeholder(stakeholderDto), HttpStatus.CREATED);
    }

    @PutMapping(StakeholderRestUri.UPDATE_STAKEHOLDER_URI)
    public ResponseEntity<StakeholderDto> updateStakeholder(@RequestBody StakeholderDto stakeholderDto) throws EntityNotFoundException, IdNullException {
        return new ResponseEntity<>(stakeholderService.updateStakeholder(stakeholderDto), HttpStatus.OK);
    }

    @DeleteMapping(StakeholderRestUri.DELETE_STAKEHOLDER_URI)
    public ResponseEntity<Void> deleteStakeholder(@PathVariable String id) throws EntityNotFoundException, IdNullException {
        stakeholderService.deleteStakeholderById(id);
        return ResponseEntity.ok().build();
    }
}
