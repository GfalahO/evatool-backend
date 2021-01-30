package com.evatool.impact.common.controller;

import com.evatool.impact.common.dto.StakeholderDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

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

    // TODO: Test normal case like in **ServiceTest tests

    // TODO: Test when inserting null. Not possible? (with deliberately malignant Dto?)
    // TODO: Test when updating null
    // TODO: Test delete when id does not exist

    // TODO: Parameterized test with get all stakeholders
}
