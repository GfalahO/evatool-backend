package com.evatool.requirements.domain.repository;

import com.evatool.requirements.repository.RequirementGRRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static com.evatool.requirements.common.TestDataGenerator.getRequirementGR;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RequirementGRRepositoryTest {

    @Autowired
    private RequirementGRRepository requirementGRRepository;




    @Test
    public void testFindById_ExistingRequirementGR_ReturnRequirement() {
        // given
        var requirementGR = getRequirementGR();
        requirementGRRepository.save(requirementGR);

        // when
        var found = requirementGRRepository.findById(requirementGR.getId()).orElse(null);

        // then
        assertThat(found.getId()).isEqualTo(requirementGR.getId());
    }

    @Test
    public void testSave_InsertedRequirementGR_IdIsNotNull() {
        // given
        var requirementGR = getRequirementGR();

        // when
        requirementGRRepository.save(requirementGR);

        // then
        assertThat(requirementGR.getId()).isNotNull();
    }

    @Test
    public void testSave_InsertedRequirementGR_IdIsUuid() {
        // given
        var requirementGR = getRequirementGR();

        // when
        requirementGRRepository.save(requirementGR);

        // then
        UUID.fromString(requirementGR.getId().toString());
    }

    @Test
    public void testSave_PresetId_Allow() {
        // given
        var requirementGR = getRequirementGR();
        requirementGR.setId(UUID.randomUUID());

        // when

        // then
        requirementGRRepository.save(requirementGR);
    }

    @Test
    public void testDelete_DeletedRequirementGR_ReturnNull() {
        // given
        var requirementGR = getRequirementGR();
        requirementGRRepository.save(requirementGR);

        // when
        requirementGRRepository.delete(requirementGR);
        var found = requirementGRRepository.findById(requirementGR.getId()).orElse(null);

        // then
        assertThat(found).isNull();
    }
}
