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
        assertThat(dimension.getId()).isEqualTo(dimensionDto.getId());
        assertThat(dimension.getName()).isEqualTo(dimensionDto.getName());
        assertThat(dimension.getDescription()).isEqualTo(dimension.getDescription());
    }

    @Test
    public void testFromDto_NewDimensionDto_EqualsDimension() {
        // given
        var dimensionMapper = new DimensionMapper();
        var dimensionDto = getDimensionDto();

        // when
        var dimension = dimensionMapper.fromDto(dimensionDto);

        // then
        assertThat(dimensionDto.getId()).isEqualTo(dimension.getId());
        assertThat(dimensionDto.getName()).isEqualTo(dimension.getName());
        assertThat(dimensionDto.getDescription()).isEqualTo(dimension.getDescription());
    }
}
