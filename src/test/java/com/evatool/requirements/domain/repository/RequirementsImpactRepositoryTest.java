package com.evatool.requirements.domain.repository;

import com.evatool.requirements.entity.RequirementsImpact;
import com.evatool.requirements.repository.RequirementsImpactsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static com.evatool.requirements.common.TestDataGenerator.getRequirementsImpacts;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RequirementsImpactRepositoryTest {

    @Autowired
    private RequirementsImpactsRepository requirementsImpactsRepository;


    @Test
    public void testFindById_InsertedImpact_ReturnImpact() {
        // given
        RequirementsImpact impact = getRequirementsImpacts();
        requirementsImpactsRepository.save(impact);

        // when
        RequirementsImpact found = requirementsImpactsRepository.findById(impact.getId()).orElse(null);

        // then
        assertThat(found.getId()).isEqualTo(impact.getId());
    }

    @Test
    public void testSave_InsertedImpact_IdIsNotNull() {
        // given
        RequirementsImpact requirementsImpact = getRequirementsImpacts();

        // when
        requirementsImpactsRepository.save(requirementsImpact);

        // then
        assertThat(requirementsImpact.getId()).isNotNull();
    }

    @Test
    public void testSave_InsertedImpact_IdIsUuid() {
        // given
        RequirementsImpact requirementsImpact = getRequirementsImpacts();

        // when
        requirementsImpactsRepository.save(requirementsImpact);

        // then
        UUID.fromString(requirementsImpact.getId().toString());
    }

    @Test
    public void testDelete_DeletedImpact_ReturnNull() {
        // given
        RequirementsImpact requirementsImpact = getRequirementsImpacts();
        requirementsImpactsRepository.save(requirementsImpact);

        // when
        requirementsImpactsRepository.delete(requirementsImpact);
        var found = requirementsImpactsRepository.findById(requirementsImpact.getId()).orElse(null);

        // then
        assertThat(found).isNull();
    }
}
