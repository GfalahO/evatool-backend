package com.evatool.impact.persistence.repository;

import com.evatool.impact.persistence.TestDataGenerator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.evatool.impact.persistence.TestDataGenerator.getImpact;
import static com.evatool.impact.persistence.TestDataGenerator.getRequirement;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class RequirementRepositoryTest {
    @Autowired
    private RequirementRepository requirementRepository;

    @Autowired
    private ImpactRepository impactRepository;

    @Test
    public void testFindById_ExistingRequirement_ReturnRequirement() {
        // given
        var requirement = getRequirement();
        requirementRepository.save(requirement);

        // when
        var found = requirementRepository.findById(requirement.getId()).orElse(null);

        // then
        assertThat(found.getId()).isEqualTo(requirement.getId());
    }

    @Test
    public void testAddImpact_RelationshipInserted_ReturnSavedImpacts() {
        // given
        var requirement = getRequirement();
        requirementRepository.save(requirement);

        // when
        var impact1 = getImpact();
        var impact2 = getImpact();
        requirement.getImpacts().add(impact1);
        requirement.getImpacts().add(impact2);
        requirementRepository.save(requirement); // Why does this line require Impact to have a default constructor?
        var found = requirementRepository.findById(requirement.getId()).orElse(null);

        // then
        assertThat(found.getImpacts()).isEqualTo(requirement.getImpacts());
    }

    @Test
    public void testAddImpact_RelationshipInserted_ReturnSavedRequirements() {
        // given
        var requirement = getRequirement();
        var impact1 = getImpact();
        var impact2 = getImpact();

        // Is this how many to many relationships are created?
        requirement.getImpacts().add(impact1);
        requirement.getImpacts().add(impact2);
        impact1.getRequirements().add(requirement);
        impact2.getRequirements().add(requirement);

        // when
        impactRepository.save(impact1);
        impactRepository.save(impact2);
        requirementRepository.save(requirement); // Why does this line require Impact to have a default constructor?

        var found = requirementRepository.findById(requirement.getId()).orElse(null);
        var foundImpact1 = impactRepository.findById(impact1.getId()).orElse(null);
        var foundImpact2 = impactRepository.findById(impact1.getId()).orElse(null);

        // then
        assertThat(found).isEqualTo(foundImpact1.getRequirements().get(0));
        assertThat(found).isEqualTo(foundImpact2.getRequirements().get(0));
    }
}
