package com.evatool.requirements.domain.entity;

import com.evatool.requirements.entity.RequirementDimension;
import com.evatool.requirements.entity.RequirementsImpact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.evatool.requirements.common.TestDataGenerator.getRequirementDimension;
import static com.evatool.requirements.common.TestDataGenerator.getRequirementsImpacts;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class RequirementsImpactTest {

    @Test
    void testSetDescription_NullValue_ThrowException() {
        // given
        RequirementDimension requirementDimension = getRequirementDimension();
        RequirementsImpact requirementsImpact = getRequirementsImpacts(requirementDimension);

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementsImpact.setDescription(null));
    }


    @ParameterizedTest
    @ValueSource(ints = {3,2,4})
    void testSetValue_NullValue_ThrowException(int value) {
        // given
        RequirementDimension requirementDimension = getRequirementDimension();
        RequirementsImpact requirementsImpact = getRequirementsImpacts(requirementDimension);

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementsImpact.setValue(value));
    }

    @Test
    void testSetRequirementDimension_NullValue_ThrowException() {
        // given
        RequirementDimension requirementDimension = getRequirementDimension();
        RequirementsImpact requirementsImpact = getRequirementsImpacts(requirementDimension);
        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirementsImpact.setRequirementDimension(null));
    }

}
