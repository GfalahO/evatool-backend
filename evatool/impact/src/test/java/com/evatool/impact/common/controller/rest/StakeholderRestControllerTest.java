package com.evatool.impact.common.controller.rest;

import com.evatool.impact.common.dto.StakeholderDto;
import com.evatool.impact.common.mapper.StakeholderMapper;
import com.evatool.impact.exception.handle.ErrorMessage;
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
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.UUID;

import static com.evatool.impact.persistence.TestDataGenerator.*;
import static com.evatool.impact.persistence.TestDataGenerator.getStakeholderDto;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StakeholderRestControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    private StakeholderMapper stakeholderMapper = new StakeholderMapper();

    //region getById

    // TODO: Are these kind of tests using an .sql file too tedious?
    @Test
    @Sql("/StakeholderRestControllerTest/Insert.sql")
    public void testGetStakeholderById_ExistingStakeholder_ReturnStakeholder() {
        // given
        var responseEntity = testRestTemplate.getForEntity("/api/stakeholder/id_1", StakeholderDto.class);

        // when

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getId()).isNotNull();
        assertThat(responseEntity.getBody().getId()).isEqualTo("id_1");
        assertThat(responseEntity.getBody().getName()).isEqualTo("stakeholder_1");
    }

    @Test
    public void testGetStakeholderById_InsertedStakeholder_ReturnStakeholder() {
        // given
        var stakeholder = getStakeholder();
        var stakeholderDto = stakeholderMapper.toDto(stakeholder);
        var httpEntity = new HttpEntity<>(stakeholderDto);
        var postResponse = testRestTemplate.postForEntity("/api/stakeholder", httpEntity, StakeholderDto.class);

        // when
        var getResponse = testRestTemplate.getForEntity(
                "/api/stakeholder/" + postResponse.getBody().getId(), StakeholderDto.class);

        // then
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody().getId()).isNotNull();
        assertThat(getResponse.getBody().getId()).isEqualTo(postResponse.getBody().getId());
        assertThat(getResponse.getBody().getName()).isEqualTo(postResponse.getBody().getName());
    }

    @Test
    public void testGetStakeholderById_NoExistingStakeholder_ReturnHttpStatusNotFound() {
        // given
        //var responseEntity = testRestTemplate.getForEntity("/api/stakeholder/wrong_id", StakeholderDto.class);
        var responseEntity = testRestTemplate.getForEntity("/api/stakeholder/wrong_id", ErrorMessage.class);

        // when

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(responseEntity.getBody().getMessage()).isNotNull();
        // TODO: Check ErrorMessage validity (for all non-successful http status tests)
    }

    //endregion

    //region getAll

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void testGetStakeholders_ExistingStakeholder_ReturnStakeholder() {
        // given
        var stakeholder = getStakeholder();
        var stakeholderDto = stakeholderMapper.toDto(stakeholder);
        var httpEntity = new HttpEntity<>(stakeholderDto);
        var postResponse = testRestTemplate.postForEntity("/api/stakeholder", httpEntity, StakeholderDto.class);

        // when
        var getResponse = testRestTemplate.getForEntity("/api/stakeholders", StakeholderDto[].class);
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
            var stakeholderDto = stakeholderMapper.toDto(stakeholder);
            var httpEntity = new HttpEntity<>(stakeholderDto);
            var postResponse = testRestTemplate.postForEntity("/api/stakeholder", httpEntity, StakeholderDto.class);
            postResponseList.add(postResponse);
        }

        // when
        var getResponse = testRestTemplate.getForEntity("/api/stakeholders", StakeholderDto[].class);
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
        var stakeholderDto = stakeholderMapper.toDto(stakeholder);

        // when
        var httpEntity = new HttpEntity<>(stakeholderDto);
        var responseEntity = testRestTemplate.postForEntity("/api/stakeholder", httpEntity, StakeholderDto.class);

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
        var stakeholderDto = stakeholderMapper.toDto(stakeholder);

        // when
        var httpEntity = new HttpEntity<>(stakeholderDto);
        var responseEntity = testRestTemplate.postForEntity("/api/stakeholder", httpEntity, StakeholderDto.class);

        var stakeholderDtoDuplicate = responseEntity.getBody();
        var httpEntityDuplicate = new HttpEntity<>(stakeholderDtoDuplicate);
        var responseEntityDuplicate = testRestTemplate.postForEntity(
                "/api/stakeholder", httpEntityDuplicate, StakeholderDto.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody().getId()).isEqualTo(responseEntityDuplicate.getBody().getId());
        assertThat(responseEntity.getBody().getName()).isEqualTo(responseEntityDuplicate.getBody().getName());

        var getResponse = testRestTemplate.getForEntity("/api/stakeholders", StakeholderDto[].class);
        var stakeholders = getResponse.getBody();
        assertThat(stakeholders.length).isEqualTo(1);
    }

    // TODO: Change code base to return bad request?
    // Note: RestController code is not being executed. The error is automatically thrown.
    @Test
    public void testInsertStakeholder_InsertNullDto_ReturnHttpStatusUnsupportedMediaType() {
        // given
        StakeholderDto stakeholderDto = null;

        // when
        var httpEntity = new HttpEntity<>(stakeholderDto);
        var responseEntity = testRestTemplate.postForEntity("/api/stakeholder", httpEntity, StakeholderDto.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @Test
    public void testInsertStakeholder_InsertStakeholderWithNullName_ReturnHttpStatusBadRequest() {
        // given
        StakeholderDto stakeholderDto = getEmptyStakeholderDto();

        // when
        var httpEntity = new HttpEntity<>(stakeholderDto);
        var responseEntity = testRestTemplate.postForEntity("/api/stakeholder", httpEntity, StakeholderDto.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    //endregion

    //region update

    @Test
    public void testUpdateStakeholder_InsertedStakeholder_ReturnUpdatedStakeholder() {
        // given
        var stakeholder = getStakeholder();
        var stakeholderDto = stakeholderMapper.toDto(stakeholder);
        var httpEntity = new HttpEntity<>(stakeholderDto);
        var postResponse = testRestTemplate.postForEntity("/api/stakeholder", httpEntity, StakeholderDto.class);

        // when
        var updatedStakeholder = stakeholderMapper.fromDto(postResponse.getBody());
        updatedStakeholder.setName("new_name");
        var putEntity = new HttpEntity<>(stakeholderMapper.toDto(updatedStakeholder));
        var putResponse = testRestTemplate.exchange(
                "/api/stakeholder/" + postResponse.getBody().getId(), HttpMethod.PUT, putEntity, StakeholderDto.class);
        var getResponse = testRestTemplate.getForEntity(
                "/api/stakeholder/" + postResponse.getBody().getId(), StakeholderDto.class);

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
        var stakeholderDto = stakeholderMapper.toDto(stakeholder);
        var httpEntity = new HttpEntity<>(stakeholderDto);

        // when
        var putResponse = testRestTemplate.exchange(
                "/api/stakeholder/" + stakeholder.getId(), HttpMethod.PUT, httpEntity, StakeholderDto.class);

        // then
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testUpdateStakeholder_UpdateNullId_ReturnHttpStatusBadRequest() {
        // given
        var stakeholder = getStakeholder();
        var stakeholderDto = stakeholderMapper.toDto(stakeholder);
        var httpEntity = new HttpEntity<>(stakeholderDto);

        // when
        var putResponse = testRestTemplate.exchange(
                "/api/stakeholder/" + stakeholder.getId(), HttpMethod.PUT, httpEntity, StakeholderDto.class);

        // then
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateStakeholder_UpdateNullName_ReturnHttpStatusBadRequest() {
        // given
        var stakeholder = getStakeholder();
        var stakeholderDto = stakeholderMapper.toDto(stakeholder);
        var httpEntity = new HttpEntity<>(stakeholderDto);
        var postResponse = testRestTemplate.postForEntity("/api/stakeholder", httpEntity, StakeholderDto.class);

        // when
        var updatedStakeholder = stakeholderMapper.fromDto(postResponse.getBody());
        var updatedStakeholderDto = stakeholderMapper.toDto(updatedStakeholder);
        updatedStakeholderDto.setName(null);
        var putEntity = new HttpEntity<>(updatedStakeholderDto);
        var putResponse = testRestTemplate.exchange(
                "/api/stakeholder/" + postResponse.getBody().getId(), HttpMethod.PUT, putEntity, StakeholderDto.class);

        // then
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    // TODO: Use custom NullDtoException? (for the next two tests)
    // Note: sending a null DTO will automatically result in a bad request. This error is thrown before the controller code executes.
    @Test
    public void testUpdateStakeholder_UpdateNullDtoIntoExistingId_ReturnHttpStatusBadRequest() {
        // given
        var stakeholder = getStakeholder();
        var stakeholderDto = stakeholderMapper.toDto(stakeholder);
        var httpEntity = new HttpEntity<>(stakeholderDto);
        var postResponse = testRestTemplate.postForEntity("/api/stakeholder", httpEntity, StakeholderDto.class);

        // when
        var updatedStakeholder = stakeholderMapper.fromDto(postResponse.getBody());
        var updatedStakeholderDto = stakeholderMapper.toDto(updatedStakeholder);
        updatedStakeholderDto = null;
        var putEntity = new HttpEntity<>(updatedStakeholderDto);
        var putResponse = testRestTemplate.exchange(
                "/api/stakeholder/" + postResponse.getBody().getId(), HttpMethod.PUT, putEntity, ErrorMessage.class);

        // then
        assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testUpdateStakeholder_UpdateNullDtoIntoNonExistingId_ReturnHttpStatusBadRequest() {
        // given
        StakeholderDto stakeholderDto = null;

        // when
        var putEntity = new HttpEntity<>(stakeholderDto);
        var putResponse = testRestTemplate.exchange(
                "/api/stakeholder/" + UUID.randomUUID().toString(), HttpMethod.PUT, putEntity, StakeholderDto.class);

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
        var stakeholderDto = stakeholderMapper.toDto(stakeholder);
        var insertEntity = new HttpEntity<>(stakeholderDto);
        var insertEntityResponse = testRestTemplate.postForEntity("/api/stakeholder", insertEntity, StakeholderDto.class);

        // when
        var insertedStakeholderDto = insertEntityResponse.getBody();
        var deleteEntity = new HttpEntity<>(insertedStakeholderDto);
        var deleteEntityResponse = testRestTemplate.exchange(
                "/api/stakeholder/" + insertedStakeholderDto.getId(), HttpMethod.DELETE, deleteEntity, Void.class);

        // then
        assertThat(deleteEntityResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        var getResponse = testRestTemplate.getForEntity("/api/stakeholders", StakeholderDto[].class);
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
                "/api/stakeholder/" + stakeholderDto.getId(), HttpMethod.DELETE, httpEntity, Void.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testDeleteStakeholder_DeleteNullId_ReturnHttpStatusBadRequest() {
        // given
        var stakeholder = getStakeholder();
        var stakeholderDto = stakeholderMapper.toDto(stakeholder);
        var httpEntity = new HttpEntity<>(stakeholderDto);

        // when
        var responseEntity = testRestTemplate.exchange(
                "/api/stakeholder/" + stakeholder.getId(), HttpMethod.DELETE, httpEntity, Void.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    //endregion

    // TODO: Try to CRUD (all 4 operations) with a wrong Dto, e.g. DimensionDto. What happens? Insert with Dto copy class with one field missing.

    // TODO: Send invalid/malignant requests and see what happens -> then make code resistant and write tests.

    // TODO: Create static error message provider and check errorMessage responses in tests.
    @Test
    public void testInspect() {
        // given
        var responseEntity = testRestTemplate.getForEntity("/api/stakeholder/wrong_id", ErrorMessage.class);

        // when
        var ex = responseEntity.getBody();
        System.out.println(ex.getTimestamp());
        System.out.println(ex.getMessage());
        System.out.println(ex.getDetails());
        System.out.println(ex.getPath());

        System.out.println(ex.getClass().getSimpleName());

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
