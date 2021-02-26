package com.evatool.impact.domain.entity;

import org.junit.jupiter.api.Test;

import static com.evatool.impact.common.TestDataGenerator.createDummyStakeholder;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ImpactStakeholderTest {

    @Test
    void testSetName_NullValue_ThrowPropertyViolationException() {
        // given
        var stakeholder = createDummyStakeholder();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> stakeholder.setName(null));
    }
}
