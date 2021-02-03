package com.evatool.impact.application.dto;

import org.junit.jupiter.api.Test;

// TODO [hbuhl] delete this;
// TODO [hbuhl] why .jupiter.api.Test is used and not org.junit.Test?
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
