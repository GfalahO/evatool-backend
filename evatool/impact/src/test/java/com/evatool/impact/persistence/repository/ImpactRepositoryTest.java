package com.evatool.impact.persistence.repository;

import com.evatool.impact.persistence.TestDataGenerator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static com.evatool.impact.persistence.TestDataGenerator.getImpact;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class ImpactRepositoryTest {
    @Autowired
    private ImpactRepository impactRepository;

    @Test
    public void testFindById_InsertedImpact_ReturnImpact() {
        // given
        var impact = getImpact();
        impactRepository.save(impact);

        // when
        var found = impactRepository.findById(impact.getId()).orElse(null);

        // then
        assertThat(found.getId()).isEqualTo(impact.getId());
    }

    @Test
    public void testSave_InsertedImpact_IdIsNotNull() {
        // given
        var impact = getImpact();

        // when
        impactRepository.save(impact);

        // then
        assertThat(impact.getId()).isNotNull();
    }

    @Test
    public void testSave_InsertedImpact_IdIsUuid() {
        // given
        var impact = getImpact();

        // when
        impactRepository.save(impact);

        // then
        UUID.fromString(impact.getId());
    }

    @Test
    public void testSave_UpdatedImpact_ReturnUpdatedDimension() {
        // given
        var impact = getImpact();
        impactRepository.save(impact);
        var newValue = 0.125;

        // when
        impact.setValue(newValue);
        impactRepository.save(impact);
        var changedDimension = impactRepository.findById(impact.getId()).orElse(null);

        // then
        assertThat(changedDimension.getValue()).isEqualTo(newValue);
    }

    @Test
    public void testDelete_DeletedImpact_ReturnNull() {
        // given
        var impact = getImpact();
        impactRepository.save(impact);

        // when
        impactRepository.delete(impact);
        var found = impactRepository.findById(impact.getId()).orElse(null);

        // then
        assertThat(found).isNull();
    }
}
