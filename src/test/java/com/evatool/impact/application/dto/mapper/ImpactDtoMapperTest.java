package com.evatool.impact.application.dto.mapper;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.evatool.impact.application.dto.mapper.ImpactDtoMapper.fromDto;
import static com.evatool.impact.application.dto.mapper.ImpactDtoMapper.toDto;
import static com.evatool.impact.common.TestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;

class ImpactDtoMapperTest {

    @Test
    void testToDto_RecreatedEntity_EqualsEntity() {
        // given
        var impact = createDummyImpact();
        impact.setId(UUID.randomUUID());
        impact.getDimension().setId(UUID.randomUUID());
        impact.getStakeholder().setId(UUID.randomUUID());

        // when
        var impactDto = toDto(impact);
        var recreatedImpact = fromDto(impactDto);

        // then
        assertThat(impact).isEqualTo(recreatedImpact);

    }

    @Test
    void testFromDto_NewImpactDto_EqualsRecreatedImpactDto() {
        // given
        var impactDto = createDummyImpactDto();
        impactDto.setId(UUID.randomUUID());
        impactDto.setDimension(createDummyDimensionDto());
        impactDto.setStakeholder(createDummyStakeholderDto());

        // when
        var impact = fromDto(impactDto);
        var recreatedImpactDto = toDto(impact);

        // then
        assertThat(impactDto).isEqualTo(recreatedImpactDto);
    }
}
