package com.evatool.impact.application.controller;

import com.evatool.impact.application.controller.util.DimensionRest;
import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.application.service.DimensionService;
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

import static com.evatool.impact.application.dto.mapper.DimensionDtoMapper.fromDto;
import static com.evatool.impact.application.dto.mapper.DimensionDtoMapper.toDto;
import static com.evatool.impact.common.TestDataGenerator.createDummyDimension;
import static com.evatool.impact.common.TestDataGenerator.createDummyDimensionDto;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DimensionRestControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private DimensionService dimensionService;

    @BeforeEach
    public void clearDatabase() {
        dimensionService.deleteDimensions();
    }

    @Nested
    public class GetById {
        @Test
        public void testGetDimensionById_InsertedDimension_ReturnDimension() {
            // given
            var dimension = createDummyDimension();
            var dimensionDto = toDto(dimension);
            var httpEntity = new HttpEntity<>(dimensionDto);
            var postResponse = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // when
            assertThat(postResponse.getBody()).isNotNull();
            var getResponse = testRestTemplate.getForEntity(
                    DimensionRest.buildGetDimensionUri(postResponse.getBody().getId()), DimensionDto.class);

            // then
            assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(getResponse.getBody()).isNotNull();
            assertThat(getResponse.getBody().getId()).isNotNull();
            assertThat(getResponse.getBody().getId()).isEqualTo(postResponse.getBody().getId());
            assertThat(getResponse.getBody().getName()).isEqualTo(postResponse.getBody().getName());
        }

        @Test
        public void testGetDimensionById_NonExistingDimension_ReturnHttpStatusNotFound() {
            // given
            var responseEntity = testRestTemplate.getForEntity(
                    DimensionRest.buildGetDimensionUri("wrong_id"), DimensionDto.class);

            // when

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }

    @Nested
    public class GetAll {
        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5})
        public void testGetDimensions_ExistingDimensions_ReturnDimensions(int value) {
            for (int i = 0; i < value; i++) {
                // given
                var dimensionDto = createDummyDimensionDto();
                dimensionService.createDimension(dimensionDto);
            }

            // when
            var getResponse = testRestTemplate.getForEntity(
                    DimensionRest.buildGetDimensionsUri(), DimensionDto[].class);
            var dimensionDtos = getResponse.getBody();

            // then
            assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(dimensionDtos).isNotNull();
            assertThat(dimensionDtos.length).isEqualTo(value);
        }
    }

    @Nested
    public class Insert {
        @Test
        public void testInsertDimension_InsertDimension_ReturnInsertedDimension() {
            // given
            var dimension = createDummyDimension();
            var dimensionDto = toDto(dimension);

            // when
            var httpEntity = new HttpEntity<>(dimensionDto);
            var responseEntity = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
            assertThat(responseEntity.getBody()).isNotNull();
            assertThat(responseEntity.getBody().getId()).isNotNull();
            assertThat(responseEntity.getBody().getName()).isEqualTo(dimension.getName());
        }

        // Note: RestController code is not being executed. The error is automatically thrown.
        @Test
        public void testInsertDimension_InsertNullDto_ReturnHttpStatusUnsupportedMediaType() {
            // given

            // when
            var httpEntity = new HttpEntity<>(null);
            var responseEntity = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }

        @Test
        public void testInsertDimension_InsertEmptyDimensionDto_ReturnHttpStatusBadRequest() {
            // given
            var httpEntity = new HttpEntity<>(new DimensionDto());

            // when
            var responseEntity = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        public void testInsertDimension_InsertDimensionWithNullName_ReturnHttpStatusBadRequest() {
            // given
            DimensionDto dimensionDto = createDummyDimensionDto();
            dimensionDto.setName(null);

            // when
            var httpEntity = new HttpEntity<>(dimensionDto);
            var responseEntity = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        public void testInsertDimension_InsertDimensionWithNullDescription_ReturnHttpStatusBadRequest() {
            // given
            DimensionDto dimensionDto = createDummyDimensionDto();
            dimensionDto.setDescription(null);

            // when
            var httpEntity = new HttpEntity<>(dimensionDto);
            var responseEntity = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        public void testInsertDimension_InsertDimensionWithNullType_ReturnHttpStatusBadRequest() {
            // given
            DimensionDto dimensionDto = createDummyDimensionDto();
            dimensionDto.setType(null);

            // when
            var httpEntity = new HttpEntity<>(dimensionDto);
            var responseEntity = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "typo"})
        public void testInsertDimension_InsertDimensionWithIllegalType_ReturnHttpStatusBadRequest(String value) {
            // given
            DimensionDto dimensionDto = createDummyDimensionDto();
            dimensionDto.setType(value);

            // when
            var httpEntity = new HttpEntity<>(dimensionDto);
            var responseEntity = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        public void testInsertDimension_InsertWithNotNullId_ReturnHttpStatusBadRequest() {
            // given
            var dimensionDto = createDummyDimensionDto();
            dimensionDto.setId(UUID.randomUUID().toString());

            // when
            var httpEntity = new HttpEntity<>(dimensionDto);
            var responseEntity = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            assertThat(responseEntity.getBody()).isNotNull();
            httpEntity = new HttpEntity<>(responseEntity.getBody());
            responseEntity = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }
    }

    @Nested
    public class Update {
        @Test
        public void testUpdateDimension_InsertedDimension_ReturnUpdatedDimension() {
            // given
            var dimension = createDummyDimension();
            var dimensionDto = toDto(dimension);
            var httpEntity = new HttpEntity<>(dimensionDto);
            var postResponse = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // when
            assertThat(postResponse.getBody()).isNotNull();
            var updatedDimension = fromDto(postResponse.getBody());
            updatedDimension.setName("new_name");
            var putEntity = new HttpEntity<>(toDto(updatedDimension));
            var putResponse = testRestTemplate.exchange(
                    DimensionRest.buildPutDimensionUri(postResponse.getBody().getId()), HttpMethod.PUT, putEntity, DimensionDto.class);
            var getResponse = testRestTemplate.getForEntity(
                    DimensionRest.buildGetDimensionUri(postResponse.getBody().getId()), DimensionDto.class);

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
        public void testUpdateDimension_UpdateNonExistingId_ReturnHttpStatusNotFound() {
            // given
            var dimension = createDummyDimension();
            dimension.setId(UUID.randomUUID().toString());
            var dimensionDto = toDto(dimension);
            var httpEntity = new HttpEntity<>(dimensionDto);

            // when
            var putResponse = testRestTemplate.exchange(
                    DimensionRest.buildPutDimensionUri(dimension.getId()), HttpMethod.PUT, httpEntity, DimensionDto.class);

            // then
            assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }

        @Test
        public void testUpdateDimension_UpdateNullId_ReturnHttpStatusNotFound() {
            // given
            var dimension = createDummyDimension();
            var dimensionDto = toDto(dimension);
            var httpEntity = new HttpEntity<>(dimensionDto);

            // when
            var putResponse = testRestTemplate.exchange(
                    DimensionRest.buildPutDimensionUri(UUID.randomUUID().toString()), HttpMethod.PUT, httpEntity, DimensionDto.class);

            // then
            assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }

        @Test
        public void testUpdateDimension_UpdateNullName_ReturnHttpStatusBadRequest() {
            // given
            var dimension = createDummyDimension();
            var dimensionDto = toDto(dimension);
            var httpEntity = new HttpEntity<>(dimensionDto);
            var postResponse = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // when
            assertThat(postResponse.getBody()).isNotNull();
            var updatedDimension = fromDto(postResponse.getBody());
            var updatedDimensionDto = toDto(updatedDimension);
            updatedDimensionDto.setName(null);
            var putEntity = new HttpEntity<>(updatedDimensionDto);
            var putResponse = testRestTemplate.exchange(
                    DimensionRest.buildPutDimensionUri(postResponse.getBody().getId()), HttpMethod.PUT, putEntity, DimensionDto.class);

            // then
            assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        public void testUpdateDimension_UpdateNullDescription_ReturnHttpStatusBadRequest() {
            // given
            var dimension = createDummyDimension();
            var dimensionDto = toDto(dimension);
            var httpEntity = new HttpEntity<>(dimensionDto);
            var postResponse = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // when
            assertThat(postResponse.getBody()).isNotNull();
            var updatedDimension = fromDto(postResponse.getBody());
            var updatedDimensionDto = toDto(updatedDimension);
            updatedDimensionDto.setDescription(null);
            var putEntity = new HttpEntity<>(updatedDimensionDto);
            var putResponse = testRestTemplate.exchange(
                    DimensionRest.buildPutDimensionUri(postResponse.getBody().getId()), HttpMethod.PUT, putEntity, DimensionDto.class);

            // then
            assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        public void testUpdateDimension_UpdateNullType_ReturnHttpStatusBadRequest() {
            // given
            var dimension = createDummyDimension();
            var dimensionDto = toDto(dimension);
            var httpEntity = new HttpEntity<>(dimensionDto);
            var postResponse = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // when
            assertThat(postResponse.getBody()).isNotNull();
            var updatedDimension = fromDto(postResponse.getBody());
            var updatedDimensionDto = toDto(updatedDimension);
            updatedDimensionDto.setType(null);
            var putEntity = new HttpEntity<>(updatedDimensionDto);
            var putResponse = testRestTemplate.exchange(
                    DimensionRest.buildPutDimensionUri(postResponse.getBody().getId()), HttpMethod.PUT, putEntity, DimensionDto.class);

            // then
            assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "typo"})
        public void testUpdateDimension_UpdateIllegalType_ReturnHttpStatusBadRequest(String value) {
            // given
            var dimension = createDummyDimension();
            var dimensionDto = toDto(dimension);
            var httpEntity = new HttpEntity<>(dimensionDto);
            var postResponse = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // when
            assertThat(postResponse.getBody()).isNotNull();
            var updatedDimension = fromDto(postResponse.getBody());
            var updatedDimensionDto = toDto(updatedDimension);
            updatedDimensionDto.setType(value);
            var putEntity = new HttpEntity<>(updatedDimensionDto);
            var putResponse = testRestTemplate.exchange(
                    DimensionRest.buildPutDimensionUri(postResponse.getBody().getId()), HttpMethod.PUT, putEntity, DimensionDto.class);

            // then
            assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        // Note: RestController code is not being executed. The error is automatically thrown.
        @Test
        public void testUpdateDimension_UpdateNullDtoIntoExistingId_ReturnHttpStatusBadRequest() {
            // given
            var dimension = createDummyDimension();
            var dimensionDto = toDto(dimension);
            var httpEntity = new HttpEntity<>(dimensionDto);
            var postResponse = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // when
            assertThat(postResponse.getBody()).isNotNull();
            var putEntity = new HttpEntity<>(null);
            var putResponse = testRestTemplate.exchange(
                    DimensionRest.buildPutDimensionUri(postResponse.getBody().getId()), HttpMethod.PUT, putEntity, DimensionDto.class);

            // then
            assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        // Note: RestController code is not being executed. The error is automatically thrown.
        @Test
        public void testUpdateDimension_UpdateNullDtoIntoNonExistingId_ReturnHttpStatusBadRequest() {
            // given

            // when
            var putEntity = new HttpEntity<>(null);
            var putResponse = testRestTemplate.exchange(
                    DimensionRest.buildPutDimensionUri(UUID.randomUUID().toString()), HttpMethod.PUT, putEntity, DimensionDto.class);

            // then
            assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }
    }

    @Nested
    public class Delete {
        @Test
        public void testDeleteDimension_ExistingDimension_DeleteDimensionAndReturnHttpStatusOK() {
            // given
            var dimension = createDummyDimension();
            var dimensionDto = toDto(dimension);
            var insertEntity = new HttpEntity<>(dimensionDto);
            var insertEntityResponse = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), insertEntity, DimensionDto.class);

            // when
            var insertedDimensionDto = insertEntityResponse.getBody();
            assertThat(insertedDimensionDto).isNotNull();
            var deleteEntity = new HttpEntity<>(insertedDimensionDto);
            var deleteEntityResponse = testRestTemplate.exchange(
                    DimensionRest.buildDeleteDimensionUri(insertedDimensionDto.getId()), HttpMethod.DELETE, deleteEntity, Void.class);

            // then
            assertThat(deleteEntityResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

            var getResponse = testRestTemplate.getForEntity(
                    DimensionRest.buildGetDimensionsUri(), DimensionDto[].class);
            assertThat(getResponse.getBody()).isNotNull();
            var dimensions = getResponse.getBody();
            assertThat(dimensions.length).isEqualTo(0);
        }

        @Test
        public void testDeleteDimension_DeleteNonExistingId_ReturnHttpStatusNotFound() {
            // given
            var dimensionDto = createDummyDimensionDto();
            dimensionDto.setId(UUID.randomUUID().toString());

            // when
            var httpEntity = new HttpEntity<>(dimensionDto);
            var responseEntity = testRestTemplate.exchange(
                    DimensionRest.buildDeleteDimensionUri(dimensionDto.getId()), HttpMethod.DELETE, httpEntity, Void.class);

            //then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }

        @Test
        public void testDeleteDimension_DeleteNullId_ReturnHttpStatusBadRequest() {
            // given
            var dimension = createDummyDimension();
            var dimensionDto = toDto(dimension);
            var httpEntity = new HttpEntity<>(dimensionDto);

            // when
            var responseEntity = testRestTemplate.exchange(
                    DimensionRest.buildDeleteDimensionUri("null"), HttpMethod.DELETE, httpEntity, Void.class);

            //then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }
}
