package com.evatool.requirements.domain.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.evatool.requirements.common.TestDataGenerator.getImpact;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class RequirementsImpactsTest {

    @Test
    public void testSetDescription_NullValue_ThrowException() {
        // given
        var impact = getImpact();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> impact.setDescription(null));
    }

    @Test
    public void testSetTitle_NullValue_ThrowException() {
        // given
        var impact = getImpact();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> impact.setTitel(null));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1})
    public void testSetValue_NullValue_ThrowException(int value) {
        // given
        var impact = getImpact();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> impact.setValue(value));
    }

}
