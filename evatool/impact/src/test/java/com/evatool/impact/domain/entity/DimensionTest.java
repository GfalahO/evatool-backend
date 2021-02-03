package com.evatool.impact.domain.entity;

import org.junit.jupiter.api.Test;

import static com.evatool.impact.common.TestDataGenerator.getDimension;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class DimensionTest {
    @Test // TODO [tzaika] delete this; not useful
    public void testToString_DefaultObject_DoNotThrowException() {
        // given
        var dimension = getDimension();

        // when

        // then
        dimension.toString();
    }

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
