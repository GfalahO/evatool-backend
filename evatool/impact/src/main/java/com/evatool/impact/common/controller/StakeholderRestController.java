package com.evatool.impact.common.controller;

import com.evatool.impact.persistence.entity.Stakeholder;
import com.evatool.impact.service.api.rest.StakeholderRestService;
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
    public ResponseEntity<Stakeholder> getStakeholder(@PathVariable String id) {
        return new ResponseEntity<>(stakeholderRestService.getStakeholderById(id), HttpStatus.OK);
    }

    @PostMapping("/stakeholder")
    public ResponseEntity<Stakeholder> saveStakeholder(@RequestBody Stakeholder stakeholder) {
        return new ResponseEntity<>(stakeholderRestService.saveStakeholder(stakeholder), HttpStatus.CREATED);
    }

    @DeleteMapping("/stakeholder/{id}")
    public ResponseEntity<Void> deleteStakeholder(@PathVariable String id) {
        stakeholderRestService.deleteStakeholderById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/stakeholders")
    public List<Stakeholder> getAllStakeholders() {
        return stakeholderRestService.getAllStakeholders();
    }
}
