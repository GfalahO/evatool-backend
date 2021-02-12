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
        assertThat(impact.getId()).hasToString(impactDto.getId());
        assertThat(impact.getValue()).isEqualTo(impactDto.getValue());
        assertThat(impact.getDescription()).isEqualTo(impactDto.getDescription());
        assertThat(impact.getDimension().getId()).hasToString(impactDto.getDimension().getId());
        // TODO [tzaika] check
        assertThat(impact.getDimension().getType().toString()).hasToString(impactDto.getDimension().getType());
        assertThat(impact.getDimension().getName()).isEqualTo(impactDto.getDimension().getName());
        assertThat(impact.getDimension().getDescription()).isEqualTo(impactDto.getDimension().getDescription());
        assertThat(impact.getStakeholder().getId()).hasToString(impactDto.getStakeholder().getId());
        assertThat(impact.getStakeholder().getName()).isEqualTo(impactDto.getStakeholder().getName());
    }

    @Disabled // TODO [tzaika] actually an integration test
    @Test
    void testFromDto_NewImpactDto_EqualsImpact() {
        // given
        var impactDto = createDummyImpactDto();
        impactDto.setDimension(createDummyDimensionDto());
        impactDto.setStakeholder(createDummyStakeholderDto());

        // when
        // TODO: [tzaika] add mock or real service
        var impact = fromDto(impactDto, null, null);

        // then
        assertThat(impact.getId()).isEqualTo(impactDto.getId());
        assertThat(impact.getValue()).isEqualTo(impactDto.getValue());
        assertThat(impact.getDescription()).isEqualTo(impactDto.getDescription());
        assertThat(impact.getDimension().getId()).isEqualTo(impactDto.getDimension().getId());
        assertThat(impact.getDimension().getType().toString()).isEqualTo(impactDto.getDimension().getType());
        assertThat(impact.getDimension().getName()).isEqualTo(impactDto.getDimension().getName());
        assertThat(impact.getDimension().getDescription()).isEqualTo(impactDto.getDimension().getDescription());
        assertThat(impact.getStakeholder().getId()).isEqualTo(impactDto.getStakeholder().getId());
        assertThat(impact.getStakeholder().getName()).isEqualTo(impactDto.getStakeholder().getName());
    }
}
