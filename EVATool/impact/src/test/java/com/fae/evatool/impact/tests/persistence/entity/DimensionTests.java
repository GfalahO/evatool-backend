package com.fae.evatool.impact.tests.persistence.entity;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static com.fae.evatool.impact.tests.persistence.TestDataGenerator.getDimension;
import static com.fae.evatool.impact.tests.persistence.TestDataGenerator.getImpact;

public class DimensionTests {
    @Test
    public void testSetName_IllegalValueThrowsException() {
        // given
        var dimension = getDimension();

        // when

        // then
        Assert.assertThrows(IllegalArgumentException.class, () -> dimension.setName(null));
    }

    @Test
    public void testSetDescription_IllegalValueThrowsException() {
        // given
        var dimension = getDimension();

        // when

        // then
        Assert.assertThrows(IllegalArgumentException.class, () -> dimension.setDescription(null));
    }
}
