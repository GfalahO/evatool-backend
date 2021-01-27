package com.evatool.impact.persistence.repository;

import com.evatool.impact.persistence.TestDataGenerator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class ImpactRepositoryTest {
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
        assertThat(found.getId()).isEqualTo(impact.getId());
    }
}
