package com.evatool.impact.common.mapper;

import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import static com.evatool.impact.persistence.TestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;

public class DimensionMapperTest {
    @Test
    public void testToDot_NewDimension_EqualsDimensionDto() {
        // given
        var dimensionMapper = new DimensionMapper();
        var dimension = getDimension();

        // when
        var dimensionDto = dimensionMapper.toDto(dimension);

        // then
        assertThat(new ReflectionEquals(dimension).matches(dimensionDto));
    }

    @Test
    public void testFromDto_NewDimensionDto_EqualsDimension() {
        // given
        var dimensionMapper = new DimensionMapper();
        var dimensionDto = getDimensionDto();

        // when
        var dimension = dimensionMapper.fromDto(dimensionDto);

        // then
        assertThat(new ReflectionEquals(dimensionDto).matches(dimension));
    }
}
