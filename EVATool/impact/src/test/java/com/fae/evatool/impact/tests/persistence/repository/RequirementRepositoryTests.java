package com.fae.evatool.impact.tests.persistence.repository;

import com.fae.evatool.impact.persistence.repository.ImpactRepository;
import com.fae.evatool.impact.persistence.repository.RequirementRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.fae.evatool.impact.tests.persistence.TestDataGenerator.*;
import static com.fae.evatool.impact.tests.persistence.TestDataGenerator.getImpact;

@DataJpaTest
public class RequirementRepositoryTests {
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
        Assert.assertEquals(requirement.getId(), found.getId());
    }

    @Test
    public void testAddImpact_ReturnSavedImpacts() {
        // given
        var requirement = getRequirement();
        requirementRepository.save(requirement);

        // when
        var impact1 = getImpact();
        var impact2 = getImpact();
        requirement.addImpact(impact1);
        requirement.addImpact(impact2);
        requirementRepository.save(requirement); // Why does this line require Impact to have a default constructor?
        var found = requirementRepository.findById(requirement.getId()).orElse(null);

        // then
        Assert.assertEquals(found.getImpacts(), requirement.getImpacts());
    }

    @Test
    public void testAddImpact_ReturnSavedRequirements_Reversed() {
        // given
        var requirement = getRequirement();
        var impact1 = getImpact();
        var impact2 = getImpact();

        // Is this how many to many relationships are created?
        requirement.addImpact(impact1);
        requirement.addImpact(impact2);
        impact1.addRequirement(requirement);
        impact2.addRequirement(requirement);

        // when
        impactRepository.save(impact1);
        impactRepository.save(impact2);
        requirementRepository.save(requirement); // Why does this line require Impact to have a default constructor?

        var found = requirementRepository.findById(requirement.getId()).orElse(null);
        var foundImpact1 = impactRepository.findById(impact1.getId()).orElse(null);
        var foundImpact2 = impactRepository.findById(impact1.getId()).orElse(null);

        // then
        Assert.assertEquals(found, foundImpact1.getRequirements().get(0));
        Assert.assertEquals(found, foundImpact2.getRequirements().get(0));
    }
}
