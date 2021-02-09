package com.evatool.requirements.domain.repository;

import com.evatool.requirements.common.TestDataGenerator;
import com.evatool.requirements.repository.RequirementRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.evatool.requirements.common.TestDataGenerator.getRequirement;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RequirementRepositoryTest {
    @Autowired
    private RequirementRepository requirementRepository;

    @Test
    public void testFindById_ExistingRequirement_ReturnRequirement() {
        // given
        var requirement = TestDataGenerator.getRequirement();
        requirementRepository.save(requirement);

        // when
        var found = requirementRepository.findById(requirement.getId()).orElse(null);

        // then
        assertThat(found.getId()).isEqualTo(requirement.getId());
    }

    @Test
    public void testSave_InsertedRequirement_IdIsNotNull() {
        // given
        var requirement = getRequirement();

        // when
        requirementRepository.save(requirement);

        // then
        assertThat(requirement.getId()).isNotNull();
    }

    @Test
    public void testSave_InsertedRequirement_IdIsUuid() {
        // given
        var requirement = getRequirement();

        // when
        requirementRepository.save(requirement);

        // then
        UUID.fromString(requirement.getId().toString());
    }

    @Test
    public void testSave_PresetId_Allow() {
        // given
        var requirement = getRequirement();
        requirement.setId(UUID.randomUUID());

        // when

        // then
        requirementRepository.save(requirement);
    }

    @Test
    public void testDelete_DeletedRequirement_ReturnNull() {
        // given
        var requirement = getRequirement();
        requirementRepository.save(requirement);

        // when
        requirementRepository.delete(requirement);
        var found = requirementRepository.findById(requirement.getId()).orElse(null);

        // then
        assertThat(found).isNull();
    }
}
