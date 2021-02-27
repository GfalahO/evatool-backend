package com.evatool.impact.domain.event.json.mapper;

import com.evatool.impact.common.exception.EventPayloadInvalidException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.evatool.impact.domain.event.json.mapper.ImpactStakeholderJsonMapper.fromJson;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImpactStakeholderJsonMapperTest {

    @Test
    void testFromJsonString_JsonString_EqualsImpactStakeholder() {
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
    void testFromJsonString_JsonStringMissingColon_ThrowEventPayloadInvalidException() {
        // given
        var id = UUID.randomUUID().toString();
        var name = "name";

        // when
        var json = String.format("{\"id\"\"%s\",\"name\":\"%s\"}", id, name);

        // then
        assertThatExceptionOfType(EventPayloadInvalidException.class).isThrownBy(() -> fromJson(json));
    }

    @Test
    void testFromJsonString_JsonStringMissingIdField_ThrowEventPayloadInvalidException() {
        // given
        var name = "name";

        // when
        var json = String.format("{\"name\":\"%s\"}", name);

        // then
        assertThatExceptionOfType(EventPayloadInvalidException.class).isThrownBy(() -> fromJson(json));
    }

    @Test
    void testFromJsonString_JsonStringMissingNameField_ThrowEventPayloadInvalidException() {
        // given
        var id = UUID.randomUUID().toString();

        // when
        var json = String.format("{\"id\":\"%s\"}", id);

        // then
        assertThatExceptionOfType(EventPayloadInvalidException.class).isThrownBy(() -> fromJson(json));
    }

    @Test
    void testFromJsonString_JsonStringNullIdField_ThrowEventPayloadInvalidException() {
        // given
        var name = "name";

        // when
        var json = String.format("{\"id\":null,\"name\":\"%s\"}", name);

        // then
        assertThatExceptionOfType(EventPayloadInvalidException.class).isThrownBy(() -> fromJson(json));
    }

    @Test
    void testFromJsonString_JsonStringNullNameField_ThrowEventPayloadInvalidException() {
        // given
        var id = UUID.randomUUID().toString();

        // when
        var json = String.format("{\"id\":\"%s\",\"name\":null}", id);

        // then
        assertThatExceptionOfType(EventPayloadInvalidException.class).isThrownBy(() -> fromJson(json));
    }
}
