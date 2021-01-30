package com.evatool.impact.persistence.entity;

import com.evatool.impact.persistence.TestDataGenerator;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.evatool.impact.persistence.TestDataGenerator.getDimension;
import static org.assertj.core.api.Assertions.*;

public class DimensionTest {
    @Test
    public void testToString_DefaultObject_DoNotThrowException() {
        // given
        var dimension = getDimension();

        // when

        // then
        dimension.toString();
    }

    @Test
    public void testSetName_NullValue_ThrowException() {
        // given
        var dimension = getDimension();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> dimension.setName(null));
    }

    @Test
    public void testSetDescription_NullValue_ThrowException() {
        // given
        var dimension = getDimension();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> dimension.setDescription(null));
    }
}
