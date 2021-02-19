package com.evatool.requirements.domain.repository;

import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.entity.RequirementGR;
import com.evatool.requirements.entity.RequirementsImpacts;
import com.evatool.requirements.repository.RequirementGRRepository;
import com.evatool.requirements.repository.RequirementRepository;
import com.evatool.requirements.repository.RequirementsImpactsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static com.evatool.requirements.common.TestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RequirementGRRepositoryTest {

    @Autowired
    private RequirementGRRepository requirementGRRepository;

    @Autowired
    private RequirementRepository requirementRepository;

    @Autowired
    private RequirementsImpactsRepository requirementsImpactsRepository;

    @Test
    public void testFindById_ExistingRequirementGR_ReturnRequirement() {
        // given
        RequirementsImpacts requirementsImpacts = getRequirementsImpacts();
        Requirement requirement = getRequirement();
        requirementRepository.save(requirement);
        requirementsImpactsRepository.save(requirementsImpacts);

        RequirementGR requirementGR = getRequirementGR(requirement,requirementsImpacts);
        requirementGRRepository.save(requirementGR);

        // when
        RequirementGR requirementGRFound = requirementGRRepository.findById(requirementGR.getId()).orElse(null);

        // then
        assertThat(requirementGRFound.getId()).isEqualTo(requirementGR.getId());
    }

    @Test
    public void testSave_InsertedRequirementGR_IdIsNotNull() {
        RequirementsImpacts requirementsImpacts = getRequirementsImpacts();
        Requirement requirement = getRequirement();
        requirementRepository.save(requirement);
        requirementsImpactsRepository.save(requirementsImpacts);
        // given
        RequirementGR requirementGR = getRequirementGR(requirement, requirementsImpacts);

        // when
        requirementGRRepository.save(requirementGR);

        // then
        assertThat(requirementGR.getId()).isNotNull();
    }

    @Test
    public void testSave_InsertedRequirementGR_IdIsUuid() {
        RequirementsImpacts requirementsImpacts = getRequirementsImpacts();
        Requirement requirement = getRequirement();
        requirementRepository.save(requirement);
        requirementsImpactsRepository.save(requirementsImpacts);
        // given
        RequirementGR requirementGR = getRequirementGR(requirement, requirementsImpacts);

        // when
        requirementGRRepository.save(requirementGR);

        // then
        UUID.fromString(requirementGR.getId().toString());
    }

    @Test
    public void testDelete_DeletedRequirementGR_ReturnNull() {
        RequirementsImpacts requirementsImpacts = getRequirementsImpacts();
        Requirement requirement = getRequirement();
        requirementRepository.save(requirement);
        requirementsImpactsRepository.save(requirementsImpacts);
        // given
        RequirementGR requirementGR = getRequirementGR(requirement, requirementsImpacts);
        requirementGRRepository.save(requirementGR);

        // when
        requirementGRRepository.delete(requirementGR);
        RequirementGR requirementGRFound = requirementGRRepository.findById(requirementGR.getId()).orElse(null);

        // then
        assertThat(requirementGRFound).isNull();
    }
}
