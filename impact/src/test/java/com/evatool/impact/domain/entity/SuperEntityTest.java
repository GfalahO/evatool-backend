package com.evatool.impact.domain.entity;

import com.evatool.impact.common.exception.PropertyViolationException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class SuperEntityTest {
    @Test
    void testCreateEntity_CreatedSuperEntity_IdIsNull() {
        // given
        var superEntity = getSuperEntity();

        // when

        // then
        assertThat(superEntity.getId()).isNull();
    }

    @Test
    void testSetId_ValidToNullValue_ThrowPropertyViolationException() {
        // given
        var superEntity = getSuperEntity();

        // when
        superEntity.setId(UUID.randomUUID().toString());

        // then
        assertThatExceptionOfType(PropertyViolationException.class).isThrownBy(() -> superEntity.setId(null));
    }

    @Test
    void testSetId_IllegalValue_ThrowPropertyViolationException() {
        // given
        var superEntity = getSuperEntity();

        // when

        // then
        assertThatExceptionOfType(PropertyViolationException.class).isThrownBy(() -> superEntity.setId("not uuid"));
    }

    @Test
    void testSetId_IllegalValueSequence_ThrowPropertyViolationException() {
        // given
        var superEntity = getSuperEntity();

        // when
        superEntity.setId(UUID.randomUUID().toString());

        // then
        var newId = UUID.randomUUID().toString();
        assertThatExceptionOfType(PropertyViolationException.class).isThrownBy(() -> superEntity.setId(newId));
    }

    private SuperEntityImpl getSuperEntity() {
        return new SuperEntityImpl();
    }

    private static class SuperEntityImpl extends SuperEntity {

    }
}
