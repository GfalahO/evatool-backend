package com.evatool.impact.persistence.repository;

import com.evatool.impact.persistence.TestDataGenerator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AnalysisRepositoryTests {
    @Autowired
    private AnalysisRepository analysisRepository;

    @Test
    public void testFindById_ExistingAnalysis_ReturnAnalysis() {
        // given
        var analysis = TestDataGenerator.getAnalysis();
        analysisRepository.save(analysis);

        // when
        var found = analysisRepository.findById(analysis.getId()).orElse(null);

        // then
        Assert.assertEquals(analysis.getId(), found.getId());
    }

    @Test
    public void testAddImpact_ReturnSavedImpacts() {
        // given
        var analysis = TestDataGenerator.getAnalysis();
        analysisRepository.save(analysis);

        // when
        var impact1 = TestDataGenerator.getImpact();
        var impact2 = TestDataGenerator.getImpact();
        analysis.getImpacts().add(impact1);
        analysis.getImpacts().add(impact2);
        analysisRepository.save(analysis); // Why does this line require Impact to have a default constructor?
        var found = analysisRepository.findById(analysis.getId()).orElse(null);

        // then
        Assert.assertEquals(found.getImpacts(), analysis.getImpacts());
    }
}
