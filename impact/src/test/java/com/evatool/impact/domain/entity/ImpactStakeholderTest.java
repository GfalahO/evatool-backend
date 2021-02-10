package com.evatool.impact.domain.entity;

import com.evatool.impact.common.exception.PropertyViolationException;
import org.junit.jupiter.api.Test;

import static com.evatool.impact.common.TestDataGenerator.getStakeholder;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ImpactStakeholderTest {
    @Test
    public void testSetName_NullValue_ThrowPropertyViolationException() {
        // given
        var stakeholder = getStakeholder();

        // when

        // then
        assertThatExceptionOfType(PropertyViolationException.class).isThrownBy(() -> stakeholder.setName(null));
    }
}
