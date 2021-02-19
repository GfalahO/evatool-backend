package com.evatool.analysis.api.interfaces;

import com.evatool.analysis.model.Analysis;

public interface AnalysisController {

    /**
     * Method that initialize a new Analysis
     * Called when user clicks on "Weiter"
     */
    void addAnalysis();

    Analysis getAnalysisById(String analysisId);
}
