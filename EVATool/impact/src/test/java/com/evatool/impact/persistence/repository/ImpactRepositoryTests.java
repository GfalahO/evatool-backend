package com.evatool.impact.persistence.repository;

import com.evatool.impact.persistence.TestDataGenerator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ImpactRepositoryTests {
    @Autowired
    private ImpactRepository impactRepository;

    @Test
    public void testFindById_ExistingImpact_ReturnImpact() {
        // given
        var impact = TestDataGenerator.getImpact();
        impactRepository.save(impact);

        // when
        var found = impactRepository.findById(impact.getId()).orElse(null);

        // then
        Assert.assertEquals(impact.getId(), found.getId());
    }
}
