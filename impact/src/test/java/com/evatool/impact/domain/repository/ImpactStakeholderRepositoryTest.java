package com.evatool.impact.domain.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static com.evatool.impact.common.TestDataGenerator.createDummyStakeholder;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ImpactStakeholderRepositoryTest {

    private final ImpactStakeholderRepository stakeholderRepository;

    @Autowired
    public ImpactStakeholderRepositoryTest(ImpactStakeholderRepository stakeholderRepository) {
        this.stakeholderRepository = stakeholderRepository;
    }

    @Test
    public void testFindById_InsertedStakeholder_ReturnStakeholder() {
        // given
        var stakeholder = createDummyStakeholder();
        stakeholderRepository.save(stakeholder);

        // when
        var found = stakeholderRepository.findById(stakeholder.getId()).orElse(null);

        // then
        assertThat(found.getId()).isEqualTo(stakeholder.getId());
    }

    @Test
    public void testFindByName_InsertedStakeholder_ReturnStakeholder() {
        // given
        var stakeholder = createDummyStakeholder();
        stakeholderRepository.save(stakeholder);

        // when
        var found = stakeholderRepository.findByName(stakeholder.getName()).orElse(null);

        // then
        assertThat(found.getName()).isEqualTo(stakeholder.getName());
    }

    @Test
    public void testSave_InsertedStakeholder_IdIsNotNull() {
        // given
        var stakeholder = createDummyStakeholder();

        // when
        stakeholderRepository.save(stakeholder);

        // then
        assertThat(stakeholder.getId()).isNotNull();
    }

    @Test
    public void testSave_InsertedStakeholder_IdIsUuid() {
        // given
        var stakeholder = createDummyStakeholder();

        // when
        stakeholderRepository.save(stakeholder);

        // then
        UUID.fromString(stakeholder.getId());
    }

    @Test
    public void testSave_PresetId_Allow() {
        // given
        var stakeholder = createDummyStakeholder();
        stakeholder.setId(UUID.randomUUID().toString());

        // when

        // then
        stakeholderRepository.save(stakeholder);
    }

    @Test
    public void testSave_UpdatedStakeholder_ReturnUpdatedDimension() {
        // given
        var stakeholder = createDummyStakeholder();
        stakeholderRepository.save(stakeholder);
        var newName = "new_name";

        // when
        stakeholder.setName(newName);
        stakeholderRepository.save(stakeholder);
        var changedDimension = stakeholderRepository.findById(stakeholder.getId()).orElse(null);

        // then
        assertThat(changedDimension.getName()).isEqualTo(newName);
    }

    @Test
    public void testDelete_DeletedStakeholder_ReturnNull() {
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
