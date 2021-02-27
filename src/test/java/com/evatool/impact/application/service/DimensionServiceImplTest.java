package com.evatool.impact.application.service;

import com.evatool.impact.common.exception.EntityIdMustBeNullException;
import com.evatool.impact.common.exception.EntityIdRequiredException;
import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.domain.entity.Dimension;
import com.evatool.impact.domain.repository.DimensionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.UUID;

import static com.evatool.impact.application.dto.mapper.DimensionDtoMapper.toDto;
import static com.evatool.impact.common.TestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
class DimensionServiceImplTest {

    @Autowired
    DimensionService dimensionService;

    @Autowired
    DimensionRepository dimensionRepository;

    @BeforeEach
    void clearDatabase() {
        dimensionService.deleteAll();
    }

    private Dimension saveFullDummyDimension() {
        var dimension = createDummyDimension();
        return dimensionRepository.save(dimension);
    }

    @Nested
    class FindById {

        @Test
        void testFindById_ExistingEntity_ReturnEntity() {
            // given
            var dimension = saveFullDummyDimension();

            // when
            var dimensionDto = dimensionService.findById(dimension.getId());

            // then
            assertThat(dimensionDto).isEqualTo(toDto(dimension));
        }

        @Test
        void testFindById_NonExistingId_ThrowEntityNotFoundException() {
            // given
            var dimension = createDummyDimension();
            dimension.setId(UUID.randomUUID());

            // when

            // then
            var id = dimension.getId();
            assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> dimensionService.findById(id));
        }
    }

    @Nested
    class FindByType {

        @Test
        void testFindByType_ExistingEntities_ReturnEntities() {
            // given
            int n_socialDimensions = 3;
            for (int i = 0; i < n_socialDimensions; i++) {
                var socialDimension = createDummyDimensionDto();
                socialDimension.setType(Dimension.Type.SOCIAL);
                dimensionService.create(socialDimension);
            }

            int n_economicDimensions = 4;
            for (int i = 0; i < n_economicDimensions; i++) {
                var economicDimension = createDummyDimensionDto();
                economicDimension.setType(Dimension.Type.ECONOMIC);
                dimensionService.create(economicDimension);
            }

            // when
            var socialDimensions = dimensionService.findAllByType(Dimension.Type.SOCIAL);
            var economicDimension = dimensionService.findAllByType(Dimension.Type.ECONOMIC);

            // then
            assertThat(socialDimensions.size()).isEqualTo(n_socialDimensions);
            assertThat(economicDimension.size()).isEqualTo(n_economicDimensions);
        }
    }

    @Nested
    class FindAll {

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3})
        void testFindAll_InsertedEntities_ReturnEntities(int value) {
            // given
            for (int i = 0; i < value; i++) {
                var dimensionDto = createDummyDimensionDto();
                dimensionService.create(dimensionDto);
            }

            // when
            var dimensions = dimensionService.findAll();

            // then
            assertThat(dimensions.size()).isEqualTo(value);
        }
    }

    @Nested
    class FindAllByTypes {

        @Test
        void testFindAllTypes_ReturnAllTypes() {
            // given

            // when
            var dimensionTypes = dimensionService.findAllTypes();

            // then
            assertThat(dimensionTypes.size()).isEqualTo(Dimension.Type.values().length);
            assertThat(dimensionTypes).isEqualTo(Arrays.asList(Dimension.Type.values()));
        }
    }

    @Nested
    class Create {

        @Test
        void testCreate_InsertedEntity_ReturnInsertedEntity() {
            // given
            var dimensionDto = createDummyDimensionDto();

            // when
            var insertedDimension = dimensionService.create(dimensionDto);
            var retrievedDimension = dimensionService.findById(insertedDimension.getId());

            // then
            assertThat(insertedDimension).isEqualTo(retrievedDimension);
        }

        @Test
        void testInsert_ExistingId_ThrowEntityIdMustBeNullException() {
            // given
            var dimensionDto = createDummyDimensionDto();

            // when
            dimensionDto.setId(UUID.randomUUID());

            // then
            assertThatExceptionOfType(EntityIdMustBeNullException.class).isThrownBy(() -> dimensionService.create(dimensionDto));
        }
    }

    @Nested
    class Update {

        @Test
        void testUpdate_UpdatedEntity_ReturnUpdatedEntity() {
            // given
            var dimension = saveFullDummyDimension();

            // when
            var newName = "new_name";
            dimension.setName(newName);
            dimensionService.update(toDto(dimension));
            var dimensionDto = dimensionService.findById(dimension.getId());

            // then
            assertThat(dimensionDto.getName()).isEqualTo(newName);
        }

        @Test
        void testUpdate_NonExistingId_ThrowEntityNotFoundException() {
            // given
            var dimensionDto = createDummyDimensionDto();
            dimensionDto.setId(UUID.randomUUID());

            // when

            // then
            assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> dimensionService.update(dimensionDto));
        }

        @Test
        void testUpdate_NullId_ThrowEntityIdRequiredException() {
            // given
            var dimensionDto = createDummyDimensionDto();

            // when

            // then
            assertThatExceptionOfType(EntityIdRequiredException.class).isThrownBy(() -> dimensionService.update(dimensionDto));
        }
    }

    @Nested
    class Delete {

        @Test
        void testDeleteById_DeleteEntity_ReturnNoEntities() {
            // given
            var dimension = saveFullDummyDimension();

            // when
            dimensionService.deleteById(dimension.getId());

            // then
            var dimensions = dimensionService.findAll();
            assertThat(dimensions.size()).isZero();
        }

        @Test
        void testDeleteById_NonExistingId_ThrowEntityNotFoundException() {
            // given
            var dimension = createDummyDimension();
            dimension.setId(UUID.randomUUID());

            // when

            // then
            var id = dimension.getId();
            assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> dimensionService.deleteById(id));
        }
    }

    @Nested
    class DeleteAll {

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5})
        void testDeleteAll_InsertEntities_ReturnNoEntities(int value) {
            // given
            for (int i = 0; i < value; i++) {
                saveFullDummyDimension();
            }

            // when
            dimensionService.deleteAll();

            // then
            var dimensions = dimensionService.findAll();
            assertThat(dimensions.size()).isZero();
        }
    }
}
