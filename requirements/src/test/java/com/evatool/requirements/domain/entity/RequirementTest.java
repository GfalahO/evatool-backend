package com.evatool.requirements.domain.entity;

import com.evatool.requirements.entity.Requirement;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static com.evatool.requirements.common.TestDataGenerator.getRequirement;

public class RequirementTest {
    @Test
    public void testSetDescription_NullValue_ThrowException() {
        // given
        Requirement requirement = getRequirement();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirement.setDescription(null));
    }

    @Test
    public void testSetTitle_NullValue_ThrowException() {
        // given
        Requirement requirement = getRequirement();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirement.setTitle(null));
    }

    @Test
    public void testSetVariants_NullValue_ThrowException() {
        // given
        var requirement = getRequirement();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirement.setVariants(null));
    }


    @Test
    public void testSetProjectId_NullValue_ThrowException() {
        // given
        var requirement = getRequirement();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> requirement.setProjectId(null));
    }
}
