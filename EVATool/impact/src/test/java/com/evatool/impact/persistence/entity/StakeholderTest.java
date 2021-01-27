package com.evatool.impact.persistence.entity;

import com.evatool.impact.persistence.TestDataGenerator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class StakeholderTest {
    @Test
    public void testToString_DefaultObject_DoesNotThrowException() {
        // given
        var stakeholder = TestDataGenerator.getStakeholder();

        // when

        // then
        stakeholder.toString();
    }

    @Test
    public void testSetName_IllegalValue_ThrowsException() {
        // given
        var stakeholder = TestDataGenerator.getStakeholder();

        // when

        // then
        Assert.assertThrows(IllegalArgumentException.class, () -> stakeholder.setName(null));
    }
}
