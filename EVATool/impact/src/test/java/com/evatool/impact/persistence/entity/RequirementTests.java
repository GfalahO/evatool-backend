package com.evatool.impact.persistence.entity;

import com.evatool.impact.persistence.TestDataGenerator;
import org.junit.jupiter.api.Test;

public class RequirementTests {
    @Test
    public void testToString_DefaultObject_DoesNotThrowException() {
        // given
        var impact = TestDataGenerator.getImpact();

        // when

        // then
        var s = impact.toString();
    }
}
