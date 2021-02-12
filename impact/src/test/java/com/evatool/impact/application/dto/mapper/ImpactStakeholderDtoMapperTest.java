package com.evatool.impact.application.dto.mapper;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.evatool.impact.application.dto.mapper.StakeholderDtoMapper.fromDto;
import static com.evatool.impact.application.dto.mapper.StakeholderDtoMapper.toDto;
import static com.evatool.impact.common.TestDataGenerator.createDummyStakeholder;
import static com.evatool.impact.common.TestDataGenerator.createDummyStakeholderDto;
import static org.assertj.core.api.Assertions.assertThat;

class ImpactStakeholderDtoMapperTest {

    @Test
    void testToDto_NewStakeholder_EqualsStakeholderDto() {
        // given
        var stakeholder = createDummyStakeholder();
        stakeholder.setId(UUID.randomUUID());

        // when
        var stakeholderDto = toDto(stakeholder);

        // then
        assertThat(stakeholder.getId()).hasToString(stakeholderDto.getId());
        assertThat(stakeholder.getName()).isEqualTo(stakeholderDto.getName());
    }

    @Test
    void testFromDto_NewStakeholderDto_EqualsStakeholder() {
        // given
        var stakeholderDto = createDummyStakeholderDto();
        stakeholderDto.setId(UUID.randomUUID().toString());

        // when
        var stakeholder = fromDto(stakeholderDto);

        // then
        assertThat(stakeholderDto.getId()).isEqualTo(stakeholder.getId().toString());
        assertThat(stakeholderDto.getName()).isEqualTo(stakeholder.getName());
    }
}
