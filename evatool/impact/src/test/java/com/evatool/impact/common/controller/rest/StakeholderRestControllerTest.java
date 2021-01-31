package com.evatool.impact.common.controller.rest;

import com.evatool.impact.common.dto.StakeholderDto;
import com.evatool.impact.common.mapper.StakeholderMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.repository.query.Param;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static com.evatool.impact.persistence.TestDataGenerator.getEmptyStakeholderDto;
import static com.evatool.impact.persistence.TestDataGenerator.getStakeholder;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StakeholderRestControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    private StakeholderMapper stakeholderMapper = new StakeholderMapper();

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
        var responseEntity = testRestTemplate.getForEntity("/api/stakeholder/wrong_id", StakeholderDto.class);

        // when

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

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
        UUID.fromString(responseEntity.getBody().getId());
        assertThat(responseEntity.getBody().getName()).isEqualTo(stakeholder.getName());
    }

    @Test
    public void testInsertStakeholder_InsertDuplicateStakeholder_AllowDuplicateInsert() {
        // given
        var stakeholder = getStakeholder();
        var stakeholderDto = stakeholderMapper.toDto(stakeholder);

        // when
        var httpEntity = new HttpEntity<>(stakeholderDto);
        var responseEntity = testRestTemplate.postForEntity("/api/stakeholder", httpEntity, StakeholderDto.class);

        var stakeholderDtoDuplicate = responseEntity.getBody();
        var httpEntityDuplicate = new HttpEntity<>(stakeholderDto);
        var responseEntityDuplicate = testRestTemplate.postForEntity(
                "/api/stakeholder", stakeholderDtoDuplicate, StakeholderDto.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody().getId()).isEqualTo(responseEntityDuplicate.getBody().getId());
    }

    @Test
    public void testInsertStakeholder_InsertNull_ReturnHttpStatusUnsupportedMediaType() {
        // given
        StakeholderDto stakeholderDto = null;

        // when
        var httpEntity = new HttpEntity<>(stakeholderDto);
        var responseEntity = testRestTemplate.postForEntity("/api/stakeholder", httpEntity, StakeholderDto.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    // TODO: Ask if this kind of error handling is too overkill? Just make everything nullable? Introduce EntityPropertyViolationException? (InternalServerError should not be raised...)
    // Internal server error due to not null check in Stakeholder.setName method.
    @Test
    public void testInsertStakeholder_InsertStakeholderWithNullName_ReturnHttpStatusInternalServerError() {
        // given
        StakeholderDto stakeholderDto = getEmptyStakeholderDto();

        // when
        var httpEntity = new HttpEntity<>(stakeholderDto);
        var responseEntity = testRestTemplate.postForEntity("/api/stakeholder", httpEntity, StakeholderDto.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

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

    // TODO: Update non existing id.
    @Test
    public void testUpdateStakeholder_UpdateNonExistingId_A() {

    }

    // TODO: Test when updating id null
    @Test
    public void testUpdateStakeholder_UpdateNullId_A() {

    }

    // TODO: Test when updating name null
    @Test
    public void testUpdateStakeholder_UpdateNullName_A() {

    }

    // TODO: Test delete
    @Test
    public void testDeleteStakeholder_ExistingStakeholder_DeleteStakeholder() {

    }

    // TODO: Test delete when id does not exist
    @Test
    public void testDeleteStakeholder_DeleteNonExistingId_A() {

    }

    // TODO: Test delete null id
    @Test
    public void testDeleteStakeholder_DeleteNullId_A() {

    }
}
