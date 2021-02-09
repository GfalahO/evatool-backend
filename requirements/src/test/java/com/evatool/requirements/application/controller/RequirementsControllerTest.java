package com.evatool.requirements.application.controller;

import com.evatool.requirements.controller.RequirementsController;
import com.evatool.requirements.dto.RequirementDTO;
import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.repository.RequirementRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Optional;
import java.util.UUID;

import static com.evatool.requirements.common.TestDataGenerator.getRequirement;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RequirementsControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RequirementsController requirementsController;

    @Test
    public void testRequirementController_ThrowException() {

        //create requirement
        var requirement = requirementsController.newRequirement(getRequirement());

        //check is requirement created
        assertThat(requirementsController.getRequirementById(requirement.getId()).get()).isNotNull();

        //change requirement title
        var testTitle = "TestTitle";
        requirement.setTitel(testTitle);
        requirementsController.updateRequirement(requirement);

        //check is requirement title changed
        Optional<Requirement> requirementAfterUpdate = requirementsController.getRequirementById(requirement.getId());

        assertThat(requirementAfterUpdate.get().getTitel()).isEqualTo(testTitle);

        //delete requirement
        UUID idRequirement = requirement.getId();
        requirementsController.deleteRequirement(requirement);

        //check is requirement deleted
        Optional<Requirement> deletedRequirement = requirementsController.getRequirementById(idRequirement);
        assertThat(deletedRequirement.isEmpty()).isEqualTo(true);

    }
}
