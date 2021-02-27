package com.evatool.impact.application.controller;

import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.application.service.DimensionService;
import com.evatool.impact.domain.entity.Dimension;
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
import static com.evatool.impact.application.controller.UriUtil.DIMENSION_TYPES;
import static com.evatool.impact.application.dto.mapper.DimensionDtoMapper.toDto;
import static com.evatool.impact.common.TestDataGenerator.createDummyDimension;
import static com.evatool.impact.common.TestDataGenerator.createDummyDimensionDto;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class DimensionRestControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private DimensionService dimensionService;

    @BeforeEach
    public void clearDatabase() {
        dimensionService.deleteAll();
    }

    private DimensionDto saveFullDummyDimensionDto() {
        var dimension = createDummyDimension();
        var dimensionDto = toDto(dimension);
        return dimensionService.insert(dimensionDto);

    }

    @Nested
    class GetById {

        @Test
        void testGetDimensionById_InsertedDimension_ReturnDimension() {
            // given
            var dimensionDto = saveFullDummyDimensionDto();

            // when
            var response = testRestTemplate.getForEntity(
                    DIMENSIONS + "/" + dimensionDto.getId().toString(), DimensionDto.class);

            // then
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isEqualTo(dimensionDto);
        }

        @Test
        void testGetDimensionById_NonExistingDimension_ReturnHttpStatusNotFound() {
            // given
            var responseEntity = testRestTemplate.getForEntity(
                    DIMENSIONS + "/" + UUID.randomUUID().toString(), DimensionDto.class);

            // when

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }

    @Nested
    class GetAll {

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5})
        void testGetDimensions_ExistingDimensions_ReturnDimensions(int value) {
            for (int i = 0; i < value; i++) {
                // given
                saveFullDummyDimensionDto();
            }

            // when
            var getResponse = testRestTemplate.getForEntity(
                    DIMENSIONS, DimensionDto[].class);
            var dimensionDtoList = getResponse.getBody();

            // then
            assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(dimensionDtoList).isNotNull().hasSize(value);
        }
    }

    @Nested
    class GetByType {

        @Test
        void testGetByType_ExistingDimensions_ReturnDimensions() {
            // given
            int n_socialDimensions = 3;
            for (int i = 0; i < n_socialDimensions; i++) {
                var socialDimension = createDummyDimensionDto();
                socialDimension.setType(Dimension.Type.SOCIAL);
                dimensionService.insert(socialDimension);
            }

            int n_economicDimensions = 4;
            for (int i = 0; i < n_economicDimensions; i++) {
                var economicDimension = createDummyDimensionDto();
                economicDimension.setType(Dimension.Type.ECONOMIC);
                dimensionService.insert(economicDimension);
            }

            // when
            var getSocialResponse = testRestTemplate.getForEntity(
                    DIMENSIONS + "?type=" + Dimension.Type.SOCIAL.toString(), DimensionDto[].class);
            var socialDimensions = getSocialResponse.getBody();

            var getEconomicResponse = testRestTemplate.getForEntity(
                    DIMENSIONS + "?type=" + Dimension.Type.ECONOMIC.toString(), DimensionDto[].class);
            var economicDimensions = getEconomicResponse.getBody();

            // then
            assertThat(socialDimensions).hasSize(n_socialDimensions);
            assertThat(economicDimensions).hasSize(n_economicDimensions);
        }
    }

    @Nested
    class GetDimensionTypes {

        @Test
        void testGetDimensionTypes_ReturnDimensionTypes() {
            // given

            // when
            var dimensionTypes = testRestTemplate.getForEntity(
                    DIMENSION_TYPES, Dimension.Type[].class);

            // then
            assertThat(dimensionTypes.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(dimensionTypes.getBody()).isEqualTo(Dimension.Type.values());
        }
    }

    @Nested
    class Insert {

        @Test
        void testInsertDimension_InsertDimension_ReturnInsertedDimension() {
            // given
            var dimensionDto = createDummyDimensionDto();

            // when
            var httpEntity = new HttpEntity<>(dimensionDto);
            var responseEntity = testRestTemplate.postForEntity(
                    DIMENSIONS, httpEntity, DimensionDto.class);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        }

        @Test
        void testInsertDimension_InsertNotNullId_ReturnHttpStatusUnprocessableEntity() {
            // given
            var dimensionDto = createDummyDimensionDto();
            dimensionDto.setId(UUID.randomUUID());

            // when
            var httpEntity = new HttpEntity<>(dimensionDto);
            var responseEntity = testRestTemplate.postForEntity(
                    DIMENSIONS, httpEntity, DimensionDto.class);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Nested
    class Update {

        @Test
        void testUpdateDimension_InsertedDimension_ReturnUpdatedDimension() {
            // given
            var dimensionDto = saveFullDummyDimensionDto();

            // when
            dimensionDto.setName("new_name");
            var putEntity = new HttpEntity<>(dimensionDto);
            var response = testRestTemplate.exchange(DIMENSIONS, HttpMethod.PUT, putEntity, DimensionDto.class);

            // then
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(dimensionService.findById(dimensionDto.getId())).isEqualTo(dimensionDto);
        }

        @Test
        void testUpdateDimension_UpdateNonExistingId_ReturnHttpStatusNotFound() {
            // given
            var dimension = createDummyDimension();
            dimension.setId(UUID.randomUUID());
            var dimensionDto = toDto(dimension);
            var httpEntity = new HttpEntity<>(dimensionDto);

            // when
            var putResponse = testRestTemplate.exchange(
                    DIMENSIONS, HttpMethod.PUT, httpEntity, DimensionDto.class);

            // then
            assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }

        @Test
        void testUpdateDimension_UpdateNullId_ReturnHttpStatusUnprocessableEntity() {
            // given
            var dimensionDto = createDummyDimensionDto();

            // when
            var httpEntity = new HttpEntity<>(dimensionDto);
            var putResponse = testRestTemplate.exchange(
                    DIMENSIONS, HttpMethod.PUT, httpEntity, DimensionDto.class);

            // then
            assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Nested
    class Delete {

        @Test
        void testDeleteDimension_ExistingDimension_DeleteDimensionAndReturnHttpStatusOK() {
            // given
            var dimensionDto = saveFullDummyDimensionDto();

            // when
            var response = testRestTemplate.exchange(
                    DIMENSIONS + "/" + dimensionDto.getId(), HttpMethod.DELETE, null, Void.class);
            var dimensions = dimensionService.findAll();

            // then
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(dimensions).isEmpty();
        }

        @Test
        void testDeleteDimension_DeleteNonExistingId_ReturnHttpStatusNotFound() {
            // given
            var dimensionDto = createDummyDimensionDto();
            dimensionDto.setId(UUID.randomUUID());

            // when
            var response = testRestTemplate.exchange(
                    DIMENSIONS + "/" + dimensionDto.getId(), HttpMethod.DELETE, null, Void.class);

            //then
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }
}
