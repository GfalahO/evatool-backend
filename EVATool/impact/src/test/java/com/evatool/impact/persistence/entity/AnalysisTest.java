package com.evatool.impact.persistence.entity;

import com.evatool.impact.persistence.TestDataGenerator;
import org.junit.jupiter.api.Test;

public class AnalysisTest {
    @Test
    public void testToString_DefaultObject_DoesNotThrowException() {
        // given
        var analysis = TestDataGenerator.getAnalysis();

        // when

        // then
        var s = analysis.toString();
    }
}
