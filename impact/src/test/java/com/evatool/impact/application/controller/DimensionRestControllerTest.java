package com.evatool.impact.application.controller;

import com.evatool.impact.application.controller.util.DimensionRest;
import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.application.service.DimensionService;
import com.evatool.impact.domain.entity.DimensionType;
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
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.UUID;

import static com.evatool.impact.application.dto.mapper.DimensionMapper.fromDto;
import static com.evatool.impact.application.dto.mapper.DimensionMapper.toDto;
import static com.evatool.impact.common.TestDataGenerator.*;
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
            var dimension = getDimension();
            var dimensionDto = toDto(dimension);
            var httpEntity = new HttpEntity<>(dimensionDto);
            var postResponse = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // when
            var getResponse = testRestTemplate.getForEntity(
                    DimensionRest.buildGetDimensionUri(postResponse.getBody().getId()), DimensionDto.class);

            // then
            assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(getResponse.getBody().getId()).isNotNull();
            assertThat(getResponse.getBody().getId()).isEqualTo(postResponse.getBody().getId());
            assertThat(getResponse.getBody().getName()).isEqualTo(postResponse.getBody().getName());
        }

        @Test
        public void testGetDimensionById_NoExistingDimension_ReturnHttpStatusNotFound() {
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
        @Transactional
        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5})
        public void testGetDimensions_ExistingDimensions_ReturnDimensions(int value) {
            var postResponseList = new ArrayList<ResponseEntity<DimensionDto>>();
            for (int i = 0; i < value; i++) {
                // given
                var dimensionDto = getDimensionDto();
                dimensionService.createDimension(dimensionDto);
            }

            // when
            var getResponse = testRestTemplate.getForEntity(
                    DimensionRest.buildGetDimensionsUri(), DimensionDto[].class);
            var dimensionDtos = getResponse.getBody();

            // then
            assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(dimensionDtos.length).isEqualTo(postResponseList.size());
        }
    }

    @Nested
    public class Insert {
        @Test
        public void testInsertDimension_InsertDimension_ReturnInsertedDimension() {
            // given
            var dimension = getDimension();
            var dimensionDto = toDto(dimension);

            // when
            var httpEntity = new HttpEntity<>(dimensionDto);
            var responseEntity = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
            assertThat(responseEntity.getBody().getId()).isNotNull();
            assertThat(responseEntity.getBody().getName()).isEqualTo(dimension.getName());
        }

        // Note: RestController code is not being executed. The error is automatically thrown.
        @Test
        public void testInsertDimension_InsertNullDto_ReturnHttpStatusUnsupportedMediaType() {
            // given
            DimensionDto dimensionDto = null;

            // when
            var httpEntity = new HttpEntity<>(dimensionDto);
            var responseEntity = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }

        @Test
        public void testInsertDimension_InsertEmptyDimensionDto_ReturnHttpStatusBadRequest() {
            // given
            var dimensionDto = getEmptyDimensionDto();

            // when
            var httpEntity = new HttpEntity<>(dimensionDto);
            var responseEntity = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        public void testInsertDimension_InsertDimensionWithNullName_ReturnHttpStatusBadRequest() {
            // given
            DimensionDto dimensionDto = getDimensionDto();
            dimensionDto.setName(null);

            // when
            var httpEntity = new HttpEntity(dimensionDto);
            var responseEntity = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        public void testInsertDimension_InsertDimensionWithNullDescription_ReturnHttpStatusBadRequest() {
            // given
            DimensionDto dimensionDto = getDimensionDto();
            dimensionDto.setDescription(null);

            // when
            var httpEntity = new HttpEntity(dimensionDto);
            var responseEntity = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        public void testInsertDimension_InsertDimensionWithNullType_ReturnHttpStatusBadRequest() {
            // given
            DimensionDto dimensionDto = getDimensionDto();
            dimensionDto.setType(null);

            // when
            var httpEntity = new HttpEntity(dimensionDto);
            var responseEntity = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "typo"})
        public void testInsertDimension_InsertDimensionWithIllegalType_ReturnHttpStatusBadRequest(String value) {
            // given
            DimensionDto dimensionDto = getDimensionDto();
            dimensionDto.setType(value);

            // when
            var httpEntity = new HttpEntity(dimensionDto);
            var responseEntity = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // then
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        public void testInsertDimension_InsertWithNotNullId_ReturnHttpStatusBadRequest() {
            // given
            var dimensionDto = getDimensionDto();
            dimensionDto.setId(UUID.randomUUID().toString());

            // when
            var httpEntity = new HttpEntity(dimensionDto);
            var responseEntity = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            httpEntity = new HttpEntity(responseEntity.getBody());
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
            var dimension = getDimension();
            var dimensionDto = toDto(dimension);
            var httpEntity = new HttpEntity<>(dimensionDto);
            var postResponse = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // when
            var updatedDimension = fromDto(postResponse.getBody());
            updatedDimension.setName("new_name");
            var putEntity = new HttpEntity<>(toDto(updatedDimension));
            var putResponse = testRestTemplate.exchange(
                    DimensionRest.buildPutDimensionUri(postResponse.getBody().getId()), HttpMethod.PUT, putEntity, DimensionDto.class);
            var getResponse = testRestTemplate.getForEntity(
                    DimensionRest.buildGetDimensionUri(postResponse.getBody().getId()), DimensionDto.class);

            // then
            assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(putResponse.getBody().getId()).isEqualTo(updatedDimension.getId());
            assertThat(putResponse.getBody().getName()).isEqualTo(updatedDimension.getName());

            assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(getResponse.getBody().getId()).isNotNull();
            assertThat(getResponse.getBody().getId()).isEqualTo(updatedDimension.getId());
            assertThat(getResponse.getBody().getName()).isEqualTo(updatedDimension.getName());
        }

        @Test
        public void testUpdateDimension_UpdateNonExistingId_ReturnHttpStatusNotFound() {
            // given
            var dimension = getDimension();
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
            var dimension = getDimension();
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
            var dimension = getDimension();
            var dimensionDto = toDto(dimension);
            var httpEntity = new HttpEntity<>(dimensionDto);
            var postResponse = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // when
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
            var dimension = getDimension();
            var dimensionDto = toDto(dimension);
            var httpEntity = new HttpEntity<>(dimensionDto);
            var postResponse = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // when
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
            var dimension = getDimension();
            var dimensionDto = toDto(dimension);
            var httpEntity = new HttpEntity<>(dimensionDto);
            var postResponse = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // when
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
            var dimension = getDimension();
            var dimensionDto = toDto(dimension);
            var httpEntity = new HttpEntity<>(dimensionDto);
            var postResponse = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // when
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
            var dimension = getDimension();
            var dimensionDto = toDto(dimension);
            var httpEntity = new HttpEntity<>(dimensionDto);
            var postResponse = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), httpEntity, DimensionDto.class);

            // when
            var updatedDimension = fromDto(postResponse.getBody());
            var updatedDimensionDto = toDto(updatedDimension);
            updatedDimensionDto = null;
            var putEntity = new HttpEntity<>(updatedDimensionDto);
            var putResponse = testRestTemplate.exchange(
                    DimensionRest.buildPutDimensionUri(postResponse.getBody().getId()), HttpMethod.PUT, putEntity, DimensionDto.class);

            // then
            assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        // Note: RestController code is not being executed. The error is automatically thrown.
        @Test
        public void testUpdateDimension_UpdateNullDtoIntoNonExistingId_ReturnHttpStatusBadRequest() {
            // given
            DimensionDto dimensionDto = null;

            // when
            var putEntity = new HttpEntity<>(dimensionDto);
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
            var dimension = getDimension();
            var dimensionDto = toDto(dimension);
            var insertEntity = new HttpEntity<>(dimensionDto);
            var insertEntityResponse = testRestTemplate.postForEntity(
                    DimensionRest.buildPostDimensionUri(), insertEntity, DimensionDto.class);

            // when
            var insertedDimensionDto = insertEntityResponse.getBody();
            var deleteEntity = new HttpEntity<>(insertedDimensionDto);
            var deleteEntityResponse = testRestTemplate.exchange(
                    DimensionRest.buildDeleteDimensionUri(insertedDimensionDto.getId()), HttpMethod.DELETE, deleteEntity, Void.class);

            // then
            assertThat(deleteEntityResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

            var getResponse = testRestTemplate.getForEntity(
                    DimensionRest.buildGetDimensionsUri(), DimensionDto[].class);
            var dimensions = getResponse.getBody();
            assertThat(dimensions.length).isEqualTo(0);
        }

        @Test
        public void testDeleteDimension_DeleteNonExistingId_ReturnHttpStatusNotFound() {
            // given
            var dimensionDto = getDimensionDto();
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
            var dimension = getDimension();
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
