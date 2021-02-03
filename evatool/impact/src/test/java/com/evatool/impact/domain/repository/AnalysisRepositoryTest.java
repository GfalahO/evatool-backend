package com.evatool.impact.domain.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.evatool.impact.common.TestDataGenerator.getAnalysis;
import static com.evatool.impact.common.TestDataGenerator.getImpact;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AnalysisRepositoryTest {
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
        assertThat(found.getId()).isEqualTo(analysis.getId());
    }

    @Test
    public void testAddImpact_RelationshipInserted_ReturnSavedImpacts() {
        // given
        var analysis = getAnalysis();
        analysisRepository.save(analysis);

        // when
        var impact1 = getImpact();
        var impact2 = getImpact();
        analysis.getImpacts().add(impact1);
        analysis.getImpacts().add(impact2);
        //analysisRepository.save(analysis);
        var found = analysisRepository.findById(analysis.getId()).orElse(null);

        // then
        assertThat(analysis.getImpacts()).isEqualTo(found.getImpacts());
    }
}
