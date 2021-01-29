package com.evatool.impact.persistence.entity;

import com.evatool.impact.persistence.TestDataGenerator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

public class StakeholderTest {
    @Test
    public void testToString_DefaultObject_DoNotThrowException() {
        // given
        var stakeholder = TestDataGenerator.getStakeholder();

        // when

        // then
        stakeholder.toString();
    }

    @Test
    public void testCreateEntity_CreatedStakeholder_IdIsNull() {
        // given
        var stakeholder = TestDataGenerator.getStakeholder();

        // when

        // then
        assertThat(stakeholder.getId()).isNull();
    }

    @Test
    public void testSetId_NullValue_ThrowException() {
        // given
        var stakeholder = TestDataGenerator.getStakeholder();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> stakeholder.setId(null));
    }

    @Test
    public void testSetId_IllegalValue_ThrowException() {
        // given
        var stakeholder = TestDataGenerator.getStakeholder();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> stakeholder.setId("not uuid"));
    }

    @Test
    public void testSetId_IllegalValueSequence_ThrowException() {
        // given
        var stakeholder = TestDataGenerator.getStakeholder();

        // when
        stakeholder.setId(UUID.randomUUID().toString());

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> stakeholder.setId(UUID.randomUUID().toString()));
    }

    @Test
    public void testSetId_LegalValue_DoNotThrowException() {
        // given
        var stakeholder = TestDataGenerator.getStakeholder();

        // when
        stakeholder.setId(UUID.randomUUID().toString());

        // then
    }

    @Test
    public void testSetName_NullValue_ThrowException() {
        // given
        var stakeholder = TestDataGenerator.getStakeholder();

        // when

        // then
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> stakeholder.setName(null));
    }
}
