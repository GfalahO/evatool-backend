package com.evatool.impact.persistence.repository;

import com.evatool.impact.persistence.TestDataGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

//@RunWith(SpringJUnit4ClassRunner.class) // Works with and without.
@DataJpaTest
public class DimensionRepositoryTest {
    @Autowired
    private DimensionRepository dimensionRepository;

    @Test
    public void testFindById_ExistingDimension_ReturnDimension() {
        // given
        var dimension = TestDataGenerator.getDimension();
        dimensionRepository.save(dimension);

        // when
        var found = dimensionRepository.findById(dimension.getId()).orElse(null);

        // then
        assertThat(found.getId()).isEqualTo(dimension.getId());
    }

    @Test
    public void testFindByName_ExistingDimension_ReturnDimension() {
        // given
        var dimension = TestDataGenerator.getDimension();
        dimensionRepository.save(dimension);

        // when
        var found = dimensionRepository.findByName(dimension.getName()).orElse(null);

        // then
        assertThat(dimension.getName()).isEqualTo(found.getName());
    }

    @Test
    public void testSave_UpdatedName_ReturnUpdatedDimension() {
        // given
        var dimension = TestDataGenerator.getDimension();
        dimensionRepository.save(dimension);
        var oldName = dimension.getName();
        var newName = "care";

        // when
        dimension.setName(newName);
        //dimensionRepository.save(dimension); // This line can be commented and the test still passes
        // Why does save not need to be called?
        // CrudRepository saved changed with setter methods automatically.
        var changedDimension = dimensionRepository.findById(dimension.getId()).orElse(null);

        // then
        assertThat(changedDimension.getName()).isEqualTo(newName);
    }

    @Test
    public void testDelete_DeleteStakeholder_ReturnNull() {
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
    public void testCreateEntity_CreatedStakeholder_ReturnIdIsNull() {
        // given
        var dimension = TestDataGenerator.getDimension();

        // when

        // then
        assertThat(dimension.getId()).isNull();
    }

    @Test
    public void testSave_SavedStakeholder_ReturnIdIsNotNull() {
        // given
        var dimension = TestDataGenerator.getDimension();
        dimensionRepository.save(dimension);

        // when

        // then
        assertThat(dimension.getId()).isNotNull();
    }
}
