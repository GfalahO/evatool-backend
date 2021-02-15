package com.evatool.requirements.domain.repository;

import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.entity.RequirementPoint;
import com.evatool.requirements.entity.RequirementsImpact;
import com.evatool.requirements.repository.RequirementsImpactsRepository;
import com.evatool.requirements.repository.RequirementGRRepository;
import com.evatool.requirements.repository.RequirementRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static com.evatool.requirements.common.TestDataGenerator.getRequirementGR;
import static com.evatool.requirements.common.TestDataGenerator.getRequirementsImpacts;
import static com.evatool.requirements.common.TestDataGenerator.getRequirement;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RequirementPointRepositoryTest {

    @Autowired
    private RequirementGRRepository requirementGRRepository;

    @Autowired
    private RequirementRepository requirementRepository;

    @Autowired
    private RequirementsImpactsRepository requirementsImpactsRepository;

    @Test
    public void testFindById_ExistingRequirementGR_ReturnRequirement() {
        // given
        RequirementsImpact requirementsImpact = getRequirementsImpacts();
        Requirement requirement = getRequirement();
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
        RequirementsImpact requirementsImpact = getRequirementsImpacts();
        Requirement requirement = getRequirement();
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
        RequirementsImpact requirementsImpact = getRequirementsImpacts();
        Requirement requirement = getRequirement();
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
        RequirementsImpact requirementsImpact = getRequirementsImpacts();
        Requirement requirement = getRequirement();
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
