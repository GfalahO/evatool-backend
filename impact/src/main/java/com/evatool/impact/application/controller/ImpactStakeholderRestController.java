package com.evatool.impact.application.controller;

import com.evatool.impact.application.dto.StakeholderDto;
import com.evatool.impact.application.service.ImpactStakeholderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger =  LoggerFactory.getLogger(ImpactStakeholderRestController.class);

    private final ImpactStakeholderService stakeholderService;

    public ImpactStakeholderRestController(ImpactStakeholderService stakeholderService) {
        this.stakeholderService = stakeholderService;
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<StakeholderDto> getStakeholder_RestLevel3(@PathVariable String id) {
        var stakeholderDto = new StakeholderDto();
        stakeholderDto.setId(UUID.randomUUID().toString());
        stakeholderDto.setName("name");
        addLinks(stakeholderDto);
        var responseEntity = new ResponseEntity(stakeholderDto, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping(GET_STAKEHOLDERS_MAPPING)
    public List<StakeholderDto> getAllStakeholders() {
        var stakeholderDtoList = stakeholderService.getAllStakeholders();
        stakeholderDtoList.forEach(s -> addLinks(s));
        return stakeholderDtoList;
    }

    private void addLinks(StakeholderDto stakeholderDto) {
        stakeholderDto.add(linkTo(DimensionRestController.class).slash(STAKEHOLDERS).withRel(buildGetStakeholdersRel()));
    }
}
