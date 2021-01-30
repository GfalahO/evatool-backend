package com.evatool.impact.common.dto;

import org.junit.jupiter.api.Test;

public class DimensionDtoTest {
    @Test
    public void testToString_DefaultObject_DoNotThrowException() {
        // given
        var dimensionDto = new DimensionDto();

        // when

        // then
        dimensionDto.toString();
    }
}
