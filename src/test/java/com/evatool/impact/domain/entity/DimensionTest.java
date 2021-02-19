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
    void testSetTypeType_NullValue_ThrowException() {
        // given
        var dimension = createDummyDimension();

        // when

        // then
        assertThatExceptionOfType(PropertyViolationException.class).isThrownBy(() -> dimension.setType((Dimension.Type) null));
    }

    @Test
    void testSetTypeString_NullValue_ThrowException() {
        // given
        var dimension = createDummyDimension();

        // when

        // then
        assertThatExceptionOfType(PropertyViolationException.class).isThrownBy(() -> dimension.setType((String) null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ECONOMIC", "SOCIAL"})
    void testFromDto_LegalTypeValues_DoNotThrowException(String value) {
        // given
        var dimension = createDummyDimension();
        dimension.setId(UUID.randomUUID().toString());

        // when
        dimension.setType(value);

        // then
        assertThat(dimension.getType()).hasToString(value);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "null", "economic", "social", "Social", "Economic", "ECONOMICd", "SOsCIAL", "economiac", "soscial", "Sodcial", "Econdomic"})
    void testFromDto_IllegalTypeValues_ThrowPropertyViolationException(String value) {
        // given
        var dimension = createDummyDimension();
        dimension.setId(UUID.randomUUID().toString());

        // when


        // then
        assertThatExceptionOfType(PropertyViolationException.class).isThrownBy(() -> dimension.setType(value));
    }

    @Test
    void testSetDescription_NullValue_ThrowException() {
        // given
        var dimension = createDummyDimension();

        // when

        // then
        assertThatExceptionOfType(PropertyViolationException.class).isThrownBy(() -> dimension.setDescription(null));
    }
}
