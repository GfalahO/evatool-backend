package com.evatool.analysis.application.controller;


import com.evatool.analysis.api.interfaces.StakeholderController;
import com.evatool.analysis.dto.StakeholderDTO;
import com.evatool.analysis.enums.Dimension;
import com.evatool.analysis.enums.StakeholderLevel;
import com.evatool.analysis.error.exceptions.EntityNotFoundException;
import com.evatool.analysis.model.AnalysisImpacts;
import com.evatool.analysis.model.Stakeholder;
import com.evatool.analysis.repository.AnalysisImpactRepository;
import com.evatool.analysis.repository.StakeholderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static com.evatool.analysis.common.TestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StakeholderControllerTest {

    @Autowired
    private StakeholderController stakeholderController;

    @Autowired
    private AnalysisImpactRepository analysisImpactRepository;

    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Test
    public void testStakeholderController_ThrowException() {

        AnalysisImpacts analysisImpacts = getAnalysisImpacts(Dimension.SAFETY);
        analysisImpactRepository.save(analysisImpacts);

        Stakeholder stakeholder = new Stakeholder("TestName", 1, StakeholderLevel.NATURALPERSON);

        //create stakeholder
        StakeholderDTO stakeholderDTO = getStakeholderDTO(stakeholder.getStakeholderName(), stakeholder.getPriority(), stakeholder.getStakeholderLevel(), stakeholder.getAnalysisImpacts());
        StakeholderDTO stakeholderDTOObj = stakeholderController.addStakeholder(stakeholderDTO).getContent();

        //check is stakeholder created
        assertThat(stakeholderController.getStakeholderById(stakeholderDTOObj.getRootEntityID())).isNotNull();

        //change stakeholder title
        String testName = "TestName";
        stakeholderDTOObj.setStakeholderName(testName);
        stakeholderController.updateStakeholder(stakeholderDTOObj);

        //check is stakeholder title changed
        StakeholderDTO stakeholderAfterUpdate = stakeholderController.getStakeholderById(stakeholderDTOObj.getRootEntityID()).getContent();

        assertThat(stakeholderAfterUpdate.getStakeholderName()).isEqualTo(testName);

        //delete stakeholder
        UUID id = stakeholderDTOObj.getRootEntityID();
        stakeholderController.deleteStakeholder(id);

        //check is stakeholder deleted
        Exception exception = assertThrows(EntityNotFoundException.class, ()-> stakeholderController.getStakeholderById(stakeholderDTOObj.getRootEntityID()).getContent());

    }
}
