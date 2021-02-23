package com.evatool.requirements.domain.repository;

import com.evatool.requirements.entity.*;
import com.evatool.requirements.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import static com.evatool.requirements.common.TestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RequirementPointRepositoryTest {

    @Autowired
    private RequirementGRRepository requirementGRRepository;

    @Autowired
    private RequirementRepository requirementRepository;

    @Autowired
    private RequirementsImpactsRepository requirementsImpactsRepository;

    @Autowired
    private RequirementDimensionRepository requirementDimensionRepository;

    @Autowired
    private RequirementAnalysisRepository requirementAnalysisRepository;

    @Autowired
    private RequirementsVariantsRepository requirementsVariantsRepository;

    @Test
    public void testFindById_ExistingRequirementGR_ReturnRequirement() {
        // given
        RequirementDimension requirementDimension = getRequirementDimension();
        requirementDimensionRepository.save(requirementDimension);

        RequirementsAnalysis requirementsAnalysis = getRequirementsAnalysis();
        requirementAnalysisRepository.save(requirementsAnalysis);

        RequirementsVariant requirementsVariant = getRequirementsVariant();
        RequirementsVariant requirementsVariant1 = getRequirementsVariant();

        requirementsVariantsRepository.save(requirementsVariant);
        requirementsVariantsRepository.save(requirementsVariant1);

        Collection<RequirementsVariant> requirementsVariants = new ArrayList<>();
        requirementsVariants.add(requirementsVariant);
        requirementsVariants.add(requirementsVariant1);


        RequirementsImpact requirementsImpact = getRequirementsImpacts(requirementDimension);
        Requirement requirement = getRequirement(requirementsAnalysis,requirementsVariants);
        requirementRepository.save(requirement);
        requirementsImpactsRepository.save(requirementsImpact);

        RequirementPoint requirementPoint = getRequirementGR(requirement, requirementsImpact);
        requirementGRRepository.save(requirementPoint);

        // when
        RequirementPoint requirementPointFound = requirementGRRepository.findById(requirementPoint.getId()).orElse(null);

        // then
        assertThat(requirementPointFound.getId()).isEqualTo(requirementPoint.getId());
    }

    @Test
    public void testSave_InsertedRequirementGR_IdIsNotNull() {

        RequirementDimension requirementDimension = getRequirementDimension();
        requirementDimensionRepository.save(requirementDimension);

        RequirementsAnalysis requirementsAnalysis = getRequirementsAnalysis();
        requirementAnalysisRepository.save(requirementsAnalysis);

        RequirementsVariant requirementsVariant = getRequirementsVariant();
        RequirementsVariant requirementsVariant1 = getRequirementsVariant();

        requirementsVariantsRepository.save(requirementsVariant);
        requirementsVariantsRepository.save(requirementsVariant1);

        Collection<RequirementsVariant> requirementsVariants = new ArrayList<>();
        requirementsVariants.add(requirementsVariant);
        requirementsVariants.add(requirementsVariant1);


        RequirementsImpact requirementsImpact = getRequirementsImpacts(requirementDimension);
        Requirement requirement = getRequirement(requirementsAnalysis,requirementsVariants);
        requirementRepository.save(requirement);
        requirementsImpactsRepository.save(requirementsImpact);
        // given
        RequirementPoint requirementPoint = getRequirementGR(requirement, requirementsImpact);

        // when
        requirementGRRepository.save(requirementPoint);

        // then
        assertThat(requirementPoint.getId()).isNotNull();
    }

    @Test
    public void testSave_InsertedRequirementGR_IdIsUuid() {

        RequirementDimension requirementDimension = getRequirementDimension();
        requirementDimensionRepository.save(requirementDimension);

        RequirementsAnalysis requirementsAnalysis = getRequirementsAnalysis();
        requirementAnalysisRepository.save(requirementsAnalysis);

        RequirementsVariant requirementsVariant = getRequirementsVariant();
        RequirementsVariant requirementsVariant1 = getRequirementsVariant();

        requirementsVariantsRepository.save(requirementsVariant);
        requirementsVariantsRepository.save(requirementsVariant1);

        Collection<RequirementsVariant> requirementsVariants = new ArrayList<>();
        requirementsVariants.add(requirementsVariant);
        requirementsVariants.add(requirementsVariant1);

        RequirementsImpact requirementsImpact = getRequirementsImpacts(requirementDimension);
        Requirement requirement = getRequirement(requirementsAnalysis,requirementsVariants);
        requirementRepository.save(requirement);
        requirementsImpactsRepository.save(requirementsImpact);
        // given
        RequirementPoint requirementPoint = getRequirementGR(requirement, requirementsImpact);

        // when
        requirementGRRepository.save(requirementPoint);

        // then
        UUID.fromString(requirementPoint.getId().toString());
    }

    @Test
    public void testDelete_DeletedRequirementGR_ReturnNull() {

        RequirementDimension requirementDimension = getRequirementDimension();
        requirementDimensionRepository.save(requirementDimension);

        RequirementsAnalysis requirementsAnalysis = getRequirementsAnalysis();
        requirementAnalysisRepository.save(requirementsAnalysis);

        RequirementsVariant requirementsVariant = getRequirementsVariant();
        RequirementsVariant requirementsVariant1 = getRequirementsVariant();

        requirementsVariantsRepository.save(requirementsVariant);
        requirementsVariantsRepository.save(requirementsVariant1);

        Collection<RequirementsVariant> requirementsVariants = new ArrayList<>();
        requirementsVariants.add(requirementsVariant);
        requirementsVariants.add(requirementsVariant1);

        RequirementsImpact requirementsImpact = getRequirementsImpacts(requirementDimension);
        Requirement requirement = getRequirement(requirementsAnalysis,requirementsVariants);
        requirementRepository.save(requirement);
        requirementsImpactsRepository.save(requirementsImpact);
        // given
        RequirementPoint requirementPoint = getRequirementGR(requirement, requirementsImpact);
        requirementGRRepository.save(requirementPoint);

        // when
        requirementGRRepository.delete(requirementPoint);
        RequirementPoint requirementPointFound = requirementGRRepository.findById(requirementPoint.getId()).orElse(null);

        // then
        assertThat(requirementPointFound).isNull();
    }
}
