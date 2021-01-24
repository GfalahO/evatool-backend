package com.fae.evatool.impact.tests.persistence.repository;

import com.fae.evatool.impact.persistence.repository.AnalysisRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.fae.evatool.impact.tests.persistence.TestDataGenerator.getAnalysis;

@DataJpaTest
public class AnalysisRepositoryTests {
    @Autowired
    private AnalysisRepository analysisRepository;

    @Test
    public void testFindById_ExistingDimension_ReturnDimension() {
        // given
        var analysis = getAnalysis();
        analysisRepository.save(analysis);

        // when
        var found = analysisRepository.findById(analysis.getId()).orElse(null);

        // then
        Assert.assertEquals(analysis.getId(), found.getId());
    }
}
