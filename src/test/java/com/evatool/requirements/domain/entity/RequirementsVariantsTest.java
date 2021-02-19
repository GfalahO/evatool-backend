package com.evatool.requirements.domain.entity;

import com.evatool.requirements.entity.RequirementsVariants;
import org.junit.jupiter.api.Test;

import static com.evatool.requirements.common.TestDataGenerator.getRequirementsVariants;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class RequirementsVariantsTest {
    @Test
    public void testSetDescription_NullValue_ThrowException() {
        // given
        RequirementsVariants requirementsVariants = getRequirementsVariants();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementsVariants.setDescription(null));
    }

    @Test
    public void testSetTitle_NullValue_ThrowException() {
        // given
        RequirementsVariants requirementsVariants = getRequirementsVariants();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementsVariants.setTitle(null));
    }
}
