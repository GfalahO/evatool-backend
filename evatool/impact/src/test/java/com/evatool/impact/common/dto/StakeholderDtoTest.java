package com.evatool.impact.common.dto;

import org.junit.jupiter.api.Test;

public class StakeholderDtoTest {
    @Test
    public void testToString_DefaultObject_DoNotThrowException() {
        // given
        var stakeholderDto = new StakeholderDto();

        // when

        // then
        stakeholderDto.toString();
    }
}
