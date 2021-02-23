package com.evatool.analysis.api.controller;

import com.evatool.analysis.api.interfaces.AnalysisController;
import com.evatool.analysis.dto.AnalysisDTO;
import com.evatool.global.event.analysis.AnalysisCreatedEvent;
import com.evatool.analysis.events.AnalysisEventPublisher;
import com.evatool.analysis.model.Analysis;
import com.evatool.analysis.repository.AnalysisRepository;
import com.evatool.analysis.services.AnalysisDTOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class AnalysisControllerImpl implements AnalysisController {

    @Autowired
    private AnalysisRepository analysisRepository;

    @Autowired
    private AnalysisEventPublisher analysisEventPublisher;

    @Autowired
    private AnalysisDTOService analysisDTOService;

    Logger logger = LoggerFactory.getLogger(AnalysisControllerImpl.class);


    @Override
    public List<AnalysisDTO> getAnalysisList() {
        logger.info("/analysis");
        List<Analysis> analysisList = analysisRepository.findAll();
        analysisEventPublisher.publishEvent(new AnalysisCreatedEvent("ANALYSIS EVENT PUBLISHED"));
        if (analysisList.size() == 0){
            return Arrays.asList();
        }
        return analysisDTOService.findAll(analysisList);
    }

    @Override
    public Optional<Analysis> getAnalysisById(UUID id) {
        logger.info("/analysis/{}");
        return analysisRepository.findById(id);

    }

    @Override
    public Analysis addAnalysis(Analysis analysis) {
        logger.info("/addStakeholder");
        return analysisRepository.save(analysis);
    }

    @Override
    public Analysis updateAnalysis(Analysis analysis) {
        logger.info("/stakeholder");
        return analysisRepository.save(analysis);
    }

    @Override
    public void deleteAnalysis(UUID id) {
        logger.info("/stakeholder");
        analysisRepository.deleteById(id);

    }

    /**
    @Autowired
    private StakeholderCreatedEventPublisher applicationEventPublisher;

    // Hallo analysis Gruppe das hier ist nur ein test
    @GetMapping("/event")
    public ResponseEntity<Void> eventTest() throws InterruptedException {
        applicationEventPublisher.onStakeholderCreated();
        return ResponseEntity.ok().build();
    }**/
}
