package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.mapper.DimensionDtoMapper;
import com.evatool.impact.application.dto.mapper.ImpactDtoMapper;
import com.evatool.impact.application.dto.mapper.ImpactStakeholderDtoMapper;
import com.evatool.impact.common.exception.EntityIdMustBeNullException;
import com.evatool.impact.common.exception.EntityIdRequiredException;
import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.domain.entity.Dimension;
import com.evatool.impact.domain.entity.Impact;
import com.evatool.impact.domain.entity.ImpactStakeholder;
import com.evatool.impact.domain.repository.DimensionRepository;
import com.evatool.impact.domain.repository.ImpactRepository;
import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.UUID;

import static com.evatool.impact.common.TestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ImpactServiceImplTest {

    @Autowired
    ImpactService impactService;

    @Autowired
    ImpactRepository impactRepository;

    @Autowired
    DimensionRepository dimensionRepository;

    @Autowired
    ImpactStakeholderRepository stakeholderRepository;

    @BeforeEach
    @AfterAll
    private void clearDatabase() {
        impactRepository.deleteAll();
        stakeholderRepository.deleteAll();
        dimensionRepository.deleteAll();
    }

    private Impact saveFullDummyImpact() {
        var dimension = saveDummyDimension();
        var stakeholder = saveDummyStakeholder();
        var impact = createDummyImpact();
        impact.setDimension(dimension);
        impact.setStakeholder(stakeholder);
        return impactRepository.save(impact);
    }

    private Dimension saveDummyDimension() {
        return dimensionRepository.save(createDummyDimension());
    }

    private ImpactStakeholder saveDummyStakeholder() {
        return stakeholderRepository.save(createDummyStakeholder());
    }

    @Nested
    class GetById {

        @Test
        void testFindImpactById_ExistingImpact_ReturnImpact() {
            // given
            var impact = saveFullDummyImpact();

            // when
            var impactDto = impactService.findImpactById(impact.getId());

            // then
            assertThat(impactDto).isEqualTo(ImpactDtoMapper.toDto(impact));
        }

        @Test
        void testFindImpactById_UnknownId() {
            var id = UUID.randomUUID();
            assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> impactService.findImpactById(id));
        }
    }

    @Nested
    class GetAll {

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5})
        void testGetAllStakeholders_InsertedStakeholders_ReturnStakeholders(int value) {
            // given
            for (int i = 0; i < value; i++) {
                saveFullDummyImpact();
            }

            // when
            var stakeholders = impactService.getAllImpacts();

            // then
            assertThat(stakeholders.size()).isEqualTo(value);
        }
    }

    @Nested
    class Insert {

        @Test
        void testInsertDimension_InsertedDimension_ReturnInsertedDimension() {
            // given
            var impactDto = createDummyImpactDto();
            impactDto.setDimension(DimensionDtoMapper.toDto(saveDummyDimension()));
            impactDto.setStakeholder(ImpactStakeholderDtoMapper.toDto(saveDummyStakeholder()));

            // when
            var insertedImpact = impactService.createImpact(impactDto);
            var retrievedImpact = impactService.findImpactById(insertedImpact.getId());

            // then
            assertThat(insertedImpact).isEqualTo(retrievedImpact);
        }

        @Test
        void testInsertDimension_ExistingId_ThrowEntityIdMustBeNullException() {
            // given
            var impactDto = createDummyImpactDto();

            // when
            impactDto.setId(UUID.randomUUID());

            // then
            assertThatExceptionOfType(EntityIdMustBeNullException.class).isThrownBy(() -> impactService.createImpact(impactDto));
        }
    }

    @Nested
    class Update {

        @Test
        void testUpdateImpact_UpdatedImpact_ReturnUpdatedImpact() {
            // given
            var impact = saveFullDummyImpact();

            // when
            var impactDto = ImpactDtoMapper.toDto(impact);
            var newDescription = "new_desc";
            impactDto.setDescription(newDescription);

            // then
            var insertImpact = impactService.updateImpact(impactDto);
            var updatedImpact = impactService.findImpactById(insertImpact.getId());
            assertThat(updatedImpact.getDescription()).isEqualTo(newDescription);
        }

        @Test
        void testUpdateImpact_NonExistingId_ThrowEntityNotFoundException() {
            // given
            var impactDto = createDummyImpactDto();
            impactDto.setId(UUID.randomUUID());

            // when

            // then
            assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> impactService.updateImpact(impactDto));
        }

        @Test
        void testUpdateImpact_NullId_ThrowEntityIdRequiredException() {
            // given
            var impactDto = createDummyImpactDto();

            // when

            // then
            assertThatExceptionOfType(EntityIdRequiredException.class).isThrownBy(() -> impactService.updateImpact(impactDto));
        }

        @Nested
        class ChildEntity {

        }
    }

    @Nested
    class Delete {

        @Test
        void testDeleteImpactById_DeleteImpact_ReturnNoImpacts() {
            // given
            var impact = saveFullDummyImpact();

            // when
            impactService.deleteImpactById(impact.getId());

            // then
            var impacts = impactService.getAllImpacts();
            assertThat(impacts.size()).isZero();
        }

        @Test
        void testDeleteImpactById_NonExistingId_ThrowEntityNotFoundException() {
            // given
            var impact = createDummyImpact();
            impact.setId(UUID.randomUUID());

            // when

            // then
            var id = impact.getId();
            assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> impactService.deleteImpactById(id));
        }

        @Nested
        class ChildEntity {

            @Test
            void testDeleteChildDimension_DeleteChildEntity_ThrowDataIntegrityViolationException() {
                // given
                var impact = saveFullDummyImpact();

                // when
                var dimension = impact.getDimension();

                // then
                assertThatExceptionOfType(DataIntegrityViolationException.class).isThrownBy(() -> dimensionRepository.delete(dimension));
            }

            @Test
            void testDeleteChildStakeholder_DeleteChildEntity_ThrowDataIntegrityViolationException() {
                // given
                var impact = saveFullDummyImpact();

                // when
                var stakeholder = impact.getStakeholder();

                // then
                assertThatExceptionOfType(DataIntegrityViolationException.class).isThrownBy(() -> stakeholderRepository.delete(stakeholder));
            }
        }
    }

    @Nested
    class DeleteAll {

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5})
        void testDeleteAll_InsertImpacts_ReturnNoImpacts(int value) {
            // given
            for (int i = 0; i < value; i++) {
                saveFullDummyImpact();
            }

            // when
            impactService.deleteImpacts();

            // then
            var impacts = impactService.getAllImpacts();
            assertThat(impacts.size()).isZero();
        }
    }
}
