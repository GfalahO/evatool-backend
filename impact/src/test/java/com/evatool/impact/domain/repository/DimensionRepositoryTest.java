package com.evatool.impact.domain.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static com.evatool.impact.common.TestDataGenerator.createDummyDimension;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class DimensionRepositoryTest {

    @Autowired
    private DimensionRepository dimensionRepository;

    @Test
    public void testFindById_InsertedDimension_ReturnDimension() {
        // given
        var dimension = createDummyDimension();
        dimensionRepository.save(dimension);

        // when
        var found = dimensionRepository.findById(dimension.getId()).orElse(null);

        // then
        assertThat(found.getId()).isEqualTo(dimension.getId());
    }

    @Test
    public void testFindByName_InsertedDimension_ReturnDimension() {
        // given
        var dimension = createDummyDimension();
        dimensionRepository.save(dimension);

        // when
        var found = dimensionRepository.findByName(dimension.getName()).orElse(null);

        // then
        assertThat(dimension.getName()).isEqualTo(found.getName());
    }

    @Test
    public void testSave_InsertedDimension_IdIsNotNull() {
        // given
        var dimension = createDummyDimension();

        // when
        dimensionRepository.save(dimension);

        // then
        assertThat(dimension.getId()).isNotNull();
    }

    @Test
    public void testSave_InsertedDimension_IdIsUuid() {
        // given
        var dimension = createDummyDimension();

        // when
        dimensionRepository.save(dimension);

        // then
        UUID.fromString(dimension.getId());
    }

    @Test
    public void testSave_PresetId_Allow() {
        // given
        var dimension = createDummyDimension();
        dimension.setId(UUID.randomUUID().toString());

        // when

        // then
        dimensionRepository.save(dimension);
    }

    @Test
    public void testSave_UpdatedDimension_ReturnUpdatedDimension() {
        // given
        var dimension = createDummyDimension();
        dimensionRepository.save(dimension);
        var newName = "new_name";

        // when
        dimension.setName(newName);
        dimensionRepository.save(dimension);
        var changedDimension = dimensionRepository.findById(dimension.getId()).orElse(null);

        // then
        assertThat(changedDimension.getName()).isEqualTo(newName);
    }

    @Test
    public void testDelete_DeletedDimension_ReturnNull() {
        // given
        var dimension = createDummyDimension();
        dimensionRepository.save(dimension);

        // when
        dimensionRepository.delete(dimension);
        var found = dimensionRepository.findById(dimension.getId()).orElse(null);

        // then
        assertThat(found).isNull();
    }
}
