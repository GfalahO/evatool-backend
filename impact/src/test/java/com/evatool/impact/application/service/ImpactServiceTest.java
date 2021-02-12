package com.evatool.impact.application.service;

import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.common.exception.InvalidUuidException;
import com.evatool.impact.domain.entity.Impact;
import com.evatool.impact.domain.repository.DimensionRepository;
import com.evatool.impact.domain.repository.ImpactRepository;
import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static com.evatool.impact.common.TestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

// TODO [tzaika] find a way to see data in h2-DB during debugging
@SpringBootTest
class ImpactServiceTest {

    @Autowired
    ImpactService impactService;

    @Autowired
    ImpactRepository impactRepository;

    @Autowired
    DimensionRepository dimensionRepository;

    @Autowired
    ImpactStakeholderRepository stakeholderRepository;

    @BeforeEach
    void beforeEachTest() {
        impactRepository.deleteAll();
    }

    // TODO [tzaika] consider moving this method to super class or comparable solutions
    private Impact saveFullDummyImpact() {
        var dimension = dimensionRepository.save(createDummyDimension());
        var stakeholder = stakeholderRepository.save(createDummyStakeholder());
        var impact = createDummyImpact();
        impact.setDimension(dimension);
        impact.setStakeholder(stakeholder);

        return impactRepository.save(impact);
    }

    // TODO [hbuhl] checked exception on service method call is not best practice
    // https://docs.oracle.com/javase/tutorial/essential/exceptions/runtime.html
    @SneakyThrows
    @Test
    void testFindImpactById() {
        // given
        var impact = saveFullDummyImpact();

        // when
        var impactDto = impactService.findImpactById(impact.getId().toString());

        // then
        assertThat(impactDto.getId()).isEqualTo(impact.getId().toString());
        assertThat(impactDto.getValue()).isEqualTo(impact.getValue());
        assertThat(impactDto.getDescription()).isEqualTo(impact.getDescription());
        assertThat(impactDto.getDimension().getId()).isEqualTo(impact.getDimension().getId().toString());
        assertThat(impactDto.getDimension().getType()).isEqualTo(impact.getDimension().getType().toString());
        assertThat(impactDto.getDimension().getName()).isEqualTo(impact.getDimension().getName());
        assertThat(impactDto.getDimension().getDescription()).isEqualTo(impact.getDimension().getDescription());
        assertThat(impactDto.getStakeholder().getId()).isEqualTo(impact.getStakeholder().getId().toString());
        assertThat(impactDto.getStakeholder().getName()).isEqualTo(impact.getStakeholder().getName());
    }

    @Test
    void testFindImpactById_IdIsNull() {
        assertThatExceptionOfType(InvalidUuidException.class).isThrownBy(() -> impactService.findImpactById(null));
    }

    @Test
    void testFindImpactById_UnknownId() {
        var id = UUID.randomUUID().toString();
        assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> impactService.findImpactById(id));
    }

    @Test
    void testGetAllImpacts_NoSavedImpacts() {
        assertThat(impactService.getAllImpacts()).isEmpty();
    }

    @Test
    void testGetAllImpacts() {
        // given
        var impact = saveFullDummyImpact();

        // when
        var impactDtoList = impactService.getAllImpacts();
        assertThat(impactDtoList).hasSize(1);
        assertThat(impactDtoList.get(0).getId()).isEqualTo(impact.getId().toString());
    }

    // TODO [tzaika] add all remaining tests
}
