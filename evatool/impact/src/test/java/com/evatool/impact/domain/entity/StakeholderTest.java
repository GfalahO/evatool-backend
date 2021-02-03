package com.evatool.impact.domain.entity;

import org.junit.jupiter.api.Test;

import static com.evatool.impact.common.TestDataGenerator.getStakeholder;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class StakeholderTest {
    @Test // TODO [hbuhl] delete this; not useful
    public void testToString_DefaultObject_DoNotThrowException() {
        // given
        var stakeholder = getStakeholder();

        // when

        // then
        stakeholder.toString();
    }

    @Test
    public void testSetName_NullValue_ThrowException() {
        // given
        var stakeholder = getStakeholder();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> stakeholder.setName(null));
    }
}
