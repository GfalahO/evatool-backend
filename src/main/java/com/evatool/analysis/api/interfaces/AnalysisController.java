package com.evatool.analysis.api.interfaces;

import com.evatool.analysis.dto.AnalysisDTO;
import com.evatool.analysis.model.Analysis;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Api(description = "API-endpoint for a analysis")
public interface AnalysisController {

    @GetMapping("/analysis")
    @ApiOperation(value = "This method returns a list of all analysis")
    public List<AnalysisDTO> getAnalysisList();

    @GetMapping("/analysis/{id}")
    @ApiOperation(value = "This method returns a value of an analysis by ID")
    public Optional<Analysis> getAnalysisById(@PathVariable UUID id);

    @PostMapping("/addAnalysis")
    @ApiOperation(value = "This method add an analysis")
    public Analysis addAnalysis(Analysis analysis);

    @PutMapping("/analysis/{id}")
    @ApiOperation(value = "This method updated an Analysis by his ID")
    public Analysis updateAnalysis(@RequestBody Analysis analysis);

    @DeleteMapping("/analysis/{id}")
    @ApiOperation(value = "This method delete an analysis By his ID ")
    public void deleteAnalysis(@PathVariable UUID id);
}
