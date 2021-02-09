package com.evatool.requirements.domain.entity;

import org.junit.jupiter.api.Test;

import static com.evatool.requirements.common.TestDataGenerator.getVariant;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class RequirementsVariantsTest {
    @Test
    public void testSetDescription_NullValue_ThrowException() {
        // given
        var variant = getVariant();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> variant.setDescription(null));
    }

    @Test
    public void testSetTitle_NullValue_ThrowException() {
        // given
        var variant = getVariant();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> variant.setTitel(null));
    }
}
