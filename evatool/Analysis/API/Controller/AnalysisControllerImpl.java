package com.FAE.EVATool.Analysis.API.Controller;

import com.FAE.EVATool.Analysis.API.Interfaces.AnalysisController;
import com.FAE.EVATool.Analysis.Model.Analysis;
import com.FAE.EVATool.Analysis.Repository.AnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


}
