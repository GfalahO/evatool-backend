package com.evatool.impact.domain.entity;

import com.evatool.impact.common.exception.PropertyViolationException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class SuperEntityTest {
    @Test
    public void testCreateEntity_CreatedSuperEntity_IdIsNull() {
        // given
        var superEntity = getSuperEntity();

        // when

        // then
        assertThat(superEntity.getId()).isNull();
    }

    @Test
    public void testSetId_ValidToNullValue_ThrowPropertyViolationException() {
        // given
        var superEntity = getSuperEntity();

        // when
        superEntity.setId(UUID.randomUUID().toString());

        // then
        assertThatExceptionOfType(PropertyViolationException.class).isThrownBy(() -> superEntity.setId(null));
    }

    @Test
    public void testSetId_IllegalValue_ThrowPropertyViolationException() {
        // given
        var superEntity = getSuperEntity();

        // when

        // then
        assertThatExceptionOfType(PropertyViolationException.class).isThrownBy(() -> superEntity.setId("not uuid"));
    }

    @Test
    public void testSetId_IllegalValueSequence_ThrowPropertyViolationException() {
        // given
        var superEntity = getSuperEntity();

        // when
        superEntity.setId(UUID.randomUUID().toString());

        // then
        assertThatExceptionOfType(PropertyViolationException.class).isThrownBy(() -> superEntity.setId(UUID.randomUUID().toString()));
    }

    @Test
    public void testSetId_NullValue_DoNotThrowException() {
        // given
        var superEntity = getSuperEntity();

        // when
        superEntity.setId(null);

        // then
    }

    @Test
    public void testSetId_LegalValue_DoNotThrowException() {
        // given
        var superEntity = getSuperEntity();

        // when
        superEntity.setId(UUID.randomUUID().toString());

        // then
    }

    private SuperEntityImpl getSuperEntity() {
        return new SuperEntityImpl();
    }

    private class SuperEntityImpl extends SuperEntity {

    }
}
