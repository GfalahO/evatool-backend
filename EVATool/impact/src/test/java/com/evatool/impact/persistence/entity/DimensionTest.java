package com.evatool.impact.persistence.entity;

import com.evatool.impact.persistence.TestDataGenerator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class DimensionTest {
    @Test
    public void testToString_DefaultObject_DoesNotThrowException() {
        // given
        var dimension = TestDataGenerator.getDimension();

        // when

        // then
        dimension.toString();
    }

    @Test
    public void testSetName_IllegalValue_ThrowsException() {
        // given
        var dimension = TestDataGenerator.getDimension();

        // when

        // then
        Assert.assertThrows(IllegalArgumentException.class, () -> dimension.setName(null));
    }

    @Test
    public void testSetDescription_IllegalValue_ThrowsException() {
        // given
        var dimension = TestDataGenerator.getDimension();

        // when

        // then
        Assert.assertThrows(IllegalArgumentException.class, () -> dimension.setDescription(null));
    }
}
