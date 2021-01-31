package com.evatool.impact.common.controller.rest;

import com.evatool.impact.common.dto.StakeholderDto;
import com.evatool.impact.common.mapper.StakeholderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

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
        var postEntity = testRestTemplate.postForEntity("/api/stakeholder", httpEntity, StakeholderDto.class);

        // when
        var getEntity = testRestTemplate.getForEntity(
                "/api/stakeholder/" + postEntity.getBody().getId(), StakeholderDto.class);

        // then
        assertThat(getEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getEntity.getBody().getId()).isNotNull();
        assertThat(getEntity.getBody().getId()).isEqualTo(postEntity.getBody().getId());
        assertThat(getEntity.getBody().getName()).isEqualTo(postEntity.getBody().getName());
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

    // TODO: Ask if this kind of error handling is too overkill? Just make everything nullable?
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

    // TODO: Test update and read
    // TODO: Test when updating id null
    // TODO: Test when updating name null

    // TODO: Test delete
    // TODO: Test delete when id does not exist

    // TODO: Test getStakeholders
    // TODO: Parameterized test with get all stakeholders
}
