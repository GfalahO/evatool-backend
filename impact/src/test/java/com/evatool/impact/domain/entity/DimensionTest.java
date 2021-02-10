package com.evatool.impact.domain.entity;

import com.evatool.impact.common.exception.PropertyViolationException;
import org.junit.jupiter.api.Test;

import static com.evatool.impact.common.TestDataGenerator.getDimension;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class DimensionTest {
    @Test
    public void testSetName_NullValue_ThrowPropertyViolationException() {
        // given
        var dimension = getDimension();

        // when

        // then
        assertThatExceptionOfType(PropertyViolationException.class).isThrownBy(() -> dimension.setName(null));
    }

    @Test
    public void testSetDescription_NullValue_ThrowException() {
        // given
        var dimension = getDimension();

        // when

        // then
        assertThatExceptionOfType(PropertyViolationException.class).isThrownBy(() -> dimension.setDescription(null));
    }
}
