package com.evatool.impact.domain.event.json.mapper;

import com.evatool.impact.domain.entity.Impact;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.evatool.impact.common.TestDataGenerator.createDummyImpact;
import static com.evatool.impact.domain.event.json.mapper.ImpactJsonMapper.toJson;
import static org.assertj.core.api.Assertions.assertThat;

class ImpactJsonMapperTest {

    @Test
    void testToJson_ImpactFromJson_EqualsImpact() {
        // given
        var impact = createDummyImpact();
        impact.setId(UUID.randomUUID());
        impact.getDimension().setId(UUID.randomUUID());
        impact.getStakeholder().setId(UUID.randomUUID());

        // when
        var impactJson = toJson(impact);

        // then
        assertThat(impact).isEqualTo(new Gson().fromJson(impactJson, Impact.class));
    }
}
