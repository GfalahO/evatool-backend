package com.evatool.impact.common.mapper;

import org.junit.jupiter.api.Test;

import static com.evatool.impact.persistence.TestDataGenerator.getStakeholder;
import static org.assertj.core.api.Assertions.assertThat;

public class StakeholderMapperTest {
    @Test
    public void testStakeholderDto_NewStakeholderDto_EqualsStakeholder() {
        // given
        var stakeholderMapper = new StakeholderMapper();
        var stakeholder = getStakeholder();

        // when
        var stakeholderDto = stakeholderMapper.toStakeholderDto(stakeholder);

        // then
        assertThat(stakeholderDto.getId()).isEqualTo(stakeholderDto.getId());
        assertThat(stakeholderDto.getName()).isEqualTo(stakeholderDto.getName());
    }
}
