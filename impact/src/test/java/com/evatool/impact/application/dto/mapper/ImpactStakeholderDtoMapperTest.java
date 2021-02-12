package com.evatool.impact.application.dto.mapper;

import org.junit.jupiter.api.Test;

import static com.evatool.impact.application.dto.mapper.StakeholderDtoMapper.fromDto;
import static com.evatool.impact.application.dto.mapper.StakeholderDtoMapper.toDto;
import static com.evatool.impact.common.TestDataGenerator.createDummyStakeholder;
import static com.evatool.impact.common.TestDataGenerator.createDummyStakeholderDto;
import static org.assertj.core.api.Assertions.assertThat;

class ImpactStakeholderDtoMapperTest {

    @Test
    void testToDot_NewStakeholder_EqualsStakeholderDto() {
        // given
        var stakeholder = createDummyStakeholder();

        // when
        var stakeholderDto = toDto(stakeholder);

        // then
        assertThat(stakeholder.getId()).isEqualTo(stakeholderDto.getId());
        assertThat(stakeholder.getName()).isEqualTo(stakeholderDto.getName());
    }

    @Test
    void testFromDto_NewStakeholderDto_EqualsStakeholder() {
        // given
        var stakeholderDto = createDummyStakeholderDto();

        // when
        var stakeholder = fromDto(stakeholderDto);

        // then
        assertThat(stakeholderDto.getId()).isEqualTo(stakeholder.getId());
        assertThat(stakeholderDto.getName()).isEqualTo(stakeholder.getName());
    }
}
