package com.evatool.requirements.domain.repository;

import com.evatool.requirements.common.TestDataGenerator;
import com.evatool.requirements.repository.InpactsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static com.evatool.requirements.common.TestDataGenerator.getImpact;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class InpactsRepositoryTest {

    @Autowired
    private InpactsRepository inpactsRepository;


    @Test
    public void testFindById_InsertedImpact_ReturnImpact() {
        // given
        var impact = getImpact();
        inpactsRepository.save(impact);

        // when
        var found = inpactsRepository.findById(impact.getId()).orElse(null);

        // then
        assertThat(found.getId()).isEqualTo(impact.getId());
    }

    @Test
    public void testSave_InsertedImpact_IdIsNotNull() {
        // given
        var impact = getImpact();

        // when
        inpactsRepository.save(impact);

        // then
        assertThat(impact.getId()).isNotNull();
    }

    @Test
    public void testSave_InsertedImpact_IdIsUuid() {
        // given
        var impact = getImpact();

        // when
        inpactsRepository.save(impact);

        // then
        UUID.fromString(impact.getId().toString());
    }

    @Test
    public void testSave_PresetId_Allow() {
        // given
        var impact = getImpact();
        impact.setId(UUID.randomUUID());

        // when

        // then
        inpactsRepository.save(impact);
    }

    @Test
    public void testSave_UpdatedImpact_ReturnUpdatedDimension() {
        // given
        var impact = getImpact();
        inpactsRepository.save(impact);
        var newValue = 41;

        // when
        impact.setValue(newValue);
        inpactsRepository.save(impact);
        var changedDimension = inpactsRepository.findById(impact.getId()).orElse(null);

        // then
        assertThat(changedDimension.getValue()).isEqualTo(newValue);
    }

    @Test
    public void testDelete_DeletedImpact_ReturnNull() {
        // given
        var impact = getImpact();
        inpactsRepository.save(impact);

        // when
        inpactsRepository.delete(impact);
        var found = inpactsRepository.findById(impact.getId()).orElse(null);

        // then
        assertThat(found).isNull();
    }
}
