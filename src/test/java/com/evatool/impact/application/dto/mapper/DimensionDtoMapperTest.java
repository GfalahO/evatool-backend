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
    void testToDto_RecreatedDimension_EqualsDimension() {
        // given
        var dimension = createDummyDimension();
        dimension.setId(UUID.randomUUID());

        // when
        var dimensionDto = toDto(dimension);
        var recreatedDimension = fromDto(dimensionDto);

        // then
        assertThat(dimension).isEqualTo(recreatedDimension);
    }

    @Test
    void testFromDto_RecreatedDimensionDto_EqualsDimensionDto() {
        // given
        var dimensionDto = createDummyDimensionDto();
        dimensionDto.setId(UUID.randomUUID());

        // when
        var dimension = fromDto(dimensionDto);
        var recreatedDimensionDto = toDto(dimension);

        // then
        assertThat(dimensionDto).isEqualTo(recreatedDimensionDto);
    }
}
