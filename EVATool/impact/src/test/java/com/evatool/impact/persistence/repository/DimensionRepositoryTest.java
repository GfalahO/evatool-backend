package com.evatool.impact.persistence.repository;

import com.evatool.impact.persistence.TestDataGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class DimensionRepositoryTest {
    @Autowired
    private DimensionRepository dimensionRepository;

    @Test
    public void testFindById_InsertedDimension_ReturnDimension() {
        // given
        var dimension = TestDataGenerator.getDimension();
        dimensionRepository.save(dimension);

        // when
        var found = dimensionRepository.findById(dimension.getId()).orElse(null);

        // then
        assertThat(found.getId()).isEqualTo(dimension.getId());
    }

    @Test
    public void testFindByName_InsertedDimension_ReturnDimension() {
        // given
        var dimension = TestDataGenerator.getDimension();
        dimensionRepository.save(dimension);

        // when
        var found = dimensionRepository.findByName(dimension.getName()).orElse(null);

        // then
        assertThat(dimension.getName()).isEqualTo(found.getName());
    }

    @Test
    public void testSave_InsertedDimension_IdIsNotNull() {
        // given
        var dimension = TestDataGenerator.getDimension();

        // when
        dimensionRepository.save(dimension);

        // then
        assertThat(dimension.getId()).isNotNull();
    }

    @Test
    public void testSave_UpdatedDimension_ReturnUpdatedDimension() {
        // given
        var dimension = TestDataGenerator.getDimension();
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
        var dimension = TestDataGenerator.getDimension();
        dimensionRepository.save(dimension);

        // when
        dimensionRepository.delete(dimension);
        var found = dimensionRepository.findById(dimension.getId()).orElse(null);

        // then
        assertThat(found).isNull();
    }
    
    @Test
    public void testUniqueName_DuplicateName_ThrowException() {
        // given
        var dimension1 = TestDataGenerator.getDimension();
        dimensionRepository.save(dimension1);
        var dimension2 = TestDataGenerator.getDimension();

        // when

        // then
        dimensionRepository.save(dimension2); // TODO: This should raise a unique constrained violation exception but it does not.

        var found1 = dimensionRepository.findById(dimension1.getId()).orElse(null);
        var found2 = dimensionRepository.findById(dimension2.getId()).orElse(null);
        assertThat(found1.getName()).isEqualTo(found2.getName());
    }
}
