package com.evatool.impact.common.controller;

import com.evatool.impact.persistence.entity.Stakeholder;
import com.evatool.impact.service.api.rest.StakeholderRestService;
import com.evatool.impact.service.impl.rest.StakeholderRestServiceImpl;
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

    @GetMapping("/stakeholder")
    public ResponseEntity<Stakeholder> saveStakeholder(@RequestBody Stakeholder stakeholder){
        return new ResponseEntity<>(stakeholderRestService.saveStakeholder(stakeholder), HttpStatus.CREATED);
    }

    @GetMapping("/stakeholders")
    public List<Stakeholder> getAllStakeholders() {
        return stakeholderRestService.getAllStakeholders();
    }
}
