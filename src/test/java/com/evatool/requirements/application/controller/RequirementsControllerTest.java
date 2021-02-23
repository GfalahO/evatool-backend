package com.evatool.requirements.application.controller;

import com.evatool.requirements.common.TestDataGenerator;
import com.evatool.requirements.controller.RequirementsController;
import com.evatool.requirements.dto.RequirementDTO;
import com.evatool.requirements.entity.*;
import com.evatool.requirements.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.*;

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

    @Autowired
    RequirementsImpactsRepository requirementsImpactsRepository;

    @Autowired
    RequirementDimensionRepository requirementDimensionRepository;

    @Autowired
    RequirementPointRepository requirementPointRepository;

    @Autowired
    RequirementRepository requirementRepository;

    @Test
    public void testRequirementController_ThrowException() {

        RequirementDimension requirementDimension = getRequirementDimension();
        requirementDimensionRepository.save(requirementDimension);

        RequirementsImpact requirementsImpact = getRequirementsImpacts(requirementDimension);
        requirementsImpactsRepository.save(requirementsImpact);

        Map<UUID,String> impactTitles = new HashMap<>();
        impactTitles.put(requirementsImpact.getId(),requirementsImpact.getTitle());

        RequirementsAnalysis requirementsAnalysis = getRequirementsAnalysis();
        requirementAnalysisRepository.save(requirementsAnalysis);

        RequirementsVariant requirementsVariant = getRequirementsVariant();
        requirementsVariantsRepository.save(requirementsVariant);
        Collection<RequirementsVariant> requirementsVariants = new ArrayList<>();
        requirementsVariants.add(requirementsVariant);

        Map<UUID,Integer> requirementImpactPoints = new HashMap<>();
        requirementImpactPoints.put(requirementsImpact.getId(),1);

        RequirementDTO requirementDTO = getRequirementDTO(impactTitles,requirementsAnalysis.getId());

        //create requirement
        RequirementDTO requirementobj = requirementsController.newRequirement(requirementDTO);

        //check is requirement created
        //assertThat(requirementsController.getRequirementById(requirement.getRootEntityId())).isNotNull();
/*
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
*/
    }

}
