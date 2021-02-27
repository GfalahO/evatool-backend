package com.evatool.impact.application.service;

import com.evatool.impact.common.exception.EntityIdMustBeNullException;
import com.evatool.impact.common.exception.EntityIdRequiredException;
import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.domain.entity.ImpactStakeholder;
import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static com.evatool.impact.application.dto.mapper.ImpactStakeholderDtoMapper.toDto;
import static com.evatool.impact.common.TestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
class ImpactStakeholderServiceImplTest {

    @Autowired
    ImpactStakeholderService stakeholderService;

    @Autowired
    ImpactStakeholderRepository stakeholderRepository;

    @BeforeEach
    void clearDatabase() {
        stakeholderService.deleteAll();
    }

    private ImpactStakeholder saveFullDummyImpactStakeholder() {
        var stakeholder = createDummyStakeholder();
        return stakeholderRepository.save(stakeholder);
    }

    @Nested
    class GetById {

        @Test
        void testFindDimensionById_ExistingDimension_ReturnDimension() {
            // given
            var stakeholder = saveFullDummyImpactStakeholder();

            // when
            var stakeholderDto = stakeholderService.findById(stakeholder.getId());

            // then
            assertThat(stakeholderDto).isEqualTo(toDto(stakeholder));
        }

        @Test
        void testGetStakeholderById_NonExistingId_ThrowEntityNotFoundException() {
            // given
            var stakeholder = createDummyStakeholder();
            stakeholder.setId(UUID.randomUUID());

            // when

            // then
            var id = stakeholder.getId();
            assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> stakeholderService.findById(id));
        }
    }

    @Nested
    class GetAll {

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5})
        void testGetAllStakeholders_InsertedStakeholders_ReturnStakeholders(int value) {
            // given
            for (int i = 0; i < value; i++) {
                saveFullDummyImpactStakeholder();
            }

            // when
            var stakeholders = stakeholderService.findAll();

            // then
            assertThat(stakeholders.size()).isEqualTo(value);
        }
    }

    @Nested
    class Insert {

        @Test
        void testInsertStakeholder_InsertedStakeholder_ReturnInsertedStakeholder() {
            // given
            var stakeholder = saveFullDummyImpactStakeholder();

            // when
            var stakeholderDto = stakeholderService.findById(stakeholder.getId());

            // then
            assertThat(stakeholderDto).isEqualTo(toDto(stakeholder));
        }

        @Test
        void testInsertStakeholder_ExistingId_ThrowEntityIdMustBeNullException() {
            // given
            var stakeholderDto = createDummyStakeholderDto();

            // when
            stakeholderDto.setId(UUID.randomUUID());

            // then
            assertThatExceptionOfType(EntityIdMustBeNullException.class).isThrownBy(() -> stakeholderService.insert(stakeholderDto));
        }
    }

    @Nested
    class Update {

        @Test
        void testUpdateStakeholder_UpdatedStakeholder_ReturnUpdatedStakeholder() {
            // given
            var stakeholder = saveFullDummyImpactStakeholder();

            // when
            var newName = "new_name";
            stakeholder.setName(newName);
            stakeholderService.update(toDto(stakeholder));
            var stakeholderDto = stakeholderService.findById(stakeholder.getId());

            // then
            assertThat(stakeholderDto.getName()).isEqualTo(newName);
        }

        @Test
        void testUpdateStakeholder_NonExistingId_ThrowEntityNotFoundException() {
            // given
            var stakeholderDto = createDummyStakeholderDto();
            stakeholderDto.setId(UUID.randomUUID());

            // when

            // then
            assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> stakeholderService.update(stakeholderDto));
        }

        @Test
        void testUpdateStakeholder_NullId_ThrowEntityIdRequiredException() {
            // given
            var stakeholderDto = createDummyStakeholderDto();

            // when

            // then
            assertThatExceptionOfType(EntityIdRequiredException.class).isThrownBy(() -> stakeholderService.update(stakeholderDto));
        }
    }

    @Nested
    class Delete {

        @Test
        void testDeleteStakeholderById_DeleteStakeholder_ReturnNoStakeholders() {
            // given
            var stakeholderDto = createDummyStakeholderDto();

            // when
            var insertedStakeholder = stakeholderService.insert(stakeholderDto);
            stakeholderService.deleteById(insertedStakeholder.getId());

            // then
            var stakeholders = stakeholderService.findAll();
            assertThat(stakeholders.size()).isZero();
        }

        @Test
        void testDeleteStakeholderById_NonExistingId_ThrowEntityNotFoundException() {
            // given
            var stakeholder = createDummyStakeholder();
            stakeholder.setId(UUID.randomUUID());

            // when

            // then
            var id = stakeholder.getId();
            assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> stakeholderService.deleteById(id));
        }
    }

    @Nested
    class DeleteAll {

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5})
        void testDeleteAll_InsertStakeholders_ReturnNoStakeholders(int value) {
            // given
            for (int i = 0; i < value; i++) {
                saveFullDummyImpactStakeholder();
            }

            // when
            stakeholderService.deleteAll();

            // then
            var stakeholders = stakeholderService.findAll();
            assertThat(stakeholders.size()).isZero();
        }
    }
}
