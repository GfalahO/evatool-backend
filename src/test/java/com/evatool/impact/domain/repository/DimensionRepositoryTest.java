package com.evatool.impact.domain.repository;

import com.evatool.impact.domain.entity.Dimension;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.evatool.impact.common.TestDataGenerator.createDummyDimension;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DimensionRepositoryTest {

    @Autowired
    private DimensionRepository dimensionRepository;

    @Test
    void testAllFindByType_ExistingEntities_ReturnEntitiesByType() {
        // given
        int n_socialDimensions = 3;
        for (int i = 0; i < n_socialDimensions; i++) {
            var socialDimension = createDummyDimension();
            socialDimension.setType(Dimension.Type.SOCIAL);
            dimensionRepository.save(socialDimension);
        }

        int n_economicDimensions = 4;
        for (int i = 0; i < n_economicDimensions; i++) {
            var economicDimension = createDummyDimension();
            economicDimension.setType(Dimension.Type.ECONOMIC);
            dimensionRepository.save(economicDimension);
        }

        // when
        var socialDimensions = dimensionRepository.findAllByType(Dimension.Type.SOCIAL);
        var economicDimension = dimensionRepository.findAllByType(Dimension.Type.ECONOMIC);

        // then
        assertThat(socialDimensions.size()).isEqualTo(n_socialDimensions);
        assertThat(economicDimension.size()).isEqualTo(n_economicDimensions);
    }
}
