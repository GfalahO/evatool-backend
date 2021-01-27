package com.evatool.impact.persistence.entity;

import com.evatool.impact.persistence.TestDataGenerator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class DimensionTests {
    @Test
    public void testToString_DefaultObject_DoesNotThrowException() {
        // given
        var dimension = TestDataGenerator.getDimension();

        // when

        // then
        var s = dimension.toString();
    }

    @Test
    public void testSetName_IllegalValueThrowsException() {
        // given
        var dimension = TestDataGenerator.getDimension();

        // when

        // then
        Assert.assertThrows(IllegalArgumentException.class, () -> dimension.setName(null));
    }

    @Test
    public void testSetDescription_IllegalValueThrowsException() {
        // given
        var dimension = TestDataGenerator.getDimension();

        // when

        // then
        Assert.assertThrows(IllegalArgumentException.class, () -> dimension.setDescription(null));
    }
}
