package com.evatool.impact.application.dto.mapper;

import com.evatool.impact.common.exception.PropertyViolationException;
import com.evatool.impact.domain.entity.Dimension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.evatool.impact.application.dto.mapper.DimensionMapper.fromDto;
import static com.evatool.impact.application.dto.mapper.DimensionMapper.toDto;
import static com.evatool.impact.common.TestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

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
        assertThat(dimension.getType().toString()).isEqualTo(dimensionDto.getType());
        assertThat(dimension.getDescription()).isEqualTo(dimension.getDescription());
    }

    @ParameterizedTest
    @ValueSource(strings = {"ECONOMIC", "SOCIAL", "economic", "social", "Social", "Economic"})
    public void testFromDto_LegalTypeValues_DoNotThrowException(String value) {
        // given
        var dto = getDimensionDto();

        // when
        dto.setType(value);

        // then
        DimensionMapper.fromDto(dto);
    }

    @ParameterizedTest
    @ValueSource(strings = {"ECONOMICd", "SOsCIAL", "economiac", "soscial", "Sodcial", "Econdomic"})
    public void testFromDto_IllegalTypeValues_DoThrowException(String value) {
        // given
        var dto = getDimensionDto();

        // when
        dto.setType(value);

        // then
        assertThatExceptionOfType(PropertyViolationException.class).isThrownBy(() -> DimensionMapper.fromDto(dto));
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
