package com.fae.evatool.impact.tests.persistence.entity;

import org.junit.jupiter.api.Test;

import static com.fae.evatool.impact.tests.persistence.TestDataGenerator.getImpact;

public class RequirementTests {
    @Test
    public void testToString_DefaultObject_DoesNotThrowException(){
        // given
        var impact = getImpact();

        // when

        // then
        var s = impact.toString();
    }
}
