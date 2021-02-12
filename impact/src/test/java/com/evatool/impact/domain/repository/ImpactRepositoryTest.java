package com.evatool.impact.domain.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static com.evatool.impact.common.TestDataGenerator.createDummyImpact;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ImpactRepositoryTest {

    @Autowired
    private ImpactRepository impactRepository;

    @Test
    public void testFindById_InsertedImpact_ReturnImpact() {
        // given
        var impact = createDummyImpact();
        impactRepository.save(impact);

        // when
        var found = impactRepository.findById(impact.getId());

        // then
        assertThat(found.isPresent()).isTrue();
    }

    @Test
    public void testSave_InsertedImpact_IdIsNotNull() {
        // given
        var impact = createDummyImpact();

        // when
        impactRepository.save(impact);

        // then
        assertThat(impact.getId()).isNotNull();
    }

    @Test
    public void testSave_InsertedImpact_IdIsUuid() {
        // given
        var impact = createDummyImpact();

        // when
        impactRepository.save(impact);

        // then
        var uuid = UUID.fromString(impact.getId());
        assertThat(uuid.toString()).isEqualTo(impact.getId());
    }

    @Test
    public void testSave_PresetId_Allow() {
        // given
        var impact = createDummyImpact();
        impact.setId(UUID.randomUUID().toString());

        // when

        // then
        impactRepository.save(impact);
    }

    @Test
    public void testSave_UpdatedImpact_ReturnUpdatedDimension() {
        // given
        var impact = createDummyImpact();
        impactRepository.save(impact);
        var newValue = 0.125;

        // when
        impact.setValue(newValue);
        impactRepository.save(impact);
        var impactOptional = impactRepository.findById(impact.getId());

        // then
        assertThat(impactOptional.isPresent()).isTrue();
        assertThat(impactOptional.get().getValue()).isEqualTo(newValue);
    }

    @Test
    public void testDelete_DeletedImpact_ReturnNull() {
        // given
        var impact = createDummyImpact();
        impactRepository.save(impact);

        // when
        impactRepository.delete(impact);
        var found = impactRepository.findById(impact.getId());

        // then
        assertThat(found.isPresent()).isFalse();
    }
}
