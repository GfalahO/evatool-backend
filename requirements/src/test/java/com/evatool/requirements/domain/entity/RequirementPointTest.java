package com.evatool.requirements.domain.entity;

import com.evatool.requirements.entity.RequirementPoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.evatool.requirements.common.TestDataGenerator.getRequirementGR;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class RequirementPointTest {
    @Test
    public void testSetRequirement_NullValue_ThrowException() {
        // given
        RequirementPoint requirementPoint = getRequirementGR();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementPoint.setRequirement(null));
    }

    @Test
    public void testSetRequirementsImpacts_NullValue_ThrowException() {
        // given
        RequirementPoint requirementPoint = getRequirementGR();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementPoint.setRequirementsImpact(null));
    }

    @ParameterizedTest
    @ValueSource(ints = {2})
    public void testSetValue_NullValue_ThrowException(int value) {
        // given
        RequirementPoint requirementPoint = getRequirementGR();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementPoint.setPoints(value));
    }
}
