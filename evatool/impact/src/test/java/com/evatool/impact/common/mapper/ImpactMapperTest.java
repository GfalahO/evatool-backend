package com.evatool.impact.common.mapper;

import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import java.util.UUID;

import static com.evatool.impact.persistence.TestDataGenerator.getImpact;
import static com.evatool.impact.persistence.TestDataGenerator.getImpactDto;
import static org.assertj.core.api.Assertions.assertThat;

public class ImpactMapperTest {
    @Test
    public void testToDot_NewImpact_EqualsImpactDto() {
        // given
        var impactMapper = new ImpactMapper();
        var impact = getImpact();

        // when
        var impactDto = impactMapper.toDto(impact);

        // then
        assertThat(impact.getId()).isEqualTo(impactDto.getId());
        assertThat(impact.getValue()).isEqualTo(impactDto.getValue());
        assertThat(impact.getDescription()).isEqualTo(impactDto.getDescription());
        assertThat(impact.getDimension().getId()).isEqualTo(impactDto.getDimensionId());
        assertThat(impact.getStakeholder().getId()).isEqualTo(impactDto.getStakeholderId());
    }

    @Test
    public void testFromDto_NewImpactDto_EqualsImpact() {
        // given
        var impactMapper = new ImpactMapper();
        var impactDto = getImpactDto();

        // when
        var impact = impactMapper.fromDto(impactDto);

        // then
        assertThat(impactDto.getId()).isEqualTo(impact.getId());
        assertThat(impactDto.getValue()).isEqualTo(impact.getValue());
        assertThat(impactDto.getDescription()).isEqualTo(impact.getDescription());
    }
}
