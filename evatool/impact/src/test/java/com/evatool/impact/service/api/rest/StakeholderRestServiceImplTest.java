package com.evatool.impact.service.api.rest;

import com.evatool.impact.exception.EntityNotFoundException;
import com.evatool.impact.exception.EntityNullException;
import com.evatool.impact.exception.IdNullException;
import com.evatool.impact.persistence.entity.Stakeholder;
import com.evatool.impact.service.impl.rest.StakeholderRestServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;

import java.util.UUID;

import static com.evatool.impact.persistence.TestDataGenerator.getStakeholder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
public class StakeholderRestServiceImplTest {
    @TestConfiguration
    private static class StakeholderRestServiceImplTestContextConfiguration {
        @Bean
        public StakeholderRestService stakeholderRestService() {
            return new StakeholderRestServiceImpl();
        }
    }

    @Autowired
    private StakeholderRestService stakeholderRestService;

    //region getById

    @Test
    public void testGetStakeholderById_NonExistingId_ThrowEntityNotFoundException() {
        // given
        var stakeholder = getStakeholder();
        stakeholder.setId(UUID.randomUUID().toString());

        // when

        // then
        assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> stakeholderRestService.getStakeholderById(stakeholder.getId()));
    }

    @Test
    public void testGetStakeholderById_NullId_ThrowIdNullException() {
        // given
        var stakeholder = getStakeholder();

        // when

        // then
        assertThatExceptionOfType(IdNullException.class).isThrownBy(() -> stakeholderRestService.getStakeholderById(stakeholder.getId()));
    }

    //endregion

    //region getAll

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testGetAllStakeholders_InsertedStakeholder_ReturnStakeholder() {
        // given
        var stakeholder1 = getStakeholder();

        // when
        var insertedStakeholder1 = stakeholderRestService.insertStakeholder(stakeholder1);
        var stakeholders = stakeholderRestService.getAllStakeholders();

        // then
        assertThat(stakeholders.size()).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testGetAllStakeholders_InsertedStakeholders_ReturnStakeholders(int value) {
        for (int i = 0; i < value; i++) {
            // given
            var stakeholder = getStakeholder();

            // when
            var insertedStakeholder = stakeholderRestService.insertStakeholder(stakeholder);
        }
        var stakeholders = stakeholderRestService.getAllStakeholders();

        // then
        assertThat(stakeholders.size()).isEqualTo(value);
    }

    //endregion

    //region insert

    @Test
    public void testInsertStakeholder_InsertedStakeholder_ReturnInsertedStakeholder() throws IdNullException, EntityNotFoundException {
        // given
        var stakeholder = getStakeholder();

        // when
        var insertedStakeholder = stakeholderRestService.insertStakeholder(stakeholder);
        var retrievedStakeholder = stakeholderRestService.getStakeholderById(insertedStakeholder.getId());

        // then
        assertThat(retrievedStakeholder).isNotNull();
        assertThat(insertedStakeholder.getId()).isEqualTo(retrievedStakeholder.getId());
        assertThat(insertedStakeholder.getName()).isEqualTo(retrievedStakeholder.getName());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testInsertStakeholder_DuplicateInsert_AllowDuplicateInsert() {
        // given
        var stakeholder = getStakeholder();

        // when
        var insertedStakeholder1 = stakeholderRestService.insertStakeholder(stakeholder);
        var insertedStakeholder2 = stakeholderRestService.insertStakeholder(stakeholder);
        var stakeholders = stakeholderRestService.getAllStakeholders();

        // then
        assertThat(stakeholders.size()).isEqualTo(1);
        assertThat(stakeholder.getId()).isEqualTo(insertedStakeholder1.getId());
        assertThat(stakeholder.getId()).isEqualTo(insertedStakeholder2.getId());
        assertThat(stakeholder.getName()).isEqualTo(insertedStakeholder1.getName());
        assertThat(stakeholder.getName()).isEqualTo(insertedStakeholder2.getName());
    }

    @Test
    public void testInsertStakeholder_NullStakeholder_ThrowEntityNullException() {
        // given
        Stakeholder stakeholder = null;

        // when

        // then
        assertThatExceptionOfType(EntityNullException.class).isThrownBy(() -> stakeholderRestService.insertStakeholder(stakeholder));
    }

    //endregion

    //region update

    @Test
    public void testUpdateStakeholder_UpdatedStakeholder_ReturnUpdatedStakeholder() throws IdNullException, EntityNotFoundException {
        // given
        var stakeholder = getStakeholder();
        var insertedStakeholder = stakeholderRestService.insertStakeholder(stakeholder);

        // when
        var newName = "new_name";
        insertedStakeholder.setName(newName);

        // then
        stakeholderRestService.updateStakeholder(insertedStakeholder);
        var updatedStakeholder = stakeholderRestService.getStakeholderById(insertedStakeholder.getId());
        assertThat(insertedStakeholder.getId()).isEqualTo(updatedStakeholder.getId());
        assertThat(updatedStakeholder.getName()).isEqualTo(newName);
    }

    @Test
    public void testUpdateStakeholder_UpdatedNonExistingId_ThrowEntityNotFoundException() throws IdNullException, EntityNotFoundException {
        // given
        var stakeholder = getStakeholder();
        stakeholder.setId(UUID.randomUUID().toString());

        // when

        // then
        assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> stakeholderRestService.updateStakeholder(stakeholder));
    }

    @Test
    public void testUpdateStakeholder_UpdatedNullId_ThrowEntityIdNullException() {
        // given
        var stakeholder = getStakeholder();

        // when

        // then
        assertThatExceptionOfType(IdNullException.class).isThrownBy(() -> stakeholderRestService.updateStakeholder(stakeholder));
    }

    //endregion

    //region delete

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testDeleteStakeholderById_DeleteStakeholder_ReturnNoStakeholders() throws IdNullException, EntityNotFoundException {
        // given
        var stakeholder = getStakeholder();

        // when
        var insertedStakeholder = stakeholderRestService.insertStakeholder(stakeholder);
        stakeholderRestService.deleteStakeholderById(insertedStakeholder.getId());

        // then
        var stakeholders = stakeholderRestService.getAllStakeholders();
        assertThat(stakeholders.size()).isEqualTo(0);
    }

    @Test
    public void testDeleteStakeholder_DeleteNonExistingId_ReturnHttpStatusNotFound() throws IdNullException, EntityNotFoundException {
        // given
        var stakeholder = getStakeholder();
        stakeholder.setId(UUID.randomUUID().toString());

        // when

        // then
        assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> stakeholderRestService.deleteStakeholderById(stakeholder.getId()));
    }

    //endregion

}
