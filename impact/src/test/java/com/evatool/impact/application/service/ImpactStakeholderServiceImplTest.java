package com.evatool.impact.application.service;

import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.common.exception.InvalidUuidException;
import com.evatool.impact.common.exception.PropertyViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static com.evatool.impact.common.TestDataGenerator.*;
import static com.evatool.impact.common.TestDataGenerator.createDummyDimensionDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
class ImpactStakeholderServiceImplTest {
    @Autowired
    ImpactStakeholderService stakeholderService;

    @BeforeEach
    void clearDatabase() {
        stakeholderService.deleteStakeholders();
    }

    @Nested
    class GetById {
        @Test
        void testGetStakeholderById_NonExistingId_ThrowEntityNotFoundException() {
            // given
            var stakeholder = createDummyStakeholder();
            stakeholder.setId(UUID.randomUUID());

            // when

            // then
            var id = stakeholder.getId().toString();
            assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> stakeholderService.findStakeholderById(id));
        }
    }

    @Nested
    class GetAll {
        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5})
        void testGetAllStakeholders_InsertedStakeholders_ReturnStakeholders(int value) {
            // given
            for (int i = 0; i < value; i++) {
                var stakeholderDto = createDummyStakeholderDto();
                stakeholderService.createStakeholder(stakeholderDto);
            }

            // when
            var stakeholders = stakeholderService.getAllStakeholders();

            // then
            assertThat(stakeholders.size()).isEqualTo(value);
        }
    }

    @Nested
    class Insert {
        @Test
        void testInsertStakeholder_InsertedStakeholder_ReturnInsertedStakeholder() {
            // given
            var stakeholderDto = createDummyStakeholderDto();

            // when
            var insertedStakeholder = stakeholderService.createStakeholder(stakeholderDto);
            var retrievedStakeholder = stakeholderService.findStakeholderById(insertedStakeholder.getId());

            // then
            assertThat(retrievedStakeholder).isNotNull();
            assertThat(insertedStakeholder.getId()).isEqualTo(retrievedStakeholder.getId());
            assertThat(insertedStakeholder.getName()).isEqualTo(retrievedStakeholder.getName());
        }

        @Test
        void testInsertStakeholder_NotNullId_ThrowPropertyViolationException() {
            // given
            var stakeholderDto = createDummyStakeholderDto();

            // when
            stakeholderDto.setId("not null");

            // then
            assertThatExceptionOfType(PropertyViolationException.class).isThrownBy(() -> stakeholderService.createStakeholder(stakeholderDto));
        }
    }

    @Nested
    class Update {
        @Test
        void testUpdateStakeholder_UpdatedStakeholder_ReturnUpdatedStakeholder() {
            // given
            var stakeholderDto = createDummyStakeholderDto();
            var insertedStakeholder = stakeholderService.createStakeholder(stakeholderDto);

            // when
            var newName = "new_name";
            insertedStakeholder.setName(newName);

            // then
            stakeholderService.updateStakeholder(insertedStakeholder);
            var updatedStakeholder = stakeholderService.findStakeholderById(insertedStakeholder.getId());
            assertThat(insertedStakeholder.getId()).isEqualTo(updatedStakeholder.getId());
            assertThat(updatedStakeholder.getName()).isEqualTo(newName);
        }

        @Test
        void testUpdateStakeholder_NonExistingId_ThrowEntityNotFoundException() {
            // given
            var stakeholderDto = createDummyStakeholderDto();
            stakeholderDto.setId(UUID.randomUUID().toString());

            // when

            // then
            assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> stakeholderService.updateStakeholder(stakeholderDto));
        }

        @Test
        void testUpdateStakeholder_NullId_ThrowInvalidUuidException() {
            // given
            var stakeholderDto = createDummyStakeholderDto();
            stakeholderDto.setId(null);

            // when

            // then
            assertThatExceptionOfType(InvalidUuidException.class).isThrownBy(() -> stakeholderService.updateStakeholder(stakeholderDto));
        }

        @Test
        void testUpdateStakeholder_InvalidId_ThrowInvalidUuidException() {
            // given
            var stakeholderDto = createDummyStakeholderDto();
            stakeholderDto.setId("invalid id");

            // when

            // then
            assertThatExceptionOfType(InvalidUuidException.class).isThrownBy(() -> stakeholderService.updateStakeholder(stakeholderDto));
        }
    }

    @Nested
    class Delete {
        @Test
        void testDeleteStakeholderById_DeleteStakeholder_ReturnNoStakeholders() {
            // given
            var stakeholderDto = createDummyStakeholderDto();

            // when
            var insertedStakeholder = stakeholderService.createStakeholder(stakeholderDto);
            stakeholderService.deleteStakeholderById(insertedStakeholder.getId());

            // then
            var stakeholders = stakeholderService.getAllStakeholders();
            assertThat(stakeholders.size()).isZero();
        }

        @Test
        void testDeleteStakeholderById_NonExistingId_ThrowEntityNotFoundException() {
            // given
            var stakeholder = createDummyStakeholder();
            stakeholder.setId(UUID.randomUUID());

            // when

            // then
            var id = stakeholder.getId().toString();
            assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> stakeholderService.deleteStakeholderById(id));
        }

        @Test
        void testDeleteStakeholderById_NullId_ThrowInvalidUuidException() {
            // given
            var id = (String) null;

            // when

            // then
            assertThatExceptionOfType(InvalidUuidException.class).isThrownBy(() -> stakeholderService.deleteStakeholderById(id));
        }

        @Test
        void testDeleteStakeholderById_InvalidId_ThrowInvalidUuidException() {
            // given
            var id = "invalid id";

            // when

            // then
            assertThatExceptionOfType(InvalidUuidException.class).isThrownBy(() -> stakeholderService.deleteStakeholderById(id));
        }
    }

    @Nested
    class DeleteAll {
        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5})
        void testDeleteAll_InsertStakeholders_ReturnNoStakeholders(int value) {
            // given
            for (int i = 0; i < value; i++) {
                var stakeholderDto = createDummyStakeholderDto();
                stakeholderService.createStakeholder(stakeholderDto);
            }

            // when
            stakeholderService.deleteStakeholders();

            // then
            var stakeholders = stakeholderService.getAllStakeholders();
            assertThat(stakeholders.size()).isZero();
        }
    }
}
