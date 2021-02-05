package com.evatool.impact.application.dto.mapper;

import org.junit.jupiter.api.Test;

import static com.evatool.impact.TestDataGenerator.getStakeholder;
import static com.evatool.impact.TestDataGenerator.getStakeholderDto;
import static com.evatool.impact.application.dto.mapper.StakeholderMapper.fromDto;
import static com.evatool.impact.application.dto.mapper.StakeholderMapper.toDto;
import static org.assertj.core.api.Assertions.assertThat;

public class ImpactStakeholderMapperTest {
    @Test
    public void testToDot_NewStakeholder_EqualsStakeholderDto() {
        // given
        var stakeholder = getStakeholder();

        // when
        var stakeholderDto = toDto(stakeholder);

        // then
        assertThat(stakeholder.getId()).isEqualTo(stakeholderDto.getId());
        assertThat(stakeholder.getName()).isEqualTo(stakeholderDto.getName());
    }

    @Test
    public void testFromDto_NewStakeholderDto_EqualsStakeholder() {
        // given
        var stakeholderDto = getStakeholderDto();

        // when
        var stakeholder = fromDto(stakeholderDto);

        // then
        assertThat(stakeholderDto.getId()).isEqualTo(stakeholder.getId());
        assertThat(stakeholderDto.getName()).isEqualTo(stakeholder.getName());
    }
}
