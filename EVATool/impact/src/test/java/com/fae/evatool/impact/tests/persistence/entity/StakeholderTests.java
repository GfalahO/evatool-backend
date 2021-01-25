package com.fae.evatool.impact.tests.persistence.entity;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static com.fae.evatool.impact.tests.persistence.TestDataGenerator.getStakeholder;

public class StakeholderTests {
    @Test
    public void testToString_DefaultObject_DoesNotThrowException() {
        // given
        var stakeholder = getStakeholder();

        // when

        // then
        var s = stakeholder.toString();
    }

    @Test
    public void testSetName_IllegalValueThrowsException() {
        // given
        var stakeholder = getStakeholder();

        // when

        // then
        Assert.assertThrows(IllegalArgumentException.class, () -> stakeholder.setName(null));
    }
}
