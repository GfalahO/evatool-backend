package com.evatool.impact.application.dto.mapper;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.evatool.impact.application.dto.mapper.ImpactMapper.fromDto;
import static com.evatool.impact.application.dto.mapper.ImpactMapper.toDto;
import static com.evatool.impact.common.TestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ImpactMapperTest {

    @Test
    public void testToDto_NewImpact_EqualsImpactDto() {
        // given
        var impact = getImpact();
        impact.getDimension().setId(UUID.randomUUID().toString());
        impact.getStakeholder().setId(UUID.randomUUID().toString());

        // when
        var impactDto = toDto(impact);

        // then
        assertThat(impact.getId()).isEqualTo(impactDto.getId());
        assertThat(impact.getValue()).isEqualTo(impactDto.getValue());
        assertThat(impact.getDescription()).isEqualTo(impactDto.getDescription());
        assertThat(impact.getDimension().getId()).isEqualTo(impactDto.getDimension().getId());
        // TODO [tzaika] check
        assertThat(impact.getDimension().getType().toString()).isEqualTo(impactDto.getDimension().getType());
        assertThat(impact.getDimension().getName()).isEqualTo(impactDto.getDimension().getName());
        assertThat(impact.getDimension().getDescription()).isEqualTo(impactDto.getDimension().getDescription());
        assertThat(impact.getStakeholder().getId()).isEqualTo(impactDto.getStakeholder().getId());
        assertThat(impact.getStakeholder().getName()).isEqualTo(impactDto.getStakeholder().getName());
    }

    @Disabled // TODO [tzaika] actually an integration test
    @Test
    public void testFromDto_NewImpactDto_EqualsImpact() {
        // given
        var impactDto = getImpactDto();
        impactDto.setDimension(getDimensionDto());
        impactDto.setStakeholder(getStakeholderDto());

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
