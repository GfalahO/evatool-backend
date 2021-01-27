package com.evatool.impact.persistence.repository;

import com.evatool.impact.persistence.TestDataGenerator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

//@RunWith(SpringJUnit4ClassRunner.class) // Works with and without.
@DataJpaTest
public class StakeholderRepositoryTest {
    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Test
    public void testFindById_ExistingStakeholder_ReturnStakeholder() {
        // given
        var stakeholder = TestDataGenerator.getStakeholder();
        stakeholderRepository.save(stakeholder);

        // when
        var found = stakeholderRepository.findById(stakeholder.getId()).orElse(null);

        // then
        Assert.assertEquals(stakeholder.getId(), found.getId());
    }

    @Test
    public void testFindByName_ExistingStakeholder_ReturnStakeholder() {
        // given
        var stakeholder = TestDataGenerator.getStakeholder();
        stakeholderRepository.save(stakeholder);

        // when
        var found = stakeholderRepository.findByName(stakeholder.getName()).orElse(null);

        // then
        Assert.assertEquals(stakeholder.getName(), found.getName());
    }
}
