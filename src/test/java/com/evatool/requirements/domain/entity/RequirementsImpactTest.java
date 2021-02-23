package com.evatool.requirements.domain.entity;

import com.evatool.requirements.entity.RequirementsImpact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.evatool.requirements.common.TestDataGenerator.getRequirementsImpacts;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class RequirementsImpactTest {

    @Test
    public void testSetDescription_NullValue_ThrowException() {
        // given
        RequirementsImpact requirementsImpact = getRequirementsImpacts();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementsImpact.setDescription(null));
    }

    @Test
    public void testSetTitle_NullValue_ThrowException() {
        // given
        RequirementsImpact requirementsImpact = getRequirementsImpacts();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementsImpact.setTitle(null));
    }

    @ParameterizedTest
    @ValueSource(ints = {3,2,4})
    public void testSetValue_NullValue_ThrowException(int value) {
        // given
        RequirementsImpact requirementsImpact = getRequirementsImpacts();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementsImpact.setValue(value));
    }

}
