package com.fae.evatool.impact.tests.persistence.repository;

import com.fae.evatool.impact.persistence.repository.RequirementRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.fae.evatool.impact.tests.persistence.TestDataGenerator.getRequirement;

@DataJpaTest
public class RequirementRepositoryTests {
    @Autowired
    private RequirementRepository requirementRepository;

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
}
