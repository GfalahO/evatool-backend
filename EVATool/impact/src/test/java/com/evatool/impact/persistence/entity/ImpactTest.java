package com.evatool.impact.persistence.entity;

import com.evatool.impact.persistence.TestDataGenerator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ImpactTest {
    @Test
    public void testToString_DefaultObject_DoesNotThrowException() {
        // given
        var impact = TestDataGenerator.getImpact();

        // when

        // then
        impact.toString();
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -0.5, 0.0, 0.5, 1.0})
    public void testSetValue_LegalValueDoesNotThrowException(double value) {
        // given
        var impact = TestDataGenerator.getImpact();

        // when

        // then
        impact.setValue(value);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Double.MAX_VALUE - 1.000001, -1.1, -1.5, -2.0, 1.000001, 1.1, 1.5, 2.0, Double.MAX_VALUE})
    public void testSetValue_IllegalValueThrowsException(double value) {
        // given
        var impact = TestDataGenerator.getImpact();

        // when

        // then
        Assert.assertThrows(IllegalArgumentException.class, () -> impact.setValue(value));
    }

    @Test
    public void testSetReason_IllegalValueThrowsException() {
        // given
        var impact = TestDataGenerator.getImpact();

        // when

        // then
        Assert.assertThrows(IllegalArgumentException.class, () -> impact.setReason(null));
    }

    @Test
    public void testSetDimension_IllegalValueThrowsException() {
        // given
        var impact = TestDataGenerator.getImpact();

        // when

        // then
        Assert.assertThrows(IllegalArgumentException.class, () -> impact.setDimension(null));
    }

    @Test
    public void testSetStakeholder_IllegalValueThrowsException() {
        // given
        var impact = TestDataGenerator.getImpact();

        // when

        // then
        Assert.assertThrows(IllegalArgumentException.class, () -> impact.setStakeholder(null));
    }
}
