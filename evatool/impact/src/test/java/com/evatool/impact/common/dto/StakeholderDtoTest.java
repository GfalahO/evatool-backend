package com.evatool.impact.common.dto;

import org.junit.jupiter.api.Test;

import static com.evatool.impact.persistence.TestDataGenerator.getStakeholder;
import static org.assertj.core.api.Assertions.assertThat;

public class StakeholderDtoTest {
    // TODO: Delete this test and move tests to mapper test class (before that, use mapper class)
    @Test
    public void testStakeholderDto_NewStakeholderDto_EqualsStakeholder() {
        // given
        var stakeholder = getStakeholder();

        // when
        var stakeholderDto = stakeholder.toDto();

        // then
        assertThat(stakeholderDto.getId()).isEqualTo(stakeholderDto.getId());
        assertThat(stakeholderDto.getName()).isEqualTo(stakeholderDto.getName());
    }

    @Test
    public void testToString_DefaultObject_DoNotThrowException(){
        // given
        var stakeholder = getStakeholder();

        // when
        var stakeholderDto = stakeholder.toDto();

        // then
        stakeholderDto.toString();
    }
}
