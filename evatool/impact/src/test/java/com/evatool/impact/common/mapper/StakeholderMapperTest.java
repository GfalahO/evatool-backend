package com.evatool.impact.common.mapper;

import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import static com.evatool.impact.persistence.TestDataGenerator.getStakeholder;
import static com.evatool.impact.persistence.TestDataGenerator.getStakeholderDto;
import static org.assertj.core.api.Assertions.assertThat;

public class StakeholderMapperTest {
    @Test
    public void testToDot_NewStakeholder_EqualsStakeholderDto() {
        // given
        var stakeholderMapper = new StakeholderMapper();
        var stakeholder = getStakeholder();

        // when
        var stakeholderDto = stakeholderMapper.toDto(stakeholder);

        // then
        assertThat(new ReflectionEquals(stakeholder).matches(stakeholderDto));
    }

    @Test
    public void testFromDto_NewStakeholderDto_EqualsStakeholder() {
        // given
        var stakeholderMapper = new StakeholderMapper();
        var stakeholderDto = getStakeholderDto();

        // when
        var stakeholder = stakeholderMapper.fromDto(stakeholderDto);

        // then
        assertThat(new ReflectionEquals(stakeholderDto).matches(stakeholder));
    }
}
