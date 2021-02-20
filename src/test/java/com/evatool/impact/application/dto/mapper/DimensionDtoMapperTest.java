package com.evatool.impact.application.dto.mapper;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.evatool.impact.application.dto.mapper.DimensionDtoMapper.fromDto;
import static com.evatool.impact.application.dto.mapper.DimensionDtoMapper.toDto;
import static com.evatool.impact.common.TestDataGenerator.createDummyDimension;
import static com.evatool.impact.common.TestDataGenerator.createDummyDimensionDto;
import static org.assertj.core.api.Assertions.assertThat;

class DimensionDtoMapperTest {

    @Test
    void testToDot_Dimension_EqualsDimensionDto() {
        // given
        var dimension = createDummyDimension();
        dimension.setId(UUID.randomUUID());

        // when
        var dimensionDto = toDto(dimension);

        // then
        assertThat(dimension.getId()).isEqualTo(dimensionDto.getId());
        assertThat(dimension.getName()).isEqualTo(dimensionDto.getName());
        assertThat(dimension.getType()).isEqualTo(dimensionDto.getType());
        assertThat(dimension.getDescription()).isEqualTo(dimensionDto.getDescription());
    }

    @Test
        // TODO [tzaika] actually an integration test
    void testFromDto_NewDimensionDto_EqualsDimension() {
        // given
        var dimensionDto = createDummyDimensionDto();
        dimensionDto.setId(UUID.randomUUID());

        // when
        var dimension = fromDto(dimensionDto);

        // then
        assertThat(dimensionDto.getId()).isEqualTo(dimension.getId());
        assertThat(dimensionDto.getName()).isEqualTo(dimension.getName());
        assertThat(dimensionDto.getDescription()).isEqualTo(dimension.getDescription());
    }
}
