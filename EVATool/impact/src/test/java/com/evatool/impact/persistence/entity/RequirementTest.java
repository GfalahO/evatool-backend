package com.evatool.impact.persistence.entity;

import com.evatool.impact.persistence.TestDataGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class RequirementTest {
    @Test
    public void testToString_DefaultObject_DoNotThrowException() {
        // given
        var impact = TestDataGenerator.getImpact();

        // when

        // then
        impact.toString();
    }

    @Test
    public void testAddImpacts_RelationshipInserted_ReturnRequirements() {
        // given
        var requirement = TestDataGenerator.getRequirement();
        var impact1 = TestDataGenerator.getImpact();
        var impact2 = TestDataGenerator.getImpact();

        // when
        requirement.getImpacts().add(impact1);
        requirement.getImpacts().add(impact2);
        impact1.getRequirements().add(requirement);
        impact2.getRequirements().add(requirement);

        // then
        assertThat(requirement).isEqualTo(impact1.getRequirements().get(0));
        assertThat(requirement).isEqualTo(impact2.getRequirements().get(0));
    }
}
