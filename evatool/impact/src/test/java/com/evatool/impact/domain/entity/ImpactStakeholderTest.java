package com.evatool.impact.domain.entity;

import org.junit.jupiter.api.Test;

import static com.evatool.impact.TestDataGenerator.getStakeholder;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ImpactStakeholderTest {
    @Test
    public void testSetName_NullValue_ThrowException() {
        // given
        var stakeholder = getStakeholder();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> stakeholder.setName(null));
    }
}
