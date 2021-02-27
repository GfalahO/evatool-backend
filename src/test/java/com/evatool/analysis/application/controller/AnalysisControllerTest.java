package com.evatool.analysis.application.controller;



import com.evatool.analysis.api.interfaces.AnalysisController;
import com.evatool.analysis.dto.AnalysisDTO;
import com.evatool.analysis.enums.Dimension;
import com.evatool.analysis.error.exceptions.EntityNotFoundException;
import com.evatool.analysis.model.Analysis;
import com.evatool.analysis.model.AnalysisImpacts;
import com.evatool.analysis.repository.AnalysisImpactRepository;
import com.evatool.analysis.repository.AnalysisRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.*;
import java.util.UUID;

import static com.evatool.analysis.common.TestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AnalysisControllerTest {

    @Autowired
    private AnalysisController analysisController;

    @Autowired
    private AnalysisImpactRepository analysisImpactRepository;

    @Autowired
    private AnalysisRepository analysisRepository;

    @Test
    public void testAnalysisController_ThrowException() {

        AnalysisImpacts analysisImpacts = getAnalysisImpacts(Dimension.SAFETY);
        analysisImpactRepository.save(analysisImpacts);

        Analysis analysis = new Analysis("name", "description");

        //create analysis
        AnalysisDTO analysisDTO = getAnalysisDTO(analysis.getAnalysisName(), analysis.getDescription());
        AnalysisDTO analysisDTOObj = analysisController.addAnalysis(analysisDTO).getContent();

        //check is analysis created
        assertThat(analysisController.getAnalysisById(analysisDTOObj.getRootEntityID())).isNotNull();

        //change analysis title
        String testName = "TestName";
        analysisDTOObj.setAnalysisName(testName);
        analysisController.updateAnalysis(analysisDTOObj);

        //check is analysis title changed
        AnalysisDTO analysisAfterUpdate = analysisController.getAnalysisById(analysisDTOObj.getRootEntityID()).getContent();

        assertThat(analysisAfterUpdate.getAnalysisName()).isEqualTo(testName);

        //delete analysis
        UUID id = analysisDTOObj.getRootEntityID();
        analysisController.deleteAnalysis(id);

        //check is analysis deleted
        Exception exception = assertThrows(EntityNotFoundException.class, ()->analysisController.getAnalysisById(analysisDTOObj.getRootEntityID()).getContent());

    }
}
