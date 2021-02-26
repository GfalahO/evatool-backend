package com.evatool.analysis.application.controller;


import com.evatool.analysis.api.interfaces.StakeholderController;
import com.evatool.analysis.model.Stakeholder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static com.evatool.analysis.common.TestDataGenerator.getStakholder;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StakeholderControllerTest {

    @Autowired
    private StakeholderController stakeholderController;

    @Test
    public void testStakeholderController() {
//
//        //create stakeholder
//        Stakeholder stakeholder = stakeholderController.addStakeholder(getStakholder());
//
//        //check is stakeholder created
//        assertThat(stakeholderController.getStakeholderById(stakeholder.getStakeholderId()).get()).isNotNull();
//
//        //change stakeholder title
//        String testTitle = "Stani";
//        stakeholder.setStakeholderName(testTitle);
//        stakeholderController.updateStakeholder(stakeholder);
//
//        //check is stakeholder title changed
//        Optional<Stakeholder> stakeholderAfterUpdate = stakeholderController.getStakeholderById(stakeholder.getStakeholderId());
//
//        assertThat(stakeholderAfterUpdate.get().getStakeholderName()).isEqualTo(testTitle);
//
//        assertThat(stakeholderAfterUpdate.get().getStakeholderName()).isEqualTo(testTitle);
//
//        // delete stakeholder
//        UUID idStakeholder = stakeholderAfterUpdate.get().getStakeholderId();
//        stakeholderController.deleteStakeholder(idStakeholder);
//
//        //check is stakeholder deleted
//        Optional<Stakeholder> deletedStakeholder = stakeholderController.getStakeholderById(idStakeholder);
//        assertThat(deletedStakeholder.isEmpty()).isEqualTo(true);
    }
}
