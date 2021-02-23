package com.evatool.analysis.api.interfaces;


import com.evatool.analysis.enums.StakeholderLevel;
import com.evatool.analysis.model.Analysis;
import com.evatool.analysis.model.Stakeholder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Api (description = "API-endpoint for Stakeholder")
public interface StakeholderController {

    @GetMapping("/stakeholder")
    @ApiOperation(value = "This method returns a list of stakeholder")
    public List<Stakeholder> getStakeholderList();

    @GetMapping("/stakeholder/{id}")
    @ApiOperation(value = "This method returns an optional of an Stakeholder by his ID")
    public Optional<Stakeholder> getStakeholderById(@PathVariable UUID id);

    @PostMapping("/addStakeholder")
    @ApiOperation(value = "This Method add a Stakeholder")
    public Stakeholder addStakeholder(Stakeholder stakeholder);

    @PutMapping("/stakeholder/{id}")
    @ApiOperation(value = "This method updated an stakeholder by his id")
    public Stakeholder updateStakeholder(@RequestBody Stakeholder stakeholder);

    @DeleteMapping("/stakeholder/{id}")
    @ApiOperation(value = "This method delete an stakeholder by his id ")
    public void deleteStakeholder(@PathVariable UUID id);

}
