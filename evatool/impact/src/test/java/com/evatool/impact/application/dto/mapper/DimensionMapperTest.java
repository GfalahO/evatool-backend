package com.evatool.impact.application.dto.mapper;

import org.junit.jupiter.api.Test;

import static com.evatool.impact.application.dto.mapper.DimensionMapper.fromDto;
import static com.evatool.impact.application.dto.mapper.DimensionMapper.toDto;
import static com.evatool.impact.common.TestDataGenerator.getDimension;
import static com.evatool.impact.common.TestDataGenerator.getDimensionDto;
import static org.assertj.core.api.Assertions.assertThat;

// TODO [hbuhl] why .jupiter.api.Test?
public class DimensionMapperTest {
    @Test
    public void testToDot_NewDimension_EqualsDimensionDto() {
        // given
        var dimension = getDimension();

        // when
        var dimensionDto = toDto(dimension);

        // then
        assertThat(dimension.getId()).isEqualTo(dimensionDto.getId());
        assertThat(dimension.getName()).isEqualTo(dimensionDto.getName());
        assertThat(dimension.getDescription()).isEqualTo(dimension.getDescription());
    }

    @Test // TODO [tzaika] actually an integration test
    public void testFromDto_NewDimensionDto_EqualsDimension() {
        // given
        var dimensionDto = getDimensionDto();

        // when
        var dimension = fromDto(dimensionDto);

        // then
        assertThat(dimensionDto.getId()).isEqualTo(dimension.getId());
        assertThat(dimensionDto.getName()).isEqualTo(dimension.getName());
        assertThat(dimensionDto.getDescription()).isEqualTo(dimension.getDescription());
    }
}
