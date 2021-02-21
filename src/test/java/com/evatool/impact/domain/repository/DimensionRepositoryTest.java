package com.evatool.impact.domain.repository;

import com.evatool.impact.domain.entity.Dimension;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static com.evatool.impact.common.TestDataGenerator.createDummyDimension;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DimensionRepositoryTest {

    @Autowired
    private DimensionRepository dimensionRepository;

    @Test
    void testFindById_InsertedDimension_ReturnDimension() {
        // given
        var dimension = createDummyDimension();
        dimensionRepository.save(dimension);

        // when
        var found = dimensionRepository.findById(dimension.getId());

        // then
        assertThat(found).isPresent();
    }

    @Test
    void testFindByName_InsertedDimension_ReturnDimension() {
        // given
        var dimension = createDummyDimension();
        dimensionRepository.save(dimension);

        // when
        var found = dimensionRepository.findByName(dimension.getName());

        // then
        assertThat(found).isPresent();
    }

    @Test
    void testSave_InsertedDimension_IdIsNotNull() {
        // given
        var dimension = createDummyDimension();

        // when
        dimensionRepository.save(dimension);

        // then
        assertThat(dimension.getId()).isNotNull();
    }

    @Test
    void testSave_UpdatedDimension_ReturnUpdatedDimension() {
        // given
        var dimension = createDummyDimension();
        dimensionRepository.save(dimension);
        var newName = "new_name";

        // when
        dimension.setName(newName);
        dimensionRepository.save(dimension);
        var changedDimension = dimensionRepository.findById(dimension.getId()).orElse(null);

        // then
        assertThat(changedDimension).isNotNull();
        assertThat(changedDimension.getName()).isEqualTo(newName);
    }

    @Test
    void testDelete_DeletedDimension_ReturnNull() {
        // given
        var dimension = createDummyDimension();
        dimensionRepository.save(dimension);

        // when
        dimensionRepository.delete(dimension);
        var found = dimensionRepository.findById(dimension.getId()).orElse(null);

        // then
        assertThat(found).isNull();
    }

    @Test
    void testFindByType_ExistingDimensions_ReturnDimensions() {
        // given
        int n_socialDimensions = 3;
        for (int i = 0; i < n_socialDimensions; i++) {
            var socialDimension = createDummyDimension();
            socialDimension.setType(Dimension.Type.SOCIAL);
            dimensionRepository.save(socialDimension);
        }

        int n_economicDimensions = 4;
        for (int i = 0; i < n_economicDimensions; i++) {
            var economicDimension = createDummyDimension();
            economicDimension.setType(Dimension.Type.ECONOMIC);
            dimensionRepository.save(economicDimension);
        }

        // when
        var socialDimensions = dimensionRepository.findDimensionsByType(Dimension.Type.SOCIAL);
        var economicDimension = dimensionRepository.findDimensionsByType(Dimension.Type.ECONOMIC);

        // then
        assertThat(socialDimensions.size()).isEqualTo(n_socialDimensions);
        assertThat(economicDimension.size()).isEqualTo(n_economicDimensions);
    }

    @Nested
    class SuperEntityUuidGeneratorTest {

        @Test
        void testPersist_NullId_Allow() {
            // given
            var superEntity = createDummyDimension();

            // when
            var savedSuperEntity = dimensionRepository.save(superEntity);

            // then
            assertThat(savedSuperEntity.getId()).isNotNull();
        }

        @Test
        void testPersist_NotNullId_Allow() {
            // given
            var superEntity = createDummyDimension();
            var id = UUID.randomUUID();
            superEntity.setId(id);

            // when
            var savedSuperEntity = dimensionRepository.save(superEntity);

            // then
            assertThat(savedSuperEntity.getId()).isNotNull();
            assertThat(savedSuperEntity.getId()).isEqualTo(id);
        }
    }
}
