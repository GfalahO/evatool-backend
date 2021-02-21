package com.evatool.impact.domain.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static com.evatool.impact.common.TestDataGenerator.createDummyDimension;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SuperEntityUuidGeneratorTest {

    @Autowired
    private DimensionRepository dimensionRepository;

    @Test
    void testPersist_NullId_Allow() {
        // given
        var superEntity = createDummyDimension();

        // when
        var savedSuperEntity = dimensionRepository.save(superEntity);

        // then
        assertThat(savedSuperEntity.getId()).isNotNull();
    }

    @Test
    void testPersist_NotNullId_Allow() {
        // given
        var superEntity = createDummyDimension();
        var id = UUID.randomUUID();
        superEntity.setId(id);

        // when
        var savedSuperEntity = dimensionRepository.save(superEntity);

        // then
        assertThat(savedSuperEntity.getId()).isNotNull();
        assertThat(savedSuperEntity.getId()).isEqualTo(id);
    }
}
