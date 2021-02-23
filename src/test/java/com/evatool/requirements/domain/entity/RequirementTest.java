package com.evatool.requirements.domain.entity;

import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.entity.RequirementsAnalysis;
import com.evatool.requirements.entity.RequirementsVariant;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static com.evatool.requirements.common.TestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class RequirementTest {
    @Test
    public void testSetDescription_NullValue_ThrowException() {
        // given
        RequirementsAnalysis requirementsAnalysis = getRequirementsAnalysis();
        Collection<RequirementsVariant> requirementsVariant = getRequirementsVariants();
        Requirement requirement = getRequirement(requirementsAnalysis,requirementsVariant);

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirement.setDescription(null));
    }

    @Test
    public void testSetTitle_NullValue_ThrowException() {
        // given
        RequirementsAnalysis requirementsAnalysis = getRequirementsAnalysis();
        Collection<RequirementsVariant> requirementsVariant = getRequirementsVariants();
        Requirement requirement = getRequirement(requirementsAnalysis,requirementsVariant);

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirement.setTitle(null));
    }

    @Test
    public void testSetVariants_NullValue_ThrowException() {
        // given
        RequirementsAnalysis requirementsAnalysis = getRequirementsAnalysis();
        Collection<RequirementsVariant> requirementsVariant = getRequirementsVariants();
        Requirement requirement = getRequirement(requirementsAnalysis,requirementsVariant);

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirement.setVariants(null));
    }


    @Test
    public void testSetProjectId_NullValue_ThrowException() {
        // given
        RequirementsAnalysis requirementsAnalysis = getRequirementsAnalysis();
        Collection<RequirementsVariant> requirementsVariant = getRequirementsVariants();
        Requirement requirement = getRequirement(requirementsAnalysis,requirementsVariant);

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirement.setProjectId(null));
    }

    @Test
    public void testSetRequirementsAnalysis_NullValue_ThrowException() {
        // given
        RequirementsAnalysis requirementsAnalysis = getRequirementsAnalysis();
        Collection<RequirementsVariant> requirementsVariant = getRequirementsVariants();
        Requirement requirement = getRequirement(requirementsAnalysis,requirementsVariant);

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirement.setRequirementsAnalysis(null));
    }
}
