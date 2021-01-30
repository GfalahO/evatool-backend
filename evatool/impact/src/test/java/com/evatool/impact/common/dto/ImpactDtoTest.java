package com.evatool.impact.common.dto;

import org.junit.jupiter.api.Test;

public class ImpactDtoTest {
    @Test
    public void testToString_DefaultObject_DoNotThrowException() {
        // given
        var impactDto = new ImpactDto();

        // when

        // then
        impactDto.toString();
    }
}
