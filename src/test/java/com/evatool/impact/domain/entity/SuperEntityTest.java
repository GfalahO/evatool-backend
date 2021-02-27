package com.evatool.impact.domain.entity;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class SuperEntityTest {

    @Test
    void testCreate_CreatedSuperEntity_IdIsNull() {
        // given
        var superEntity = new SuperEntity();

        // when

        // then
        assertThat(superEntity.getId()).isNull();
    }

    @Test
    void testSetId_ValidToNullValue_ThrowIllegalArgumentException() {
        // given
        var superEntity = new SuperEntity();

        // when
        superEntity.setId(UUID.randomUUID());

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> superEntity.setId(null));
    }

    @Test
    void testSetId_IllegalValueSequence_ThrowIllegalArgumentException() {
        // given
        var superEntity = new SuperEntity();

        // when
        superEntity.setId(UUID.randomUUID());

        // then
        var newId = UUID.randomUUID();
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> superEntity.setId(newId));
    }
}
