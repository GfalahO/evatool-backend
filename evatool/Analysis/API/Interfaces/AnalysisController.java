package com.FAE.EVATool.Analysis.API.Interfaces;

import com.FAE.EVATool.Analysis.Model.Analysis;
import org.springframework.beans.factory.annotation.Autowired;

public interface AnalysisController {

    /**
     * Method that initialize a new Analysis
     * Called when user clicks on "Weiter"
     */
    void addAnalysis();

    Analysis getAnalysisById(String analysisId);
}
