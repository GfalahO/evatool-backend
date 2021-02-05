package com.evatool.impact.domain.entity;

import org.junit.jupiter.api.Test;

import static com.evatool.impact.TestDataGenerator.getDimension;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class DimensionTest {
    @Test
    public void testSetName_NullValue_ThrowException() {
        // given
        var dimension = getDimension();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> dimension.setName(null));
    }

    @Test
    public void testSetDescription_NullValue_ThrowException() {
        // given
        var dimension = getDimension();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> dimension.setDescription(null));
    }
}
