package com.evatool.impact.common.controller.rest;

import com.evatool.impact.common.dto.StakeholderDto;
import com.evatool.impact.common.mapper.StakeholderMapper;
import com.evatool.impact.persistence.entity.Stakeholder;
import com.evatool.impact.service.api.rest.StakeholderRestService;
import com.evatool.impact.service.impl.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StakeholderRestController {
    @Autowired
    private StakeholderRestService stakeholderRestService;

    private StakeholderMapper modelMapper = new StakeholderMapper();

    @GetMapping("/stakeholder/{id}")
    public ResponseEntity<StakeholderDto> getStakeholder(@PathVariable String id) throws EntityNotFoundException {
        var stakeholder = stakeholderRestService.getStakeholderById(id);
        var stakeholderDto = convertToDto(stakeholder);
        var responseEntity = new ResponseEntity<>(stakeholderDto, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/stakeholders")
    public List<StakeholderDto> getAllStakeholders() {
        var stakeholders = stakeholderRestService.getAllStakeholders();
        var stakeholderDtoList = new ArrayList<StakeholderDto>();
        stakeholders.forEach(s -> stakeholderDtoList.add(convertToDto(s)));
        return stakeholderDtoList;
    }

    @PostMapping("/stakeholder")
    public ResponseEntity<StakeholderDto> insertStakeholder(@RequestBody StakeholderDto stakeholderDto) {
        var stakeholder = convertToEntity(stakeholderDto);
        var insertedStakeholder = stakeholderRestService.insertStakeholder(stakeholder);
        var insertedStakeholderDto = convertToDto(insertedStakeholder);
        var responseEntity = new ResponseEntity<>(insertedStakeholderDto, HttpStatus.CREATED);
        return responseEntity;
    }

    @PutMapping("/stakeholder/{id}")
    public ResponseEntity<StakeholderDto> updateStakeholder(@RequestBody StakeholderDto stakeholderDto) throws EntityNotFoundException {
        var stakeholder = convertToEntity(stakeholderDto);
        var updateStakeholder = stakeholderRestService.updateStakeholder(stakeholder);
        var updateStakeholderDto = convertToDto(updateStakeholder);
        var responseEntity = new ResponseEntity<>(updateStakeholderDto, HttpStatus.OK);
        return responseEntity;
    }

    @DeleteMapping("/stakeholder/{id}")
    public ResponseEntity<Void> deleteStakeholder(@PathVariable String id) throws EntityNotFoundException {
        stakeholderRestService.deleteStakeholderById(id);
        return ResponseEntity.ok().build();
    }

    private Stakeholder convertToEntity(StakeholderDto stakeholderDto) {
        var entity = modelMapper.fromDto(stakeholderDto);
        return entity;
    }

    private StakeholderDto convertToDto(Stakeholder stakeholder) {
        var dto = modelMapper.toDto(stakeholder);
        return dto;
    }
}
