package com.evatool.requirements.domain.entity;

import com.evatool.requirements.entity.RequirementsVariant;
import org.junit.jupiter.api.Test;

import static com.evatool.requirements.common.TestDataGenerator.getRequirementsVariants;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class RequirementsVariantTest {
    @Test
    public void testSetDescription_NullValue_ThrowException() {
        // given
        RequirementsVariant requirementsVariant = getRequirementsVariants();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementsVariant.setDescription(null));
    }

    @Test
    public void testSetTitle_NullValue_ThrowException() {
        // given
        RequirementsVariant requirementsVariant = getRequirementsVariants();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementsVariant.setTitle(null));
    }
}
