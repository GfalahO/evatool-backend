package com.evatool.impact.application.json.mapper;

import com.evatool.impact.common.exception.EventPayloadInvalidException;
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
        assertThatExceptionOfType(EventPayloadInvalidException.class).isThrownBy(() -> fromJson(json));
    }

    @Test
    void testFromJsonString_JsonStringMissingIdField_ThrowInvalidEventPayloadException() {
        // given
        var name = "name";

        // when
        var json = String.format("{\"name\":\"%s\"}", name);

        // then
        assertThatExceptionOfType(EventPayloadInvalidException.class).isThrownBy(() -> fromJson(json));
    }

    @Test
    void testFromJsonString_JsonStringMissingNameField_ThrowInvalidEventPayloadException() {
        // given
        var id = UUID.randomUUID().toString();

        // when
        var json = String.format("{\"id\":\"%s\"}", id);

        // then
        assertThatExceptionOfType(EventPayloadInvalidException.class).isThrownBy(() -> fromJson(json));
    }

    @Test
    void testFromJsonString_JsonStringNullIdField_ThrowInvalidEventPayloadException() {
        // given
        var name = "name";

        // when
        var json = String.format("{\"id\":null,\"name\":\"%s\"}", name);

        // then
        assertThatExceptionOfType(EventPayloadInvalidException.class).isThrownBy(() -> fromJson(json));
    }

    @Test
    void testFromJsonString_JsonStringNullNameField_ThrowInvalidEventPayloadException() {
        // given
        var id = UUID.randomUUID().toString();

        // when
        var json = String.format("{\"id\":\"%s\",\"name\":null}", id);

        // then
        assertThatExceptionOfType(EventPayloadInvalidException.class).isThrownBy(() -> fromJson(json));
    }
}
