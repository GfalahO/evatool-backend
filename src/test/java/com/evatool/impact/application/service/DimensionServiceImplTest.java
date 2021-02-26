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
        dimensionService.deleteDimensions();
    }

    private Dimension saveFullDummyDimension() {
        var dimension = createDummyDimension();
        return dimensionRepository.save(dimension);
    }

    @Nested
    class GetById {

        @Test
        void testFindDimensionById_ExistingDimension_ReturnDimension() {
            // given
            var dimension = saveFullDummyDimension();

            // when
            var dimensionDto = dimensionService.findDimensionById(dimension.getId());

            // then
            assertThat(dimensionDto).isEqualTo(toDto(dimension));
        }

        @Test
        void testGetDimensionById_NonExistingId_ThrowEntityNotFoundException() {
            // given
            var dimension = createDummyDimension();
            dimension.setId(UUID.randomUUID());

            // when

            // then
            var id = dimension.getId();
            assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> dimensionService.findDimensionById(id));
        }
    }

    @Nested
    class GetByType {

        @Test
        void testGetByType_ExistingDimensions_ReturnDimensions() {
            // given
            int n_socialDimensions = 3;
            for (int i = 0; i < n_socialDimensions; i++) {
                var socialDimension = createDummyDimensionDto();
                socialDimension.setType(Dimension.Type.SOCIAL);
                dimensionService.createDimension(socialDimension);
            }

            int n_economicDimensions = 4;
            for (int i = 0; i < n_economicDimensions; i++) {
                var economicDimension = createDummyDimensionDto();
                economicDimension.setType(Dimension.Type.ECONOMIC);
                dimensionService.createDimension(economicDimension);
            }

            // when
            var socialDimensions = dimensionService.findDimensionsByType(Dimension.Type.SOCIAL);
            var economicDimension = dimensionService.findDimensionsByType(Dimension.Type.ECONOMIC);

            // then
            assertThat(socialDimensions.size()).isEqualTo(n_socialDimensions);
            assertThat(economicDimension.size()).isEqualTo(n_economicDimensions);
        }
    }

    @Nested
    class GetAll {

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3})
        void testGetAllDimensions_InsertedDimensions_ReturnDimensions(int value) {
            // given
            for (int i = 0; i < value; i++) {
                var dimensionDto = createDummyDimensionDto();
                dimensionService.createDimension(dimensionDto);
            }

            // when
            var dimensions = dimensionService.getAllDimensions();

            // then
            assertThat(dimensions.size()).isEqualTo(value);
        }
    }

    @Nested
    class GetDimensionTypes {

        @Test
        void testGetAllDimensionTypes_ReturnAllDimensionTypes() {
            // given

            // when
            var dimensionTypes = dimensionService.getAllDimensionTypes();

            // then
            assertThat(dimensionTypes.size()).isEqualTo(Dimension.Type.values().length);
            assertThat(dimensionTypes).isEqualTo(Arrays.asList(Dimension.Type.values()));
        }
    }

    @Nested
    class Insert {

        @Test
        void testInsertDimension_InsertedDimension_ReturnInsertedDimension() {
            // given
            var dimensionDto = createDummyDimensionDto();

            // when
            var insertedDimension = dimensionService.createDimension(dimensionDto);
            var retrievedDimension = dimensionService.findDimensionById(insertedDimension.getId());

            // then
            assertThat(insertedDimension).isEqualTo(retrievedDimension);
        }

        @Test
        void testInsertDimension_ExistingId_ThrowEntityIdMustBeNullException() {
            // given
            var dimensionDto = createDummyDimensionDto();

            // when
            dimensionDto.setId(UUID.randomUUID());

            // then
            assertThatExceptionOfType(EntityIdMustBeNullException.class).isThrownBy(() -> dimensionService.createDimension(dimensionDto));
        }
    }

    @Nested
    class Update {

        @Test
        void testUpdateDimension_UpdatedDimension_ReturnUpdatedDimension() {
            // given
            var dimension = saveFullDummyDimension();

            // when
            var newName = "new_name";
            dimension.setName(newName);
            dimensionService.updateDimension(toDto(dimension));
            var dimensionDto = dimensionService.findDimensionById(dimension.getId());

            // then
            assertThat(dimensionDto.getName()).isEqualTo(newName);
        }

        @Test
        void testUpdateDimension_NonExistingId_ThrowEntityNotFoundException() {
            // given
            var dimensionDto = createDummyDimensionDto();
            dimensionDto.setId(UUID.randomUUID());

            // when

            // then
            assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> dimensionService.updateDimension(dimensionDto));
        }

        @Test
        void testUpdateDimension_NullId_ThrowEntityIdRequiredException() {
            // given
            var dimensionDto = createDummyDimensionDto();

            // when

            // then
            assertThatExceptionOfType(EntityIdRequiredException.class).isThrownBy(() -> dimensionService.updateDimension(dimensionDto));
        }
    }

    @Nested
    class Delete {

        @Test
        void testDeleteDimensionById_DeleteDimension_ReturnNoDimensions() {
            // given
            var dimension = saveFullDummyDimension();

            // when
            dimensionService.deleteDimensionById(dimension.getId());

            // then
            var dimensions = dimensionService.getAllDimensions();
            assertThat(dimensions.size()).isZero();
        }

        @Test
        void testDeleteDimensionById_NonExistingId_ThrowEntityNotFoundException() {
            // given
            var dimension = createDummyDimension();
            dimension.setId(UUID.randomUUID());

            // when

            // then
            var id = dimension.getId();
            assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> dimensionService.deleteDimensionById(id));
        }
    }

    @Nested
    class DeleteAll {

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5})
        void testDeleteAll_InsertDimensions_ReturnNoDimensions(int value) {
            // given
            for (int i = 0; i < value; i++) {
                saveFullDummyDimension();
            }

            // when
            dimensionService.deleteDimensions();

            // then
            var dimensions = dimensionService.getAllDimensions();
            assertThat(dimensions.size()).isZero();
        }
    }
}
