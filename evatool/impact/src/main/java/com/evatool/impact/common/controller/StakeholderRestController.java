package com.evatool.impact.common.controller;

import com.evatool.impact.common.dto.StakeholderDto;
import com.evatool.impact.service.api.rest.StakeholderRestService;
import com.evatool.impact.service.impl.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StakeholderRestController {
    @Autowired
    private StakeholderRestService stakeholderRestService;

    @GetMapping("/stakeholder/{id}")
    public ResponseEntity<StakeholderDto> getStakeholder(@PathVariable String id) throws EntityNotFoundException {
        return new ResponseEntity<>(stakeholderRestService.getStakeholderById(id), HttpStatus.OK);
    }

    @GetMapping("/stakeholders")
    public List<StakeholderDto> getAllStakeholders() {
        return stakeholderRestService.getAllStakeholders();
    }

    @PostMapping("/stakeholder")
    public ResponseEntity<StakeholderDto> insertStakeholder(@RequestBody StakeholderDto stakeholderDto) {
        return new ResponseEntity<>(stakeholderRestService.insertStakeholder(stakeholderDto), HttpStatus.CREATED);
    }

    @PutMapping("/stakeholder/{id}")
    public ResponseEntity<StakeholderDto> updateStakeholder(@RequestBody StakeholderDto stakeholderDto) throws EntityNotFoundException {
        return new ResponseEntity<>(stakeholderRestService.updateStakeholder(stakeholderDto), HttpStatus.OK);
    }

    @DeleteMapping("/stakeholder/{id}")
    public ResponseEntity<Void> deleteStakeholder(@PathVariable String id) throws EntityNotFoundException {
        stakeholderRestService.deleteStakeholderById(id);
        return ResponseEntity.ok().build();
    }
}
