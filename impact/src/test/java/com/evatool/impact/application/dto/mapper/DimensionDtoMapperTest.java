package com.evatool.impact.application.dto.mapper;

import com.evatool.impact.common.exception.PropertyViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Locale;
import java.util.UUID;

import static com.evatool.impact.application.dto.mapper.DimensionDtoMapper.fromDto;
import static com.evatool.impact.application.dto.mapper.DimensionDtoMapper.toDto;
import static com.evatool.impact.common.TestDataGenerator.createDummyDimension;
import static com.evatool.impact.common.TestDataGenerator.createDummyDimensionDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class DimensionDtoMapperTest {

    @Test
    void testToDot_Dimension_EqualsDimensionDto() {
        // given
        var dimension = createDummyDimension();
        dimension.setId(UUID.randomUUID());

        // when
        var dimensionDto = toDto(dimension);

        // then
        assertThat(dimension.getId()).hasToString(dimensionDto.getId());
        assertThat(dimension.getName()).isEqualTo(dimensionDto.getName());
        assertThat(dimension.getType()).hasToString(dimensionDto.getType());
        assertThat(dimension.getDescription()).isEqualTo(dimensionDto.getDescription());
    }

    @Test
        // TODO [tzaika] actually an integration test
    void testFromDto_NewDimensionDto_EqualsDimension() {
        // given
        var dimensionDto = createDummyDimensionDto();
        dimensionDto.setId(UUID.randomUUID().toString());

        // when
        var dimension = fromDto(dimensionDto);

        // then
        assertThat(dimensionDto.getId()).isEqualTo(dimension.getId().toString());
        assertThat(dimensionDto.getName()).isEqualTo(dimension.getName());
        assertThat(dimensionDto.getDescription()).isEqualTo(dimension.getDescription());
    }
}
