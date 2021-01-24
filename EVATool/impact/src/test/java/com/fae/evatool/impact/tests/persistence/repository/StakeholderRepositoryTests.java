package com.fae.evatool.impact.tests.persistence.repository;

import com.fae.evatool.impact.persistence.repository.StakeholderRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.fae.evatool.impact.tests.persistence.TestDataGenerator.getStakeholder;

//@RunWith(SpringJUnit4ClassRunner.class) // Works with and without.
@DataJpaTest
public class StakeholderRepositoryTests {
    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Test
    public void testFindById_ExistingStakeholder_ReturnStakeholder() {
        // given
        var stakeholder = getStakeholder();
        stakeholderRepository.save(stakeholder);

        // when
        var found = stakeholderRepository.findById(stakeholder.getId()).orElse(null);

        // then
        Assert.assertEquals(stakeholder.getId(), found.getId());
    }
}
