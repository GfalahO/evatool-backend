package com.evatool.impact.persistence.entity;

import com.evatool.impact.persistence.TestDataGenerator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class StakeholderTest {
    @Test
    public void testToString_DefaultObject_DoNotThrowException() {
        // given
        var stakeholder = TestDataGenerator.getStakeholder();

        // when

        // then
        stakeholder.toString();
    }

    @Test
    public void testSetName_IllegalValue_ThrowException() {
        // given
        var stakeholder = TestDataGenerator.getStakeholder();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->  stakeholder.setName(null));
    }
}
