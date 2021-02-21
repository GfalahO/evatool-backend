package com.evatool.impact.application.json.mapper;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.evatool.impact.application.json.mapper.ImpactJsonMapper.toJson;
import static com.evatool.impact.common.TestDataGenerator.createDummyImpact;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImpactJsonMapperTest {

    @Test
    void testToJson_NewImpact_EqualsImpactJson() {
        // given
        var impact = createDummyImpact();
        impact.setId(UUID.randomUUID());
        impact.getDimension().setId(UUID.randomUUID());
        impact.getStakeholder().setId(UUID.randomUUID());

        // when
        var impactJson = toJson(impact);

        // then
        assertTrue(impactJson.equals(impact));
    }
}
