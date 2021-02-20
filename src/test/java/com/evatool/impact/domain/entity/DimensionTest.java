package com.evatool.impact.domain.entity;

import com.evatool.impact.common.exception.PropertyViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.UUID;

import static com.evatool.impact.common.TestDataGenerator.createDummyDimension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class DimensionTest {

    @Test
    void testSetName_NullValue_ThrowPropertyViolationException() {
        // given
        var dimension = createDummyDimension();

        // when

        // then
        assertThatExceptionOfType(PropertyViolationException.class).isThrownBy(() -> dimension.setName(null));
    }

    @Test
    void testSetTypeString_NullValue_ThrowPropertyViolationException() {
        // given
        var dimension = createDummyDimension();

        // when

        // then
        assertThatExceptionOfType(PropertyViolationException.class).isThrownBy(() -> dimension.setType(null));
    }

    @Test
    void testSetDescription_NullValue_ThrowPropertyViolationException() {
        // given
        var dimension = createDummyDimension();

        // when

        // then
        assertThatExceptionOfType(PropertyViolationException.class).isThrownBy(() -> dimension.setDescription(null));
    }
}
