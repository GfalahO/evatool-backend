package com.fae.evatool.impact.tests.persistence.repository;

import com.fae.evatool.impact.persistence.repository.ImpactRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.fae.evatool.impact.tests.persistence.TestDataGenerator.getImpact;

@DataJpaTest
public class ImpactRepositoryTests {
    @Autowired
    private ImpactRepository impactRepository;

    @Test
    public void testFindById_ExistingDimension_ReturnDimension() {
        // given
        var impact = getImpact();
        impactRepository.save(impact);

        // when
        var found = impactRepository.findById(impact.getId()).orElse(null);

        // then
        Assert.assertEquals(impact.getId(), found.getId());
    }
}
