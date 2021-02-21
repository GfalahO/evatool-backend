package com.evatool.impact.domain.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.evatool.impact.common.TestDataGenerator.createDummyStakeholder;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ImpactStakeholderRepositoryTest {

    @Autowired
    private ImpactStakeholderRepository stakeholderRepository;

    @Test
    void testFindById_InsertedStakeholder_ReturnStakeholder() {
        // given
        var stakeholder = createDummyStakeholder();
        stakeholderRepository.save(stakeholder);

        // when
        var found = stakeholderRepository.findById(stakeholder.getId());

        // then
        assertThat(found).isPresent();
    }

    @Test
    void testFindByName_InsertedStakeholder_ReturnStakeholder() {
        // given
        var stakeholder = createDummyStakeholder();
        stakeholderRepository.save(stakeholder);

        // when
        var found = stakeholderRepository.findByName(stakeholder.getName());

        // then
        assertThat(found).isPresent();
    }

    @Test
    void testSave_InsertedStakeholder_IdIsNotNull() {
        // given
        var stakeholder = createDummyStakeholder();

        // when
        stakeholderRepository.save(stakeholder);

        // then
        assertThat(stakeholder.getId()).isNotNull();
    }

    @Test
    void testSave_UpdatedStakeholder_ReturnUpdatedDimension() {
        // given
        var stakeholder = createDummyStakeholder();
        stakeholderRepository.save(stakeholder);
        var newName = "new_name";

        // when
        stakeholder.setName(newName);
        stakeholderRepository.save(stakeholder);
        var changedDimension = stakeholderRepository.findById(stakeholder.getId()).orElse(null);

        // then
        assertThat(changedDimension).isNotNull();
        assertThat(changedDimension.getName()).isEqualTo(newName);
    }

    @Test
    void testDelete_DeletedStakeholder_ReturnNull() {
        // given
        var stakeholder = createDummyStakeholder();
        stakeholderRepository.save(stakeholder);

        // when
        stakeholderRepository.delete(stakeholder);
        var found = stakeholderRepository.findById(stakeholder.getId()).orElse(null);

        // then
        assertThat(found).isNull();
    }
}
