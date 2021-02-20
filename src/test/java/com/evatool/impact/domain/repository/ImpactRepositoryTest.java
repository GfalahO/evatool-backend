package com.evatool.impact.domain.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.evatool.impact.common.TestDataGenerator.createDummyImpact;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ImpactRepositoryTest {

    @Autowired
    private ImpactRepository impactRepository;

    @Autowired
    private DimensionRepository dimensionRepository;

    @Autowired
    private ImpactStakeholderRepository stakeholderRepository;

    @Test
    void testFindById_InsertedImpact_ReturnImpact() {
        // given
        var impact = createDummyImpact();
        dimensionRepository.save(impact.getDimension());
        stakeholderRepository.save(impact.getStakeholder());
        impactRepository.save(impact);

        // when
        var found = impactRepository.findById(impact.getId());

        // then
        assertThat(found).isPresent();
    }

    @Test
    void testSave_InsertedImpact_IdIsNotNull() {
        // given
        var impact = createDummyImpact();

        // when
        dimensionRepository.save(impact.getDimension());
        stakeholderRepository.save(impact.getStakeholder());
        impactRepository.save(impact);

        // then
        assertThat(impact.getId()).isNotNull();
    }

    @Test
    void testSave_UpdatedImpact_ReturnUpdatedDimension() {
        // given
        var impact = createDummyImpact();
        dimensionRepository.save(impact.getDimension());
        stakeholderRepository.save(impact.getStakeholder());
        impactRepository.save(impact);
        var newValue = 0.125;

        // when
        impact.setValue(newValue);
        impactRepository.save(impact);
        var impactOptional = impactRepository.findById(impact.getId());

        // then
        assertThat(impactOptional).isPresent();
        assertThat(impactOptional.get().getValue()).isEqualTo(newValue);
    }

    @Test
    void testDelete_DeletedImpact_ReturnNull() {
        // given
        var impact = createDummyImpact();
        dimensionRepository.save(impact.getDimension());
        stakeholderRepository.save(impact.getStakeholder());
        impactRepository.save(impact);

        // when
        impactRepository.delete(impact);
        var found = impactRepository.findById(impact.getId());

        // then
        assertThat(found).isNotPresent();
    }
}
