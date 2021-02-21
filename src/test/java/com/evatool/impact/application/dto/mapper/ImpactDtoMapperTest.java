package com.evatool.impact.application.dto.mapper;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.evatool.impact.application.dto.mapper.ImpactDtoMapper.fromDto;
import static com.evatool.impact.application.dto.mapper.ImpactDtoMapper.toDto;
import static com.evatool.impact.common.TestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;

class ImpactDtoMapperTest {

    @Test
    void testToDto_NewImpact_EqualsImpactDto() {
        // given
        var impact = createDummyImpact();
        impact.setId(UUID.randomUUID());
        impact.getDimension().setId(UUID.randomUUID());
        impact.getStakeholder().setId(UUID.randomUUID());

        // when
        var impactDto = toDto(impact);

        // then
        assertThat(impact.getId()).isEqualTo(impactDto.getId());
        assertThat(impact.getValue()).isEqualTo(impactDto.getValue());
        assertThat(impact.getDescription()).isEqualTo(impactDto.getDescription());
        assertThat(impact.getDimension().getId()).isEqualTo(impactDto.getDimension().getId());
        assertThat(impact.getDimension().getType()).isEqualTo(impactDto.getDimension().getType());
        assertThat(impact.getDimension().getName()).isEqualTo(impactDto.getDimension().getName());
        assertThat(impact.getDimension().getDescription()).isEqualTo(impactDto.getDimension().getDescription());
        assertThat(impact.getStakeholder().getId()).isEqualTo(impactDto.getStakeholder().getId());
        assertThat(impact.getStakeholder().getName()).isEqualTo(impactDto.getStakeholder().getName());
    }

    @Test
    void testFromDto_NewImpactDto_EqualsImpact() {
        // given
        var impactDto = createDummyImpactDto();
        impactDto.setId(UUID.randomUUID());
        impactDto.setDimension(createDummyDimensionDto());
        impactDto.setStakeholder(createDummyStakeholderDto());

        // when
        var impact = fromDto(impactDto);

        // then
        assertThat(impactDto.getId()).isEqualTo(impact.getId());
        assertThat(impactDto.getValue()).isEqualTo(impact.getValue());
        assertThat(impactDto.getDescription()).isEqualTo(impact.getDescription());
        assertThat(impactDto.getDimension().getId()).isEqualTo(impact.getDimension().getId());
        assertThat(impactDto.getDimension().getType()).isEqualTo(impact.getDimension().getType());
        assertThat(impactDto.getDimension().getName()).isEqualTo(impact.getDimension().getName());
        assertThat(impactDto.getDimension().getDescription()).isEqualTo(impact.getDimension().getDescription());
        assertThat(impactDto.getStakeholder().getId()).isEqualTo(impact.getStakeholder().getId());
        assertThat(impactDto.getStakeholder().getName()).isEqualTo(impact.getStakeholder().getName());
    }
}
