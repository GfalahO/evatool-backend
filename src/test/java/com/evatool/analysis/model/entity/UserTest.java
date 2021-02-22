package com.evatool.analysis.model.entity;

import org.junit.jupiter.api.Test;

import static com.evatool.analysis.common.TestDataGenerator.getUser;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class UserTest {

    @Test
    public void testSetUserName_ThrowException() {

        // given
        var user = getUser();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> user.setUserName(null));
    }

    @Test
    public void testSetUserPassword(){

        // given
        var user = getUser();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> user.setUserPassword(null));
    }

    @Test
    public void testSet(){

        // given
        var user = getUser();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> user.setUserEmail(null));
    }

}
