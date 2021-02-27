package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.mapper.DimensionDtoMapper;
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

import java.util.UUID;

import static com.evatool.impact.application.dto.mapper.ImpactDtoMapper.toDto;
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
    class FindById {

        @Test
        void testFindById_ExistingImpact_ReturnImpact() {
            // given
            var impact = saveFullDummyImpact();

            // when
            var impactDto = impactService.findById(impact.getId());

            // then
            assertThat(impactDto).isEqualTo(toDto(impact));
        }

        @Test
        void testFindById_NonExistingId_ThrowEntityNotFoundException() {
            // given
            var id = UUID.randomUUID();

            // when

            // then
            assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> impactService.findById(id));
        }
    }

    @Nested
    class FindAll {

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5})
        void testFindAll_ExistingImpacts_ReturnImpact(int value) {
            // given
            for (int i = 0; i < value; i++) {
                saveFullDummyImpact();
            }

            // when
            var impacts = impactService.findAll();

            // then
            assertThat(impacts.size()).isEqualTo(value);
        }
    }

    @Nested
    class Create {

        @Test
        void testCreate_CreatedImpact_ReturnCreatedImpact() {
            // given
            var impactDto = createDummyImpactDto();
            impactDto.setDimension(DimensionDtoMapper.toDto(saveDummyDimension()));
            impactDto.setStakeholder(ImpactStakeholderDtoMapper.toDto(saveDummyStakeholder()));

            // when
            var createdImpact = impactService.create(impactDto);
            var retrievedImpact = impactService.findById(createdImpact.getId());

            // then
            assertThat(createdImpact).isEqualTo(retrievedImpact);
        }

        @Test
        void testCreate_ExistingId_ThrowEntityIdMustBeNullException() {
            // given
            var impactDto = createDummyImpactDto();

            // when
            impactDto.setId(UUID.randomUUID());

            // then
            assertThatExceptionOfType(EntityIdMustBeNullException.class).isThrownBy(() -> impactService.create(impactDto));
        }
    }

    @Nested
    class Update {

        @Test
        void testUpdate_UpdatedImpact_ReturnUpdatedImpact() {
            // given
            var impact = saveFullDummyImpact();

            // when
            var newDescription = "new_desc";
            impact.setDescription(newDescription);
            impactService.update(toDto(impact));
            var impactDto = impactService.findById(impact.getId());

            // then
            assertThat(impactDto.getDescription()).isEqualTo(newDescription);
        }

        @Test
        void testUpdate_NonExistingId_ThrowEntityNotFoundException() {
            // given
            var impactDto = createDummyImpactDto();
            impactDto.setId(UUID.randomUUID());

            // when

            // then
            assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> impactService.update(impactDto));
        }

        @Test
        void testUpdate_NullId_ThrowEntityIdRequiredException() {
            // given
            var impactDto = createDummyImpactDto();

            // when

            // then
            assertThatExceptionOfType(EntityIdRequiredException.class).isThrownBy(() -> impactService.update(impactDto));
        }
    }

    @Nested
    class Delete {

        @Test
        void testDeleteById_DeleteImpact_ReturnNoImpacts() {
            // given
            var impact = saveFullDummyImpact();

            // when
            impactService.deleteById(impact.getId());

            // then
            var impacts = impactService.findAll();
            assertThat(impacts.size()).isZero();
        }

        @Test
        void testDeleteById_NonExistingId_ThrowEntityNotFoundException() {
            // given
            var impact = createDummyImpact();
            impact.setId(UUID.randomUUID());

            // when

            // then
            var id = impact.getId();
            assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> impactService.deleteById(id));
        }
    }

    @Nested
    class DeleteAll {

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5})
        void testDeleteAll_ExistingImpacts_ReturnNoImpact(int value) {
            // given
            for (int i = 0; i < value; i++) {
                saveFullDummyImpact();
            }

            // when
            impactService.deleteAll();

            // then
            var impacts = impactService.findAll();
            assertThat(impacts.size()).isZero();
        }
    }
}
