package com.evatool.impact.common.controller;

import com.evatool.impact.common.dto.StakeholderDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import java.util.UUID;

import static com.evatool.impact.persistence.TestDataGenerator.getStakeholder;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StakeholderRestControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

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
    public void testGetStakeholderById_NoExistingStakeholder_ReturnHttpStatusNotFound() {
        // given
        var responseEntity = testRestTemplate.getForEntity("/api/stakeholder/wrong_id", StakeholderDto.class);

        // when

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        // TODO: How to get exception back so this can be tested? Why does the exception not make the test fail?
        //assertThatExceptionOfType(StakeholderNotFoundException.class).isThrownBy(() -> testRestTemplate.getForEntity("/api/stakeholder/wrong_id", StakeholderDto.class));
    }

    @Test
    void testInsertStakeholder_InsertStakeholder_ReturnInsertedStakeholder() {
        // given
        var stakeholder = getStakeholder();
        var stakeholderDto = stakeholder.toDto();

        // when
        var httpEntity = new HttpEntity<StakeholderDto>(stakeholderDto);
        var responseEntity = testRestTemplate.postForEntity("/api/stakeholder", httpEntity, StakeholderDto.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody().getId()).isNotNull();
        UUID.fromString(responseEntity.getBody().getId());
        //assertThat(responseEntity.getBody().getId()).isEqualTo(stakeholder.getId()); // TODO: Id should be set or is this simulating a frontend call?
        assertThat(responseEntity.getBody().getName()).isEqualTo(stakeholder.getName());
    }

    // TODO: Test normal cases like in ServiceTest

    // TODO: Test when inserting null. Not possible? (with deliberately malignant Dto?)
    // TODO: Test when updating null
    // TODO: Test delete when id does not exist

    // TODO: Parameterized test with get all stakeholders
}
