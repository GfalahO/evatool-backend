package com.evatool.requirements.domain.repository;

import com.evatool.requirements.common.TestDataGenerator;
import com.evatool.requirements.repository.RequirementRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RequirementRepositoryTest {
    @Autowired
    private RequirementRepository requirementRepository;

    @Test
    public void testFindById_ExistingRequirement_ReturnRequirement() {
        // given
        var requirement = TestDataGenerator.getRequirement();
        requirementRepository.save(requirement);

        // when
        var found = requirementRepository.findById(requirement.getId()).orElse(null);

        // then
        assertThat(found.getId()).isEqualTo(requirement.getId());
    }
}
