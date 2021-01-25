package com.fae.evatool.impact.tests.persistence.repository;

import com.fae.evatool.impact.persistence.repository.AnalysisRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.fae.evatool.impact.tests.persistence.TestDataGenerator.getAnalysis;
import static com.fae.evatool.impact.tests.persistence.TestDataGenerator.getImpact;

@DataJpaTest
public class AnalysisRepositoryTests {
    @Autowired
    private AnalysisRepository analysisRepository;

    @Test
    public void testFindById_ExistingAnalysis_ReturnAnalysis() {
        // given
        var analysis = getAnalysis();
        analysisRepository.save(analysis);

        // when
        var found = analysisRepository.findById(analysis.getId()).orElse(null);

        // then
        Assert.assertEquals(analysis.getId(), found.getId());
    }

    @Test
    public void testAddImpact_ReturnSavedImpacts() {
        // given
        var analysis = getAnalysis();
        analysisRepository.save(analysis);

        // when
        var impact1 = getImpact();
        var impact2 = getImpact();
        analysis.addImpact(impact1);
        analysis.addImpact(impact2);
        analysisRepository.save(analysis); // Why does this line require Impact to have a default constructor?
        var found = analysisRepository.findById(analysis.getId()).orElse(null);

        // then
        Assert.assertEquals(found.getImpacts(), analysis.getImpacts());
    }
}
