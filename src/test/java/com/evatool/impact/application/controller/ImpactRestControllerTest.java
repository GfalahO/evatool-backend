package com.evatool.impact.application.controller;

import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.application.dto.ImpactDto;
import com.evatool.impact.application.dto.mapper.DimensionDtoMapper;
import com.evatool.impact.application.service.ImpactService;
import com.evatool.impact.domain.repository.DimensionRepository;
import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static com.evatool.impact.application.controller.UriUtil.DIMENSIONS;
import static com.evatool.impact.application.controller.UriUtil.IMPACTS;
import static com.evatool.impact.application.dto.mapper.ImpactDtoMapper.toDto;
import static com.evatool.impact.common.TestDataGenerator.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ImpactRestControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ImpactService impactService;

    @Autowired
    private DimensionRepository dimensionRepository;

    @Autowired
    private ImpactStakeholderRepository stakeholderRepository;

    @BeforeEach
    public void clearDatabase() {
        impactService.deleteImpacts();
    }

    private ImpactDto saveFullDummyImpact() {
        var impact = createDummyImpact();
        dimensionRepository.save(impact.getDimension());
        stakeholderRepository.save(impact.getStakeholder());
        return impactService.createImpact(toDto(impact));
    }

    @Nested
    class GetById {

        @Test
        void testGetImpactById_InsertedImpact_ReturnImpact() {
            // given
            var impactDto = saveFullDummyImpact();

            // when
            var response = testRestTemplate.getForEntity(
                    IMPACTS + "/" + impactDto.getId().toString(), ImpactDto.class);

            // then
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isEqualTo(impactDto);
        }

        @Test
        void testGetDimensionById_NonExistingDimension_ReturnHttpStatusNotFound() {
            // given
            var response = testRestTemplate.getForEntity(
                    IMPACTS + "/" + UUID.randomUUID().toString(), ImpactDto.class);

            // when

            // then
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }

    @Nested
    class GetAll {

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5})
        void testGetDimensions_ExistingDimensions_ReturnDimensions(int value) {
            for (int i = 0; i < value; i++) {
                // given
                saveFullDummyImpact();
            }

            // when
            var getResponse = testRestTemplate.getForEntity(
                    IMPACTS, ImpactDto[].class);
            var impactDtoList = getResponse.getBody();

            // then
            assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(impactDtoList).isNotNull().hasSize(value);
        }
    }

    @Nested
    class Insert {


        @Nested
        class WhenChildEntityDeleted {

        }
    }

    @Nested
    class Update {


        @Nested
        class WhenChildEntityDeleted {

        }
    }

    @Nested
    class Delete {


        @Nested
        class WhenChildEntityDeleted {

        }
    }
}
