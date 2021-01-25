package com.fae.evatool.impact.tests.persistence.entity;

import org.junit.jupiter.api.Test;

import static com.fae.evatool.impact.tests.persistence.TestDataGenerator.getAnalysis;

public class AnalysisTests {
    @Test
    public void testToString_DefaultObject_DoesNotThrowException(){
        // given
        var analysis = getAnalysis();

        // when

        // then
        var s = analysis.toString();
    }

}
