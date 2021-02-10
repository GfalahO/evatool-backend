package com.evatool.impact.application.service;

import com.evatool.impact.common.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static com.evatool.impact.common.TestDataGenerator.getDimension;
import static com.evatool.impact.common.TestDataGenerator.getDimensionDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
public class DimensionServiceImplTest {
    @Autowired
    DimensionService dimensionService;

    @BeforeEach
    void clearDatabase() {
        dimensionService.deleteDimensions();
    }

    void insertDimension() {
        var dimension = getDimensionDto();
        dimensionService.createDimension(dimension).getId();
    }

    @Nested
    public class GetById {
        @Test
        public void testGetDimensionById_NonExistingId_ThrowEntityNotFoundException() {
            // given
            var dimension = getDimension();
            dimension.setId(UUID.randomUUID().toString());

            // when

            // then
            assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> dimensionService.findDimensionById(dimension.getId()));
        }
    }

    @Nested
    public class GetAll {
        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5})
        public void testGetAllDimensions_InsertedDimensions_ReturnDimensions(int value) {
            // given
            for (int i = 0; i < value; i++) {
                insertDimension();
            }

            // when
            var dimensions = dimensionService.getAllDimensions();

            // then
            assertThat(dimensions.size()).isEqualTo(value);
        }
    }

    @Nested
    public class Insert {
        @Test
        public void testInsertDimension_InsertedDimension_ReturnInsertedDimension() throws EntityNotFoundException {
            // given
            var dimension = getDimensionDto();

            // when
            var insertedDimension = dimensionService.createDimension(dimension);
            var retrievedDimension = dimensionService.findDimensionById(insertedDimension.getId());

            // then
            assertThat(retrievedDimension).isNotNull();
            assertThat(insertedDimension.getId()).isEqualTo(retrievedDimension.getId());
            assertThat(insertedDimension.getName()).isEqualTo(retrievedDimension.getName());
        }
    }

    @Nested
    public class Update {
        @Test
        public void testUpdateDimension_UpdatedDimension_ReturnUpdatedDimension() throws EntityNotFoundException {
            // given
            var dimension = getDimensionDto();
            var insertedDimension = dimensionService.createDimension(dimension);

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
        public void testUpdateDimension_UpdatedNonExistingId_ThrowEntityNotFoundException() {
            // given
            var dimension = getDimensionDto();
            dimension.setId(UUID.randomUUID().toString());

            // when

            // then
            assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> dimensionService.updateDimension(dimension));
        }
    }

    @Nested
    public class Delete {
        @Test
        public void testDeleteDimensionById_DeleteDimension_ReturnNoDimensions() throws EntityNotFoundException {
            // given
            var dimension = getDimensionDto();

            // when
            var insertedDimension = dimensionService.createDimension(dimension);
            dimensionService.deleteDimensionById(insertedDimension.getId());

            // then
            var dimensions = dimensionService.getAllDimensions();
            assertThat(dimensions.size()).isEqualTo(0);
        }

        @Test
        public void testDeleteDimensionById_DeleteNonExistingId_ReturnHttpStatusNotFound() {
            // given
            var dimension = getDimension();
            dimension.setId(UUID.randomUUID().toString());

            // when

            // then
            assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> dimensionService.deleteDimensionById(dimension.getId()));
        }
    }

    @Nested
    public class DeleteAll {
        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5})
        public void testDeleteAll_InsertDimensions_ReturnNoDimensions(int value) {
            // given
            for (int i = 0; i < value; i++) {
                insertDimension();
            }

            // when
            dimensionService.deleteDimensions();

            // then
            var dimensions = dimensionService.getAllDimensions();
            assertThat(dimensions.size()).isEqualTo(0);
        }
    }
}
