package com.evatool.impact.persistence.entity;

import com.evatool.impact.persistence.TestDataGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class RequirementTest {
    @Test
    public void testToString_DefaultObject_DoesNotThrowException() {
        // given
        var impact = TestDataGenerator.getImpact();

        // when

        // then
        impact.toString();
    }
}
