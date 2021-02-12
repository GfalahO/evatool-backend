package com.evatool.impact.application.dto.mapper;

import com.evatool.impact.common.exception.PropertyViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Locale;

import static com.evatool.impact.application.dto.mapper.DimensionDtoMapper.fromDto;
import static com.evatool.impact.application.dto.mapper.DimensionDtoMapper.toDto;
import static com.evatool.impact.common.TestDataGenerator.createDummyDimension;
import static com.evatool.impact.common.TestDataGenerator.createDummyDimensionDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class DimensionDtoMapperTest {

    @Test
    void testToDot_NewDimension_EqualsDimensionDto() {
        // given
        var dimension = createDummyDimension();

        // when
        var dimensionDto = toDto(dimension);

        // then
        assertThat(dimension.getId()).isEqualTo(dimensionDto.getId());
        assertThat(dimension.getName()).isEqualTo(dimensionDto.getName());
        assertThat(dimension.getType()).hasToString(dimensionDto.getType());
        assertThat(dimension.getDescription()).isEqualTo(dimensionDto.getDescription());
    }

    @ParameterizedTest
    @ValueSource(strings = {"ECONOMIC", "SOCIAL"})
    void testFromDto_LegalTypeValues_DoNotThrowException(String value) {
        // given
        var dimensionDto = createDummyDimensionDto();

        // when
        dimensionDto.setType(value);
        var dimension = DimensionDtoMapper.fromDto(dimensionDto);

        // then
        assertThat(dimension.getType()).hasToString(value);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "null", "economic", "social", "Social", "Economic", "ECONOMICd", "SOsCIAL", "economiac", "soscial", "Sodcial", "Econdomic"})
    void testFromDto_IllegalTypeValues_ThrowPropertyViolationException(String value) {
        // given
        var dto = createDummyDimensionDto();

        // when
        dto.setType(value);

        // then
        assertThatExceptionOfType(PropertyViolationException.class).isThrownBy(() -> DimensionDtoMapper.fromDto(dto));
    }

    @Test
        // TODO [tzaika] actually an integration test
    void testFromDto_NewDimensionDto_EqualsDimension() {
        // given
        var dimensionDto = createDummyDimensionDto();

        // when
        var dimension = fromDto(dimensionDto);

        // then
        assertThat(dimensionDto.getId()).isEqualTo(dimension.getId());
        assertThat(dimensionDto.getName()).isEqualTo(dimension.getName());
        assertThat(dimensionDto.getDescription()).isEqualTo(dimension.getDescription());
    }
}
