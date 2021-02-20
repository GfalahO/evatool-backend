package com.evatool.impact.application.json.mapper;

import com.evatool.impact.common.exception.InvalidEventPayloadException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.evatool.impact.application.json.mapper.ImpactStakeholderJsonMapper.fromJson;
import static org.assertj.core.api.Assertions.*;

class ImpactStakeholderJsonMapperTest {

    @Test
    void testFromJsonString_JsonString_EqualsImpactStakeholderJson() {
        // given
        var id = UUID.randomUUID().toString();
        var name = "name";
        var json = String.format("{\"id\":\"%s\",\"name\":\"%s\"}", id, name);

        // when
        var impactStakeholderJson = fromJson(json);

        // then
        assertThat(impactStakeholderJson.getId()).hasToString(id);
        assertThat(impactStakeholderJson.getName()).isEqualTo(name);
    }

    @Test
    void testFromJsonString_JsonStringMissingColon_ThrowInvalidEventPayloadException() {
        // given
        var id = UUID.randomUUID().toString();
        var name = "name";

        // when
        var json = String.format("{\"id\"\"%s\",\"name\":\"%s\"}", id, name);

        // then
        assertThatExceptionOfType(InvalidEventPayloadException.class).isThrownBy(() -> fromJson(json));
    }

    @Test
    void testFromJsonString_JsonStringMissingField_ThrowInvalidEventPayloadException() {
        // given
        var id = UUID.randomUUID().toString();
        var name = "name";

        // when
        var json = String.format("{\"name\":\"%s\"}", id, name);

        // then
        assertThatExceptionOfType(InvalidEventPayloadException.class).isThrownBy(() -> fromJson(json));
    }

    @Test
    void testFromJsonString_JsonStringNullField_NullIsConvertedToStringByMapper() {
        // given
        var id = UUID.randomUUID().toString();

        // when
        var json = String.format("{\"id\":\"%s\",\"name\":%s}", id, null);
        var impactStakeholder = fromJson(json);

        // then
        assertThat(impactStakeholder.getId()).hasToString(id);
        assertThat(impactStakeholder.getName()).isEqualTo("null");
    }
}
