package com.evatool.impact.application.dto.mapper;

import org.junit.Ignore;
import org.junit.Test;

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
        assertThat(impact.getDimension().getId()).isEqualTo(impactDto.getDimensionId());
        assertThat(impact.getStakeholder().getId()).isEqualTo(impactDto.getStakeholderId());
    }

    @Ignore // TODO [tzaika] actually an integration test
    @Test
    public void testFromDto_NewImpactDto_EqualsImpact() {
        // given
        var dimension = getDimension();
        dimension.setId(UUID.randomUUID().toString());
        var stakeholder = getStakeholder();
        stakeholder.setId(UUID.randomUUID().toString());
        var impactDto = getImpactDto();
        impactDto.setDimensionId(dimension.getId());
        impactDto.setStakeholderId(stakeholder.getId());

        // when
        var impact = fromDto(impactDto);

        // then
        assertThat(impact.getId()).isEqualTo(impactDto.getId());
        assertThat(impact.getValue()).isEqualTo(impactDto.getValue());
        assertThat(impact.getDescription()).isEqualTo(impactDto.getDescription());
        assertThat(impact.getDimension().getId()).isEqualTo(impactDto.getDimensionId());
        assertThat(impact.getDimension().getName()).isEqualTo(dimension.getName());
        assertThat(impact.getDimension().getDescription()).isEqualTo(dimension.getDescription());
        assertThat(impact.getStakeholder().getId()).isEqualTo(impactDto.getStakeholderId());
        assertThat(impact.getStakeholder().getName()).isEqualTo(stakeholder.getName());
    }
}
