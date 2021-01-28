package com.evatool.impact.persistence.entity;

import com.evatool.impact.persistence.TestDataGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ImpactTest {
    @Test
    public void testToString_DefaultObject_DoNotThrowException() {
        // given
        var impact = TestDataGenerator.getImpact();

        // when

        // then
        impact.toString();
    }

    @Test
    public void testCreateEntity_CreatedStakeholder_IdIsNull() {
        // given
        var impact = TestDataGenerator.getImpact();

        // when

        // then
        assertThat(impact.getId()).isNull();
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -0.5, 0.0, 0.5, 1.0})
    public void testSetValue_LegalValue_DoNotThrowException(double value) {
        // given
        var impact = TestDataGenerator.getImpact();

        // when

        // then
        impact.setValue(value);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Double.MAX_VALUE, -1.000001, -1.1, -1.5, -2.0, 1.000001, 1.1, 1.5, 2.0, Double.MAX_VALUE})
    public void testSetValue_IllegalValue_ThrowException(double value) {
        // given
        var impact = TestDataGenerator.getImpact();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> impact.setValue(value));
    }

    @Test
    public void testSetDescription_IllegalValue_ThrowException() {
        // given
        var impact = TestDataGenerator.getImpact();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> impact.setDescription(null));
    }

    @Test
    public void testSetDimension_IllegalValue_ThrowException() {
        // given
        var impact = TestDataGenerator.getImpact();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> impact.setDimension(null));
    }

    @Test
    public void testSetStakeholder_IllegalValue_ThrowException() {
        // given
        var impact = TestDataGenerator.getImpact();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> impact.setStakeholder(null));
    }
}
