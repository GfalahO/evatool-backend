package com.evatool.requirements.domain.repository;

import com.evatool.requirements.entity.RequirementsVariant;
import com.evatool.requirements.repository.RequirementsVariantsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static com.evatool.requirements.common.TestDataGenerator.getRequirementsVariants;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class VariantsRepositoryTest {

    @Autowired
    private RequirementsVariantsRepository requirementsVariantsRepository;


    @Test
    public void testFindById_InsertedVariants_ReturnImpact() {
        // given
        RequirementsVariant requirementsVariant = getRequirementsVariants();
        requirementsVariantsRepository.save(requirementsVariant);

        // when
        RequirementsVariant requirementsVariantFound = requirementsVariantsRepository.findById(requirementsVariant.getId()).orElse(null);

        // then
        assertThat(requirementsVariantFound.getId()).isEqualTo(requirementsVariant.getId());
    }

    @Test
    public void testSave_InsertedVariants_IdIsNotNull() {
        // given
        RequirementsVariant requirementsVariant = getRequirementsVariants();

        // when
        requirementsVariantsRepository.save(requirementsVariant);

        // then
        assertThat(requirementsVariant.getId()).isNotNull();
    }

    @Test
    public void testSave_InsertedVariants_IdIsUuid() {
        // given
        RequirementsVariant requirementsVariant = getRequirementsVariants();

        // when
        requirementsVariantsRepository.save(requirementsVariant);

        // then
        UUID.fromString(requirementsVariant.getId().toString());
    }

    @Test
    public void testDelete_DeletedVariants_ReturnNull() {
        // given
        RequirementsVariant requirementsVariant = getRequirementsVariants();
        requirementsVariantsRepository.save(requirementsVariant);

        // when
        requirementsVariantsRepository.delete(requirementsVariant);
        RequirementsVariant requirementsVariantFound = requirementsVariantsRepository.findById(requirementsVariant.getId()).orElse(null);

        // then
        assertThat(requirementsVariantFound).isNull();
    }
}
