package com.evatool.impact.domain.event.json.mapper;

import com.evatool.impact.domain.event.json.DimensionJson;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.evatool.impact.common.TestDataGenerator.createDummyDimension;
import static com.evatool.impact.domain.event.json.mapper.DimensionJsonMapper.toJson;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DimensionJsonMapperTest {

    @Test
    void testToJson_DimensionFromJson_EqualsDimension() {
        // given
        var dimension = createDummyDimension();
        dimension.setId(UUID.randomUUID());

        // when
        var dimensionJson = new Gson().fromJson(toJson(dimension), DimensionJson.class);

        // then
        assertTrue(dimensionJson.equalsEntity(dimension));
    }
}
