package com.evatool.impact.application.json.mapper;

import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.evatool.impact.application.json.mapper.ImpactStakeholderJsonMapper.fromJson;
import static org.assertj.core.api.Assertions.assertThat;

public class ImpactStakeholderJsonMapperTest {
    @Test
    public void testFromJson_ImpactStakeholderJsonString_EqualsImpactStakeholderJson() throws JSONException {
        // given
        var id = UUID.randomUUID().toString();
        var name = "name";
        var json = String.format("{\"id\":\"%s\",\"name\":\"%s\"}", id, name);

        // when
        var impactStakeholder = fromJson(json);

        // then
        assertThat(impactStakeholder.getId()).isEqualTo(id);
        assertThat(impactStakeholder.getName()).isEqualTo(name);
    }
}
