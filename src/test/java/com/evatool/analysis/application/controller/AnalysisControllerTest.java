package com.evatool.analysis.application.controller;

import com.evatool.analysis.api.controller.UserControllerImpl;
import com.evatool.analysis.api.interfaces.AnalysisController;
import com.evatool.analysis.api.interfaces.StakeholderController;
import com.evatool.analysis.api.interfaces.UserController;
import com.evatool.analysis.dto.AnalysisDTO;
import com.evatool.analysis.model.Analysis;
import com.evatool.analysis.model.Stakeholder;
import com.evatool.analysis.model.User;
import com.evatool.analysis.repository.StakeholderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static com.evatool.analysis.common.TestDataGenerator.getAnalysis;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AnalysisControllerTest {

    @Autowired
    private AnalysisController analysisController;



    @Test
    public void testAnalysisController(){

        //create analysis
        Analysis analysis = analysisController.addAnalysis(getAnalysis());

        //check is analysis created
        assertThat(analysisController.getAnalysisById(analysis.getAnalysisId()).get()).isNotNull();

        //change requirement title
        String testTitle = "Stani";
        analysis.setAnalysisName(testTitle);
        analysisController.updateAnalysis(analysis);

        //check is analysis title changed
        Optional<Analysis> analysisAfterUpdate = analysisController.getAnalysisById(analysis.getAnalysisId());

        assertThat(analysisAfterUpdate.get().getAnalysisName()).isEqualTo(testTitle);

        assertThat(analysisAfterUpdate.get().getAnalysisName()).isEqualTo(testTitle);

        // delete analysis
        UUID idAnalysis = analysis.getAnalysisId();
        analysisController.deleteAnalysis(idAnalysis);

        //check is analysis deleted
        Optional<Analysis> deletedAnalysis = analysisController.getAnalysisById(idAnalysis);
        assertThat(deletedAnalysis.isEmpty()).isEqualTo(true);

    }

}
