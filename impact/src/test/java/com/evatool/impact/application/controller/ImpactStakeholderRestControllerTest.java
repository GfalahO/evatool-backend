package com.evatool.impact.application.controller;

import com.evatool.impact.application.controller.util.StakeholderRest;
import com.evatool.impact.application.dto.StakeholderDto;
import com.evatool.impact.application.service.ImpactStakeholderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import static com.evatool.impact.common.TestDataGenerator.createDummyStakeholderDto;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ImpactStakeholderRestControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ImpactStakeholderService stakeholderService;

    @BeforeEach
    public void clearDatabase() {
        stakeholderService.deleteStakeholders();
    }

    @Nested
    public class GetAll {
        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5})
        public void testGetStakeholders_ExistingStakeholders_ReturnStakeholders(int value) {
            for (int i = 0; i < value; i++) {
                // given
                var stakeholderDto = createDummyStakeholderDto();
                stakeholderService.createStakeholder(stakeholderDto);
            }

            // when
            var getResponse = testRestTemplate.getForEntity(
                    StakeholderRest.buildGetStakeholdersUri(), StakeholderDto[].class);
            var stakeholderDtos = getResponse.getBody();

            // then
            assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(stakeholderDtos.length).isEqualTo(value);
        }
    }
}
