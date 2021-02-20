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

import java.util.Arrays;
import java.util.UUID;

import static com.evatool.impact.application.controller.UriUtil.DIMENSIONS;
import static com.evatool.impact.application.controller.UriUtil.DIMENSION_TYPES;
import static com.evatool.impact.application.dto.mapper.DimensionDtoMapper.fromDto;
import static com.evatool.impact.application.dto.mapper.DimensionDtoMapper.toDto;
import static com.evatool.impact.common.TestDataGenerator.createDummyDimension;
import static com.evatool.impact.common.TestDataGenerator.createDummyDimensionDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class DimensionRestControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private DimensionService dimensionService;

    @BeforeEach
    public void clearDatabase() {
        dimensionService.deleteDimensions();
    }

    @Nested
    class GetById {

        @Test
        void testGetDimensionById_InsertedDimension_ReturnDimension() {
            // given
            var dimension = createDummyDimension();
            var dimensionDto = toDto(dimension);
            var httpEntity = new HttpEntity<>(dimensionDto);
            var postResponse = testRestTemplate.postForEntity(
                    DIMENSIONS, httpEntity, DimensionDto.class);

            // when
            assertThat(postResponse.getBody()).isNotNull();
            var getResponse = testRestTemplate.getForEntity(
                    DIMENSIONS + "/" + postResponse.getBody().getId(), DimensionDto.class);

            // then
            assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(getResponse.getBody()).isNotNull();
            assertThat(getResponse.getBody().getId()).isNotNull();
            assertThat(getResponse.getBody().getId()).isEqualTo(postResponse.getBody().getId());
            assertThat(getResponse.getBody().getName()).isEqualTo(postResponse.getBody().getName());
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

        @Test
        void testGetDimensions_ExistingDimensions_ReturnDimensions() {
            // given
            var dimensionDto = createDummyDimensionDto();
            dimensionService.createDimension(dimensionDto);

            // when
            var getResponse = testRestTemplate.getForEntity(
                    DIMENSIONS, DimensionDto[].class);
            var dimensionDtoList = getResponse.getBody();

            // then
            assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(dimensionDtoList).isNotNull().hasSize(1);
            assertThat(dimensionDtoList[0].getId()).isNotNull();
            assertThat(dimensionDtoList[0].getName()).isEqualTo(dimensionDto.getName());
            assertThat(dimensionDtoList[0].getDescription()).isEqualTo(dimensionDto.getDescription());
            assertThat(dimensionDtoList[0].getType()).isEqualTo(dimensionDto.getType());
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5})
        void testGetDimensions_ExistingDimensions_ReturnDimensions(int value) {
            for (int i = 0; i < value; i++) {
                // given
                var dimensionDto = createDummyDimensionDto();
                dimensionService.createDimension(dimensionDto);
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
        void testGetByType_ExistingDimensions_ReturnDimensions() throws Exception {
            // given
            int n_socialDimensions = 3;
            for (int i = 0; i < n_socialDimensions; i++) {
                var socialDimension = createDummyDimensionDto();
                socialDimension.setType(Dimension.Type.SOCIAL);
                dimensionService.createDimension(socialDimension);
            }

            int n_economicDimensions = 4;
            for (int i = 0; i < n_economicDimensions; i++) {
                var economicDimension = createDummyDimensionDto();
                economicDimension.setType(Dimension.Type.ECONOMIC);
                dimensionService.createDimension(economicDimension);
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
            var dimension = createDummyDimension();
            var dimensionDto = toDto(dimension);

            // when
            var httpEntity = new HttpEntity<>(dimensionDto);
            var responseEntity = testRestTemplate.postForEntity(
                    DIMENSIONS, httpEntity, DimensionDto.class);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
            assertThat(responseEntity.getBody()).isNotNull();
            assertThat(responseEntity.getBody().getId()).isNotNull();
            assertThat(responseEntity.getBody().getName()).isEqualTo(dimension.getName());
        }

        @Test
        void testInsertDimension_InsertEmptyDimensionDto_ReturnHttpStatusBadRequest() {
            // given
            var httpEntity = new HttpEntity<>(new DimensionDto());

            // when
            var responseEntity = testRestTemplate.postForEntity(
                    DIMENSIONS, httpEntity, DimensionDto.class);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        void testInsertDimension_InsertDimensionWithNullName_ReturnHttpStatusBadRequest() {
            // given
            DimensionDto dimensionDto = createDummyDimensionDto();
            dimensionDto.setName(null);

            // when
            var httpEntity = new HttpEntity<>(dimensionDto);
            var responseEntity = testRestTemplate.postForEntity(
                    DIMENSIONS, httpEntity, DimensionDto.class);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        void testInsertDimension_InsertDimensionWithNullDescription_ReturnHttpStatusBadRequest() {
            // given
            DimensionDto dimensionDto = createDummyDimensionDto();
            dimensionDto.setDescription(null);

            // when
            var httpEntity = new HttpEntity<>(dimensionDto);
            var responseEntity = testRestTemplate.postForEntity(
                    DIMENSIONS, httpEntity, DimensionDto.class);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        void testInsertDimension_InsertDimensionWithNullType_ReturnHttpStatusBadRequest() {
            // given
            DimensionDto dimensionDto = createDummyDimensionDto();
            dimensionDto.setType(null);

            // when
            var httpEntity = new HttpEntity<>(dimensionDto);
            var responseEntity = testRestTemplate.postForEntity(
                    DIMENSIONS, httpEntity, DimensionDto.class);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        void testInsertDimension_InsertNotNullId_ReturnHttpStatusUnprocessableEntity() {
            // given
            DimensionDto dimensionDto = createDummyDimensionDto();
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
            var dimension = createDummyDimension();
            var dimensionDto = toDto(dimension);
            var httpEntity = new HttpEntity<>(dimensionDto);
            var postResponse = testRestTemplate.postForEntity(
                    DIMENSIONS, httpEntity, DimensionDto.class);

            // when
            assertThat(postResponse.getBody()).isNotNull();
            var updatedDimension = fromDto(postResponse.getBody());
            updatedDimension.setName("new_name");
            var putEntity = new HttpEntity<>(toDto(updatedDimension));
            var putResponse = testRestTemplate.exchange(
                    DIMENSIONS, HttpMethod.PUT, putEntity, DimensionDto.class);
            var getResponse = testRestTemplate.getForEntity(
                    DIMENSIONS + "/" + postResponse.getBody().getId(), DimensionDto.class);

            // then
            assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(putResponse.getBody()).isNotNull();
            assertThat(putResponse.getBody().getId()).isEqualTo(updatedDimension.getId());
            assertThat(putResponse.getBody().getName()).isEqualTo(updatedDimension.getName());

            assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(getResponse.getBody()).isNotNull();
            assertThat(getResponse.getBody().getId()).isNotNull();
            assertThat(getResponse.getBody().getId()).isEqualTo(updatedDimension.getId());
            assertThat(getResponse.getBody().getName()).isEqualTo(updatedDimension.getName());
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
        void testUpdateDimension_UpdateNullName_ReturnHttpStatusBadRequest() {
            // given
            var dimension = createDummyDimension();
            var dimensionDto = toDto(dimension);
            var httpEntity = new HttpEntity<>(dimensionDto);
            var postResponse = testRestTemplate.postForEntity(
                    DIMENSIONS, httpEntity, DimensionDto.class);

            // when
            assertThat(postResponse.getBody()).isNotNull();
            var updatedDimension = fromDto(postResponse.getBody());
            var updatedDimensionDto = toDto(updatedDimension);
            updatedDimensionDto.setName(null);
            var putEntity = new HttpEntity<>(updatedDimensionDto);
            var putResponse = testRestTemplate.exchange(
                    DIMENSIONS, HttpMethod.PUT, putEntity, DimensionDto.class);

            // then
            assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        void testUpdateDimension_UpdateNullDescription_ReturnHttpStatusBadRequest() {
            // given
            var dimension = createDummyDimension();
            var dimensionDto = toDto(dimension);
            var httpEntity = new HttpEntity<>(dimensionDto);
            var postResponse = testRestTemplate.postForEntity(
                    DIMENSIONS, httpEntity, DimensionDto.class);

            // when
            assertThat(postResponse.getBody()).isNotNull();
            var updatedDimension = fromDto(postResponse.getBody());
            var updatedDimensionDto = toDto(updatedDimension);
            updatedDimensionDto.setDescription(null);
            var putEntity = new HttpEntity<>(updatedDimensionDto);
            var putResponse = testRestTemplate.exchange(
                    DIMENSIONS, HttpMethod.PUT, putEntity, DimensionDto.class);

            // then
            assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        void testUpdateDimension_UpdateNullType_ReturnHttpStatusBadRequest() {
            // given
            var dimension = createDummyDimension();
            var dimensionDto = toDto(dimension);
            var httpEntity = new HttpEntity<>(dimensionDto);
            var postResponse = testRestTemplate.postForEntity(
                    DIMENSIONS, httpEntity, DimensionDto.class);

            // when
            assertThat(postResponse.getBody()).isNotNull();
            var updatedDimension = fromDto(postResponse.getBody());
            var updatedDimensionDto = toDto(updatedDimension);
            updatedDimensionDto.setType(null);
            var putEntity = new HttpEntity<>(updatedDimensionDto);
            var putResponse = testRestTemplate.exchange(
                    DIMENSIONS, HttpMethod.PUT, putEntity, DimensionDto.class);

            // then
            assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        void testUpdateDimension_UpdateNullId_ReturnHttpStatusUnprocessableEntity() {
            // given
            var dimension = createDummyDimension();
            var dimensionDto = toDto(dimension);
            var httpEntity = new HttpEntity<>(dimensionDto);

            // when
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
            var dimension = createDummyDimension();
            var dimensionDto = toDto(dimension);
            var insertEntity = new HttpEntity<>(dimensionDto);
            var insertEntityResponse = testRestTemplate.postForEntity(
                    DIMENSIONS, insertEntity, DimensionDto.class);

            // when
            var insertedDimensionDto = insertEntityResponse.getBody();
            assertThat(insertedDimensionDto).isNotNull();
            var deleteEntity = new HttpEntity<>(insertedDimensionDto);
            var deleteEntityResponse = testRestTemplate.exchange(
                    DIMENSIONS + "/" + insertedDimensionDto.getId(), HttpMethod.DELETE, deleteEntity, Void.class);

            // then
            assertThat(deleteEntityResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

            var getResponse = testRestTemplate.getForEntity(
                    DIMENSIONS, DimensionDto[].class);
            assertThat(getResponse.getBody()).isNotNull();
            var dimensions = getResponse.getBody();
            assertThat(dimensions).isEmpty();
        }

        @Test
        void testDeleteDimension_DeleteNonExistingId_ReturnHttpStatusNotFound() {
            // given
            var dimensionDto = createDummyDimensionDto();
            dimensionDto.setId(UUID.randomUUID());

            // when
            var httpEntity = new HttpEntity<>(dimensionDto);
            var responseEntity = testRestTemplate.exchange(
                    DIMENSIONS + "/" + dimensionDto.getId(), HttpMethod.DELETE, httpEntity, Void.class);

            //then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }
}
