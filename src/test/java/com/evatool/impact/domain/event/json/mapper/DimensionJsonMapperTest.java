package com.evatool.impact.domain.event.json.mapper;

import com.evatool.impact.domain.entity.Dimension;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.evatool.impact.common.TestDataGenerator.createDummyDimension;
import static com.evatool.impact.domain.event.json.mapper.DimensionJsonMapper.toJson;
import static org.assertj.core.api.Assertions.assertThat;

class DimensionJsonMapperTest {

    @Test
    void testToJson_DimensionFromJson_EqualsDimension() {
        // given
        var dimension = createDummyDimension();
        dimension.setId(UUID.randomUUID());

        // when
        var dimensionJson = toJson(dimension);

        // then
        assertThat(dimension).isEqualTo(new Gson().fromJson(dimensionJson, Dimension.class));
    }
}
