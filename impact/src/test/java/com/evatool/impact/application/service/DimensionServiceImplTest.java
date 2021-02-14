package com.evatool.impact.application.service;

import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.common.exception.InvalidUuidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static com.evatool.impact.common.TestDataGenerator.createDummyDimension;
import static com.evatool.impact.common.TestDataGenerator.createDummyDimensionDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
class DimensionServiceImplTest {

    @Autowired
    DimensionService dimensionService;

    @BeforeEach
    void clearDatabase() {
        dimensionService.deleteDimensions();
    }

    @Nested
    class GetById {
        @Test
        void testGetDimensionById_NonExistingId_ThrowEntityNotFoundException() {
            // given
            var dimension = createDummyDimension();
            dimension.setId(UUID.randomUUID());

            // when

            // then
            var id = dimension.getId().toString();
            assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> dimensionService.findDimensionById(id));
        }

        @Test
        void testGetDimensionById_NullId_ThrowInvalidUuidException() {
            // given

            // when

            // then
            assertThatExceptionOfType(InvalidUuidException.class).isThrownBy(() -> dimensionService.findDimensionById(null));
        }

        @Test
        void testGetDimensionById_InvalidId_ThrowInvalidUuidException() {
            // given

            // when

            // then
            assertThatExceptionOfType(InvalidUuidException.class).isThrownBy(() -> dimensionService.findDimensionById("invalid id"));
        }
    }

    @Nested
    class GetAll {
        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5})
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
    class Insert {
        @Test
        void testInsertDimension_InsertedDimension_ReturnInsertedDimension() {
            // given
            var dimensionDto = createDummyDimensionDto();

            // when
            var insertedDimension = dimensionService.createDimension(dimensionDto);
            var retrievedDimension = dimensionService.findDimensionById(insertedDimension.getId());

            // then
            assertThat(retrievedDimension).isNotNull();
            assertThat(insertedDimension.getId()).isEqualTo(retrievedDimension.getId());
            assertThat(insertedDimension.getName()).isEqualTo(retrievedDimension.getName());
        }

        // Test error cases
    }

    @Nested
    class Update {
        @Test
        void testUpdateDimension_UpdatedDimension_ReturnUpdatedDimension() {
            // given
            var dimensionDto = createDummyDimensionDto();
            var insertedDimension = dimensionService.createDimension(dimensionDto);

            // when
            var newName = "new_name";
            insertedDimension.setName(newName);

            // then
            dimensionService.updateDimension(insertedDimension);
            var updatedDimension = dimensionService.findDimensionById(insertedDimension.getId());
            assertThat(insertedDimension.getId()).isEqualTo(updatedDimension.getId());
            assertThat(updatedDimension.getName()).isEqualTo(newName);
        }

        @Test
        void testUpdateDimension_UpdatedNonExistingId_ThrowEntityNotFoundException() {
            // given
            var dimensionDto = createDummyDimensionDto();
            dimensionDto.setId(UUID.randomUUID().toString());

            // when

            // then
            assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> dimensionService.updateDimension(dimensionDto));
        }

        // More error test cases
    }

    @Nested
    class Delete {
        @Test
        void testDeleteDimensionById_DeleteDimension_ReturnNoDimensions() {
            // given
            var dimensionDto = createDummyDimensionDto();

            // when
            var insertedDimension = dimensionService.createDimension(dimensionDto);
            dimensionService.deleteDimensionById(insertedDimension.getId());

            // then
            var dimensions = dimensionService.getAllDimensions();
            assertThat(dimensions.size()).isZero();
        }

        @Test
        void testDeleteDimensionById_DeleteNonExistingId_ThrowEntityNotFoundException() {
            // given
            var dimension = createDummyDimension();
            dimension.setId(UUID.randomUUID());

            // when

            // then
            var id = dimension.getId().toString();
            assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> dimensionService.deleteDimensionById(id));
        }

        // More error test cases
    }

    @Nested
    class DeleteAll {
        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5})
        void testDeleteAll_InsertDimensions_ReturnNoDimensions(int value) {
            // given
            for (int i = 0; i < value; i++) {
                var dimensionDto = createDummyDimensionDto();
                dimensionService.createDimension(dimensionDto);
            }

            // when
            dimensionService.deleteDimensions();

            // then
            var dimensions = dimensionService.getAllDimensions();
            assertThat(dimensions.size()).isZero();
        }
    }
}
