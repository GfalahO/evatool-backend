package com.evatool.impact.domain.event.json.mapper;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.evatool.impact.domain.event.json.mapper.DimensionJsonMapper.toJson;
import static com.evatool.impact.common.TestDataGenerator.createDummyDimension;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DimensionJsonMapperTest {

    @Test
    void testToJson_DimensionJson_EqualsDimension() {
        // given
        var dimension = createDummyDimension();
        dimension.setId(UUID.randomUUID());

        // when
        var dimensionJson = toJson(dimension);

        // then
        assertTrue(dimensionJson.equals(dimension));
    }
}
