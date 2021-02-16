package com.evatool.analysis.api.controller;

import com.evatool.analysis.api.interfaces.AnalysisController;
import com.evatool.analysis.event.StakeholderCreatedEventPublisher;
import com.evatool.analysis.model.Analysis;
import com.evatool.analysis.repository.AnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AnalysisControllerImpl implements AnalysisController {

    @Autowired
    AnalysisRepository analysisRepository;
    Analysis analysis;

    @Override
    @PostMapping("/addAnalysis")
    public void addAnalysis() {
        analysis.setAnalysisName("WERT");
        analysis.setDescription("WERT");
    }

    @Override
    @GetMapping("/getAnalysisById/{id}")
    public Analysis getAnalysisById(@PathVariable String analysisId) {
        Optional<Analysis> currentAnalysis = analysisRepository.findById(analysisId);
        return currentAnalysis.get();
    }


    @Autowired
    private StakeholderCreatedEventPublisher applicationEventPublisher;

    // Hallo analysis Gruppe das hier ist nur ein test
    @GetMapping("/event")
    public ResponseEntity<Void> eventTest() throws InterruptedException {
        applicationEventPublisher.onStakeholderCreated();
        return ResponseEntity.ok().build();
    }
}
