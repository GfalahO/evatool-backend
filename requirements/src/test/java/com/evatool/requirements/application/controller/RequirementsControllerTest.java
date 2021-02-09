package com.evatool.requirements.application.controller;

import com.evatool.requirements.controller.RequirementsController;
import com.evatool.requirements.entity.Requirement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Optional;
import java.util.UUID;

import static com.evatool.requirements.common.TestDataGenerator.getRequirement;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RequirementsControllerTest {

    @Autowired
    private RequirementsController requirementsController;

    @Test
    public void testRequirementController_ThrowException() {

        //create requirement
        Requirement requirement = requirementsController.newRequirement(getRequirement());

        //check is requirement created
        assertThat(requirementsController.getRequirementById(requirement.getId()).get()).isNotNull();

        //change requirement title
        String testTitle = "TestTitle";
        requirement.setTitle(testTitle);
        requirementsController.updateRequirement(requirement);

        //check is requirement title changed
        Optional<Requirement> requirementAfterUpdate = requirementsController.getRequirementById(requirement.getId());

        assertThat(requirementAfterUpdate.get().getTitle()).isEqualTo(testTitle);

        //delete requirement
        UUID idRequirement = requirement.getId();
        requirementsController.deleteRequirement(requirement);

        //check is requirement deleted
        Optional<Requirement> deletedRequirement = requirementsController.getRequirementById(idRequirement);
        assertThat(deletedRequirement.isEmpty()).isEqualTo(true);

    }
}
