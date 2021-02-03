package com.evatool.impact.domain.entity;

import org.junit.jupiter.api.Test;

import static com.evatool.impact.common.TestDataGenerator.getAnalysis;

// TODO [hbuhl] delete this; not useful
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
