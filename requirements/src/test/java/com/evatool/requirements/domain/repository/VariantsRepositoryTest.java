package com.evatool.requirements.domain.repository;

import com.evatool.requirements.entity.RequirementsVariants;
import com.evatool.requirements.repository.RequirementsVariantsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static com.evatool.requirements.common.TestDataGenerator.getVariant;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class VariantsRepositoryTest {

    @Autowired
    private RequirementsVariantsRepository requirementsVariantsRepository;


    @Test
    public void testFindById_InsertedVariants_ReturnImpact() {
        // given
        var variant = getVariant();
        requirementsVariantsRepository.save(variant);

        // when
        var found = requirementsVariantsRepository.findById(variant.getId()).orElse(null);

        // then
        assertThat(found.getId()).isEqualTo(variant.getId());
    }

    @Test
    public void testSave_InsertedVariants_IdIsNotNull() {
        // given
        var variant = getVariant();

        // when
        requirementsVariantsRepository.save(variant);

        // then
        assertThat(variant.getId()).isNotNull();
    }

    @Test
    public void testSave_InsertedVariants_IdIsUuid() {
        // given
        var variant = getVariant();

        // when
        requirementsVariantsRepository.save(variant);

        // then
        UUID.fromString(variant.getId().toString());
    }

    @Test
    public void testSave_PresetId_Allow() {
        // given
        var variant = getVariant();
        variant.setId(UUID.randomUUID());

        // when

        // then
        requirementsVariantsRepository.save(variant);
    }

    @Test
    public void testDelete_DeletedVariants_ReturnNull() {
        // given
        var variant = getVariant();
        requirementsVariantsRepository.save(variant);

        // when
        requirementsVariantsRepository.delete(variant);
        var found = requirementsVariantsRepository.findById(variant.getId()).orElse(null);

        // then
        assertThat(found).isNull();
    }
}
