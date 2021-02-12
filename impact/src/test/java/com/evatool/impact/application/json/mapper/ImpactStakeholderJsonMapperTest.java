package com.evatool.impact.application.json.mapper;

import com.evatool.impact.common.exception.InvalidUuidException;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.evatool.impact.application.json.mapper.ImpactStakeholderJsonMapper.fromJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ImpactStakeholderJsonMapperTest {

    @Test
    void testFromJson_ImpactStakeholderJsonString_EqualsImpactStakeholderJson() throws JSONException {
        // given
        var id = UUID.randomUUID().toString();
        var name = "name";
        var json = String.format("{\"id\":\"%s\",\"name\":\"%s\"}", id, name);

        // when
        var impactStakeholder = fromJson(json);

        // then
        assertThat(impactStakeholder.getId()).hasToString(id);
        assertThat(impactStakeholder.getName()).isEqualTo(name);
    }

    @Test
    void testFromJson_InvalidId_ThrowInvalidUuidException() throws JSONException {
        // given
        var id = "invalid uuid";
        var name = "name";
        var json = String.format("{\"id\":\"%s\",\"name\":\"%s\"}", id, name);

        // when

        // then
        assertThatExceptionOfType(InvalidUuidException.class).isThrownBy(() -> fromJson(json));
    }
}
