package com.evatool.requirements.application.controller;

import com.evatool.requirements.common.TestDataGenerator;
import com.evatool.requirements.controller.RequirementsController;
import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.entity.RequirementsAnalysis;
import com.evatool.requirements.entity.RequirementsVariant;
import com.evatool.requirements.repository.RequirementAnalysisRepository;
import com.evatool.requirements.repository.RequirementsVariantsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import static com.evatool.requirements.common.TestDataGenerator.*;
import static com.evatool.requirements.common.TestDataGenerator.getRequirementsVariant;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RequirementsControllerTest {

    @Autowired
    private RequirementsController requirementsController;

    @Autowired
    private RequirementAnalysisRepository requirementAnalysisRepository;

    @Autowired
    private RequirementsVariantsRepository requirementsVariantsRepository;

    @Test
    public void testRequirementController_ThrowException() {

        RequirementsAnalysis requirementsAnalysis = getRequirementsAnalysis();
        requirementAnalysisRepository.save(requirementsAnalysis);

        RequirementsVariant requirementsVariant = getRequirementsVariant();
        RequirementsVariant requirementsVariant1 = getRequirementsVariant();

        requirementsVariantsRepository.save(requirementsVariant);
        requirementsVariantsRepository.save(requirementsVariant1);

        Collection<RequirementsVariant> requirementsVariants = new ArrayList<>();
        requirementsVariants.add(requirementsVariant);
        requirementsVariants.add(requirementsVariant1);


        //create requirement
        Requirement requirement = requirementsController.newRequirement(getRequirement(requirementsAnalysis,requirementsVariants));

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
        requirementsController.deleteRequirement(idRequirement);

        //check is requirement deleted
        Optional<Requirement> deletedRequirement = requirementsController.getRequirementById(idRequirement);
        assertThat(deletedRequirement.isEmpty()).isEqualTo(true);

    }
}
