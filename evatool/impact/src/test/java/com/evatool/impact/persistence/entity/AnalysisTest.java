package com.evatool.impact.persistence.entity;

import com.evatool.impact.persistence.TestDataGenerator;
import org.junit.jupiter.api.Test;

import static com.evatool.impact.persistence.TestDataGenerator.getAnalysis;
import static org.assertj.core.api.Assertions.*;

public class AnalysisTest {
    @Test
    public void testToString_DefaultObject_DoNotThrowException() {
        // given
        var analysis = getAnalysis();

        // when

        // then
        analysis.toString();
    }
}
