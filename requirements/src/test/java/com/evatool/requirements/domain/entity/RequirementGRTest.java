package com.evatool.requirements.domain.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.evatool.requirements.common.TestDataGenerator.getRequirementGR;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class RequirementGRTest {
    @Test
    public void testSetRequirement_NullValue_ThrowException() {
        // given
        var requirementGR = getRequirementGR();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementGR.setRequirement(null));
    }

    @Test
    public void testSetRequirementsImpacts_NullValue_ThrowException() {
        // given
        var requirementGR = getRequirementGR();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementGR.setRequirementsImpacts(null));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1})
    public void testSetVariants_NullValue_ThrowException(int value) {
        // given
        var requirementGR = getRequirementGR();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementGR.setPoints(value));
    }
}
