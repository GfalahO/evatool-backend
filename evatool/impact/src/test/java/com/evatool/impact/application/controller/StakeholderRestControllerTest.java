package com.evatool.impact.application.controller;

import com.evatool.impact.application.controller.uri.StakeholderRestUri;
import com.evatool.impact.application.dto.ImpactDto;
import com.evatool.impact.application.dto.StakeholderDto;
import com.evatool.impact.application.service.StakeholderService;
import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.common.exception.IdNullException;
import com.evatool.impact.common.exception.handle.ErrorMessage;
import com.evatool.impact.domain.entity.Stakeholder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.UUID;

import static com.evatool.impact.application.dto.mapper.StakeholderMapper.fromDto;
import static com.evatool.impact.application.dto.mapper.StakeholderMapper.toDto;
import static com.evatool.impact.TestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StakeholderRestControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private StakeholderService stakeholderService;

    @BeforeEach
    public void clearDatabase() throws IdNullException, EntityNotFoundException {
        var stakeholders = stakeholderService.getAllStakeholders();
        for (var s : stakeholders) {
            stakeholderService.deleteStakeholderById(s.getId());
        }
    }

    //region getById

    @Test
    public void testGetStakeholderById_InsertedStakeholder_ReturnStakeholder() {
        // given
        var stakeholder = getStakeholder();
        var stakeholderDto = toDto(stakeholder);
        var httpEntity = new HttpEntity<>(stakeholderDto);
        var postResponse = testRestTemplate.postForEntity(
                StakeholderRestUri.buildCreateStakeholderUri(), httpEntity, StakeholderDto.class);

        // when
        var getResponse = testRestTemplate.getForEntity(
                StakeholderRestUri.buildGetStakeholderUri(postResponse.getBody().getId()), StakeholderDto.class);

        // then
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody().getId()).isNotNull();
        assertThat(getResponse.getBody().getId()).isEqualTo(postResponse.getBody().getId());
        assertThat(getResponse.getBody().getName()).isEqualTo(postResponse.getBody().getName());
    }

    @Test
    public void testGetStakeholderById_NoExistingStakeholder_ReturnHttpStatusNotFound() {
        // given
        var responseEntity = testRestTemplate.getForEntity(
                StakeholderRestUri.buildGetStakeholderUri("wrong_id"), ErrorMessage.class);

        // when

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        var errorMessage = EntityNotFoundException.MESSAGE_FORMAT
                .replaceFirst("%s", Stakeholder.class.getSimpleName())
                .replaceFirst("%s", "wrong_id");
        assertThat(responseEntity.getBody().getMessage()).isEqualTo(errorMessage);
    }

    //endregion

    //region getAll

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testGetStakeholders_ExistingStakeholder_ReturnStakeholder() {
        // given
        var stakeholder = getStakeholder();
        var stakeholderDto = toDto(stakeholder);
        var httpEntity = new HttpEntity<>(stakeholderDto);
        var postResponse = testRestTemplate.postForEntity(
                StakeholderRestUri.buildCreateStakeholderUri(), httpEntity, StakeholderDto.class);

        // when
        var getResponse = testRestTemplate.getForEntity(
                StakeholderRestUri.buildGetStakeholdersUri(), StakeholderDto[].class);
        var stakeholders = getResponse.getBody();

        // then
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(stakeholders[0].getId()).isNotNull();
        assertThat(stakeholders[0].getId()).isEqualTo(postResponse.getBody().getId());
        assertThat(stakeholders[0].getName()).isEqualTo(postResponse.getBody().getName());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testGetStakeholders_ExistingStakeholders_ReturnStakeholders(int value) {
        var postResponseList = new ArrayList<ResponseEntity<StakeholderDto>>();
        for (int i = 0; i < value; i++) {
            // given
            var stakeholder = getStakeholder();
            var stakeholderDto = toDto(stakeholder);
            var httpEntity = new HttpEntity<>(stakeholderDto);
            var postResponse = testRestTemplate.postForEntity(
                    StakeholderRestUri.buildCreateStakeholderUri(), httpEntity, StakeholderDto.class);
            postResponseList.add(postResponse);
        }

        // when
        var getResponse = testRestTemplate.getForEntity(
                StakeholderRestUri.buildGetStakeholdersUri(), StakeholderDto[].class);
        var stakeholderDtos = getResponse.getBody();

        // then
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(stakeholderDtos.length).isEqualTo(postResponseList.size());
    }

    //endregion

    //region insert

    @Test
    public void testInsertStakeholder_InsertStakeholder_ReturnInsertedStakeholder() {
        // given
        var stakeholder = getStakeholder();
        var stakeholderDto = toDto(stakeholder);

        // when
        var httpEntity = new HttpEntity<>(stakeholderDto);
        var responseEntity = testRestTemplate.postForEntity(
                StakeholderRestUri.buildCreateStakeholderUri(), httpEntity, StakeholderDto.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody().getId()).isNotNull();
        assertThat(responseEntity.getBody().getName()).isEqualTo(stakeholder.getName());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testInsertStakeholder_InsertDuplicateStakeholder_AllowDuplicateInsert() {
        // given
        var stakeholder = getStakeholder();
        var stakeholderDto = toDto(stakeholder);

        // when
        var httpEntity = new HttpEntity<>(stakeholderDto);
        var responseEntity = testRestTemplate.postForEntity(
                StakeholderRestUri.buildCreateStakeholderUri(), httpEntity, StakeholderDto.class);

        var stakeholderDtoDuplicate = responseEntity.getBody();
        var httpEntityDuplicate = new HttpEntity<>(stakeholderDtoDuplicate);
        var responseEntityDuplicate = testRestTemplate.postForEntity(
                StakeholderRestUri.buildCreateStakeholderUri(), httpEntityDuplicate, StakeholderDto.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody().getId()).isEqualTo(responseEntityDuplicate.getBody().getId());
        assertThat(responseEntity.getBody().getName()).isEqualTo(responseEntityDuplicate.getBody().getName());

        var getResponse = testRestTemplate.getForEntity(
                StakeholderRestUri.buildGetStakeholdersUri(), StakeholderDto[].class);
        var stakeholders = getResponse.getBody();
        assertThat(stakeholders.length).isEqualTo(1);
    }

    // Note: RestController code is not being executed. The error is automatically thrown.
    @Test
    public void testInsertStakeholder_InsertNullDto_ReturnHttpStatusUnsupportedMediaType() {
        // given
        StakeholderDto stakeholderDto = null;

        // when
        var httpEntity = new HttpEntity<>(stakeholderDto);
        var responseEntity = testRestTemplate.postForEntity(
                StakeholderRestUri.buildCreateStakeholderUri(), httpEntity, StakeholderDto.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @Test
    public void testInsertStakeholder_InsertEmptyImpactDto_ReturnHttpStatusBadRequest() {
        // given
        ImpactDto stakeholderDto = getEmptyImpactDto();

        // when
        var httpEntity = new HttpEntity<>(stakeholderDto);
        var responseEntity = testRestTemplate.postForEntity(
                StakeholderRestUri.buildCreateStakeholderUri(), httpEntity, ErrorMessage.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testInsertStakeholder_InsertImpactDto_ReturnHttpStatusBadRequest() {
        // given
        ImpactDto stakeholderDto = getImpactDto();

        // when
        var httpEntity = new HttpEntity<>(stakeholderDto);
        var responseEntity = testRestTemplate.postForEntity(
                StakeholderRestUri.buildCreateStakeholderUri(), httpEntity, ErrorMessage.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testInsertStakeholder_InsertStakeholderWithNullName_ReturnHttpStatusBadRequest() {
        // given
        StakeholderDto stakeholderDto = getEmptyStakeholderDto();

        // when
        var httpEntity = new HttpEntity<>(stakeholderDto);
        var responseEntity = testRestTemplate.postForEntity(
                StakeholderRestUri.buildCreateStakeholderUri(), httpEntity, StakeholderDto.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    //endregion

    //region update

    @Test
    public void testUpdateStakeholder_InsertedStakeholder_ReturnUpdatedStakeholder() {
        // given
        var stakeholder = getStakeholder();
        var stakeholderDto = toDto(stakeholder);
        var httpEntity = new HttpEntity<>(stakeholderDto);
        var postResponse = testRestTemplate.postForEntity(
                StakeholderRestUri.buildCreateStakeholderUri(), httpEntity, StakeholderDto.class);

        // when
        var updatedStakeholder = fromDto(postResponse.getBody());
        updatedStakeholder.setName("new_name");
        var putEntity = new HttpEntity<>(toDto(updatedStakeholder));
        var putResponse = testRestTemplate.exchange(
                StakeholderRestUri.buildUpdateStakeholderUri(postResponse.getBody().getId()), HttpMethod.PUT, putEntity, StakeholderDto.class);
        var getResponse = testRestTemplate.getForEntity(
                StakeholderRestUri.buildGetStakeholderUri(postResponse.getBody().getId()), StakeholderDto.class);

        // then
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(putResponse.getBody().getId()).isEqualTo(updatedStakeholder.getId());
        assertThat(putResponse.getBody().getName()).isEqualTo(updatedStakeholder.getName());

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody().getId()).isNotNull();
        assertThat(getResponse.getBody().getId()).isEqualTo(updatedStakeholder.getId());
        assertThat(getResponse.getBody().getName()).isEqualTo(updatedStakeholder.getName());
    }

    @Test
    public void testUpdateStakeholder_UpdateNonExistingId_ReturnHttpStatusNotFound() {
        // given
        var stakeholder = getStakeholder();
        stakeholder.setId(UUID.randomUUID().toString());
        var stakeholderDto = toDto(stakeholder);
        var httpEntity = new HttpEntity<>(stakeholderDto);

        // when
        var putResponse = testRestTemplate.exchange(
                StakeholderRestUri.buildUpdateStakeholderUri(stakeholder.getId()), HttpMethod.PUT, httpEntity, StakeholderDto.class);

        // then
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testUpdateStakeholder_UpdateNullId_ReturnHttpStatusNotFound() {
        // given
        var stakeholder = getStakeholder();
        var stakeholderDto = toDto(stakeholder);
        var httpEntity = new HttpEntity<>(stakeholderDto);

        // when
        var putResponse = testRestTemplate.exchange(
                StakeholderRestUri.buildUpdateStakeholderUri("null"), HttpMethod.PUT, httpEntity, StakeholderDto.class);

        // then
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST); // Not NOT_FOUND: non UUID id throws exception in SuperEntity.
    }

    @Test
    public void testUpdateStakeholder_UpdateNullName_ReturnHttpStatusBadRequest() {
        // given
        var stakeholder = getStakeholder();
        var stakeholderDto = toDto(stakeholder);
        var httpEntity = new HttpEntity<>(stakeholderDto);
        var postResponse = testRestTemplate.postForEntity(
                StakeholderRestUri.buildCreateStakeholderUri(), httpEntity, StakeholderDto.class);

        // when
        var updatedStakeholder = fromDto(postResponse.getBody());
        var updatedStakeholderDto = toDto(updatedStakeholder);
        updatedStakeholderDto.setName(null);
        var putEntity = new HttpEntity<>(updatedStakeholderDto);
        var putResponse = testRestTemplate.exchange(
                StakeholderRestUri.buildUpdateStakeholderUri(postResponse.getBody().getId()), HttpMethod.PUT, putEntity, StakeholderDto.class);

        // then
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    // Note: RestController code is not being executed. The error is automatically thrown.
    @Test
    public void testUpdateStakeholder_UpdateNullDtoIntoExistingId_ReturnHttpStatusBadRequest() {
        // given
        var stakeholder = getStakeholder();
        var stakeholderDto = toDto(stakeholder);
        var httpEntity = new HttpEntity<>(stakeholderDto);
        var postResponse = testRestTemplate.postForEntity(
                StakeholderRestUri.buildCreateStakeholderUri(), httpEntity, StakeholderDto.class);

        // when
        var updatedStakeholder = fromDto(postResponse.getBody());
        var updatedStakeholderDto = toDto(updatedStakeholder);
        updatedStakeholderDto = null;
        var putEntity = new HttpEntity<>(updatedStakeholderDto);
        var putResponse = testRestTemplate.exchange(
                StakeholderRestUri.buildUpdateStakeholderUri(postResponse.getBody().getId()), HttpMethod.PUT, putEntity, ErrorMessage.class);

        // then
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    // Note: RestController code is not being executed. The error is automatically thrown.
    @Test
    public void testUpdateStakeholder_UpdateNullDtoIntoNonExistingId_ReturnHttpStatusBadRequest() {
        // given
        StakeholderDto stakeholderDto = null;

        // when
        var putEntity = new HttpEntity<>(stakeholderDto);
        var putResponse = testRestTemplate.exchange(
                StakeholderRestUri.buildUpdateStakeholderUri(UUID.randomUUID().toString()), HttpMethod.PUT, putEntity, StakeholderDto.class);

        // then
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    //endregion

    //region delete

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testDeleteStakeholder_ExistingStakeholder_DeleteStakeholderAndReturnHttpStatusOK() {
        // given
        var stakeholder = getStakeholder();
        var stakeholderDto = toDto(stakeholder);
        var insertEntity = new HttpEntity<>(stakeholderDto);
        var insertEntityResponse = testRestTemplate.postForEntity(
                StakeholderRestUri.buildCreateStakeholderUri(), insertEntity, StakeholderDto.class);

        // when
        var insertedStakeholderDto = insertEntityResponse.getBody();
        var deleteEntity = new HttpEntity<>(insertedStakeholderDto);
        var deleteEntityResponse = testRestTemplate.exchange(
                StakeholderRestUri.buildDeleteStakeholderUri(insertedStakeholderDto.getId()), HttpMethod.DELETE, deleteEntity, Void.class);

        // then
        assertThat(deleteEntityResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        var getResponse = testRestTemplate.getForEntity(
                StakeholderRestUri.buildGetStakeholdersUri(), StakeholderDto[].class);
        var stakeholders = getResponse.getBody();
        assertThat(stakeholders.length).isEqualTo(0);
    }

    @Test
    public void testDeleteStakeholder_DeleteNonExistingId_ReturnHttpStatusNotFound() {
        // given
        var stakeholderDto = getStakeholderDto();
        stakeholderDto.setId(UUID.randomUUID().toString());

        // when
        var httpEntity = new HttpEntity<>(stakeholderDto);
        var responseEntity = testRestTemplate.exchange(
                StakeholderRestUri.buildDeleteStakeholderUri(stakeholderDto.getId()), HttpMethod.DELETE, httpEntity, Void.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testDeleteStakeholder_DeleteNullId_ReturnHttpStatusBadRequest() {
        // given
        var stakeholder = getStakeholder();
        var stakeholderDto = toDto(stakeholder);
        var httpEntity = new HttpEntity<>(stakeholderDto);

        // when
        var responseEntity = testRestTemplate.exchange(
                StakeholderRestUri.buildDeleteStakeholderUri("null"), HttpMethod.DELETE, httpEntity, Void.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    //endregion

    // TODO [hbuhl] Check ErrorMessage validity (for all non-successful http status tests) + in service + exception? Overkill?

}
