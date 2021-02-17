package com.evatool.impact.application.controller;

import com.evatool.impact.ImpactModule;
import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.application.service.DimensionService;
import com.evatool.impact.common.config.SwaggerConfig;
import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.common.exception.InvalidUuidException;
import com.evatool.impact.common.exception.PropertyViolationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import static com.evatool.impact.application.controller.UriUtil.*;
import static com.evatool.impact.common.TestDataGenerator.createDummyDimensionDto;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DimensionRestController.class)
@ContextConfiguration(classes = {SwaggerConfig.class, ImpactModule.class})
class DimensionRestControllerMockServiceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DimensionService dimensionService;

    @Nested
    class GetById {
        @Test
        void testGetDimension_ExistingDimension_CorrectRestLevel3() throws Exception {
            // given
            var dimensionDto = createDummyDimensionDto();
            var id = UUID.randomUUID().toString();
            dimensionDto.setId(id);

            // when
            given(dimensionService.findDimensionById(any(String.class))).willReturn(dimensionDto);

            // then
            mvc.perform(get(DIMENSIONS + "/" + UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.links").isNotEmpty())
                    .andExpect(jsonPath("$.links", hasSize(5)))
                    .andExpect(jsonPath("$.links[*].rel").value(containsInAnyOrder(
                            GET_DIMENSIONS,
                            CREATE_DIMENSIONS,
                            "self",
                            UPDATE_DIMENSIONS,
                            DELETE_DIMENSIONS)))
                    .andExpect(jsonPath("$.links[*].href").value(containsInAnyOrder(
                            "http://localhost" + DIMENSIONS,
                            "http://localhost" + DIMENSIONS,
                            "http://localhost" + DIMENSIONS + "/" + id,
                            "http://localhost" + DIMENSIONS + "/" + id,
                            "http://localhost" + DIMENSIONS + "/" + id))); // TODO [hbuhl] is { DIMENSIONS + "/" + id } so complex that we need ab build-method? We can read it directly and there is no need to go into the method to check

            // TODO [hbuhl] we write code to read it later and not to use complex things for something very simple; please take it as an advise
        }

        @Test
        void testGetDimensionById_ExistingDimension_ReturnDimension() throws Exception {
            // given
            var dimensionDto = createDummyDimensionDto();

            // when
            when(dimensionService.findDimensionById(anyString())).thenReturn(dimensionDto);

            // then
            mvc.perform(get(DIMENSIONS + "/" + UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name").value(dimensionDto.getName()));
        }

        @Test
        void testGetDimensionById_NonExistingDimension_ReturnHttpStatusNotFoundAndErrorMessage() throws Exception {
            // given
            var id = UUID.randomUUID().toString();

            // when
            when(dimensionService.findDimensionById(anyString())).thenThrow(EntityNotFoundException.class);

            // then
            mvc.perform(get(DIMENSIONS + "/" + id)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.*", hasSize(3)))
                    .andExpect(jsonPath("$.timestamp").isNotEmpty())
                    .andExpect(jsonPath("$.message").isEmpty()) // exists but contains null.
                    .andExpect(jsonPath("$.uri").isNotEmpty());
        }

        @Test
        void testGetDimensionById_InvalidId_ReturnHttpStatusBadRequestAndErrorMessage() throws Exception {
            // given
            var id = "invalid id";

            // when
            when(dimensionService.findDimensionById(anyString())).thenThrow(InvalidUuidException.class);

            // then
            mvc.perform(get(DIMENSIONS + "/" + id)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.*", hasSize(3)))
                    .andExpect(jsonPath("$.timestamp").isNotEmpty())
                    .andExpect(jsonPath("$.message").isEmpty()) // exists but contains null.
                    .andExpect(jsonPath("$.uri").isNotEmpty());
        }
    }

    @Nested
    class GetAll {
        @Test
        void testGetAllDimensions_ExistingDimension_CorrectRestLevel3() throws Exception {
            // given
            var dimensionDto = createDummyDimensionDto();
            var id = UUID.randomUUID().toString();
            dimensionDto.setId(id);

            // when
            given(dimensionService.getAllDimensions()).willReturn(Collections.singletonList(dimensionDto));

            // then
            mvc.perform(get(DIMENSIONS)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0]..links").isNotEmpty())
                    .andExpect(jsonPath("$[0]..links", hasSize(1)))
                    .andExpect(jsonPath("$[0].links[*].rel").value(containsInAnyOrder(
                            GET_DIMENSIONS,
                            CREATE_DIMENSIONS,
                            "self",
                            UPDATE_DIMENSIONS,
                            DELETE_DIMENSIONS)))
                    .andExpect(jsonPath("$[0].links[*].href").value(containsInAnyOrder(
                            "http://localhost" + DIMENSIONS,
                            "http://localhost" + DIMENSIONS,
                            "http://localhost" + DIMENSIONS + "/" + id,
                            "http://localhost" + DIMENSIONS + "/" + id,
                            "http://localhost" + DIMENSIONS + "/" + id)));
        }

        @Test
        void testGetAllDimensions_ExistingDimensions_ReturnDimensions() throws Exception {
            // given
            var dimension1 = createDummyDimensionDto();
            dimension1.setId(UUID.randomUUID().toString());
            var dimension2 = createDummyDimensionDto();
            dimension2.setId(UUID.randomUUID().toString());

            // when
            var dimensionDtoList = Arrays.asList(dimension1, dimension2);
            given(dimensionService.getAllDimensions()).willReturn(dimensionDtoList);

            // then
            mvc.perform(get(DIMENSIONS)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andExpect(jsonPath("$[0].id").value(dimension1.getId()))
                    .andExpect(jsonPath("$[0].name").value(dimension1.getName()))
                    .andExpect(jsonPath("$[0].description").value(dimension1.getDescription()))
                    .andExpect(jsonPath("$[0].type").value(dimension1.getType()))
                    .andExpect(jsonPath("$[1].id").value(dimension2.getId()))
                    .andExpect(jsonPath("$[1].name").value(dimension2.getName()))
                    .andExpect(jsonPath("$[1].description").value(dimension2.getDescription()))
                    .andExpect(jsonPath("$[1].type").value(dimension2.getType()));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5})
        void testGetAllDimensions_ExistingDimensions_ReturnDimensions(int value) throws Exception {
            var dimensionDtoList = new ArrayList<DimensionDto>();
            for (int i = 0; i < value; i++) {
                // given
                var dimensionDto = createDummyDimensionDto();
                dimensionDtoList.add(dimensionDto);
            }
            // when
            given(dimensionService.getAllDimensions()).willReturn(dimensionDtoList);

            // then
            mvc.perform(get(DIMENSIONS)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(value)));
        }
    }

    @Nested
    class Insert {
        @Test
        void testInsertDimension_InsertedDimension_CorrectRestLevel3() throws Exception {
            // given
            var dimensionDto = createDummyDimensionDto();
            dimensionDto.setId(UUID.randomUUID().toString());

            // when
            when(dimensionService.createDimension(any(DimensionDto.class))).thenReturn(dimensionDto);

            // then
            mvc.perform(post(DIMENSIONS).content(new ObjectMapper().writeValueAsString(dimensionDto))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").exists())
                    .andExpect(jsonPath("$.id").value(dimensionDto.getId()))
                    .andExpect(jsonPath("$.name").value(dimensionDto.getName()));

        }

        @Test
        void testInsertDimension_InsertedDimension_ReturnInsertedDimension() throws Exception {
            // given
            var dimensionDto = createDummyDimensionDto();
            var id = UUID.randomUUID().toString();
            dimensionDto.setId(id);

            // when
            when(dimensionService.createDimension(any(DimensionDto.class))).thenReturn(dimensionDto);

            // then
            mvc.perform(post(DIMENSIONS).content(new ObjectMapper().writeValueAsString(dimensionDto))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.links").isNotEmpty())
                    .andExpect(jsonPath("$.links", hasSize(5)))
                    .andExpect(jsonPath("$.links[*].rel").value(containsInAnyOrder(
                            GET_DIMENSIONS,
                            CREATE_DIMENSIONS,
                            "self",
                            UPDATE_DIMENSIONS,
                            DELETE_DIMENSIONS)))
                    .andExpect(jsonPath("$.links[*].href").value(containsInAnyOrder(
                            "http://localhost" + DIMENSIONS,
                            "http://localhost" + DIMENSIONS,
                            "http://localhost" + DIMENSIONS + "/" + id,
                            "http://localhost" + DIMENSIONS + "/" + id,
                            "http://localhost" + DIMENSIONS + "/" + id)));
        }

        @Test
        void testInsertDimension_NotNullId_ReturnHttpStatusBadRequestAndErrorMessage() throws Exception {
            // given
            var dimensionDto = createDummyDimensionDto();
            var id = UUID.randomUUID().toString();
            dimensionDto.setId(id);

            // when
            when(dimensionService.createDimension(any(DimensionDto.class))).thenThrow(PropertyViolationException.class);

            // then
            mvc.perform(post(DIMENSIONS).content(new ObjectMapper().writeValueAsString(dimensionDto))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.*", hasSize(3)))
                    .andExpect(jsonPath("$.timestamp").isNotEmpty())
                    .andExpect(jsonPath("$.message").isEmpty()) // exists but contains null.
                    .andExpect(jsonPath("$.uri").isNotEmpty());
        }
    }

    @Nested
    class Update {
        @Test
        void testUpdateDimension_ExistingDimension_CorrectRestLevel3() throws Exception {
            // given
            var dimensionDto = createDummyDimensionDto();
            var id = UUID.randomUUID().toString();
            dimensionDto.setId(id);

            // when
            when(dimensionService.createDimension(any(DimensionDto.class))).thenReturn(dimensionDto);
            dimensionDto.setName("new_name");
            when(dimensionService.updateDimension(any(DimensionDto.class))).thenReturn(dimensionDto);

            // then
            mvc.perform(put(DIMENSIONS).content(new ObjectMapper().writeValueAsString(dimensionDto))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.links").isNotEmpty())
                    .andExpect(jsonPath("$.links", hasSize(5)))
                    .andExpect(jsonPath("$.links[*].rel").value(containsInAnyOrder(
                            GET_DIMENSIONS,
                            CREATE_DIMENSIONS,
                            "self",
                            UPDATE_DIMENSIONS,
                            DELETE_DIMENSIONS)))
                    .andExpect(jsonPath("$.links[*].href").value(containsInAnyOrder(
                            "http://localhost" + DIMENSIONS,
                            "http://localhost" + DIMENSIONS,
                            "http://localhost" + DIMENSIONS + "/" + id,
                            "http://localhost" + DIMENSIONS + "/" + id,
                            "http://localhost" + DIMENSIONS + "/" + id)));
        }

        @Test
        void testUpdateDimension_UpdatedDimension_ReturnUpdatedDimension() throws Exception {
            // given
            var dimensionDto = createDummyDimensionDto();
            dimensionDto.setId(UUID.randomUUID().toString());

            // when
            when(dimensionService.createDimension(any(DimensionDto.class))).thenReturn(dimensionDto);
            dimensionDto.setName("new_name");
            when(dimensionService.updateDimension(any(DimensionDto.class))).thenReturn(dimensionDto);

            // then
            mvc.perform(put(DIMENSIONS).content(new ObjectMapper().writeValueAsString(dimensionDto))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").exists())
                    .andExpect(jsonPath("$.name").value(dimensionDto.getName()));
        }

        @Test
        void testUpdateDimension_NonExistingDimension_ReturnHttpStatusNotFoundAndErrorMessage() throws Exception {
            // given
            var dimensionDto = createDummyDimensionDto();
            dimensionDto.setId(UUID.randomUUID().toString());

            // when
            when(dimensionService.updateDimension(any(DimensionDto.class))).thenThrow(EntityNotFoundException.class);

            // then
            mvc.perform(put(DIMENSIONS).content(new ObjectMapper().writeValueAsString(dimensionDto))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.*", hasSize(3)))
                    .andExpect(jsonPath("$.timestamp").isNotEmpty())
                    .andExpect(jsonPath("$.message").isEmpty()) // exists but contains null.
                    .andExpect(jsonPath("$.uri").isNotEmpty());
        }

        @Test
        void testUpdateDimension_InvalidId_ReturnHttpStatusBadRequestAndErrorMessage() throws Exception {
            // given
            var dimensionDto = createDummyDimensionDto();
            dimensionDto.setId("invalid id");

            // when
            when(dimensionService.updateDimension(any(DimensionDto.class))).thenThrow(InvalidUuidException.class);

            // then
            mvc.perform(put(DIMENSIONS).content(new ObjectMapper().writeValueAsString(dimensionDto))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.*", hasSize(3)))
                    .andExpect(jsonPath("$.timestamp").isNotEmpty())
                    .andExpect(jsonPath("$.message").isEmpty()) // exists but contains null.
                    .andExpect(jsonPath("$.uri").isNotEmpty());
        }
    }

    @Nested
    class Delete {
        @Test
        void testDeleteDimension_DeletedDimension_ReturnNull() throws Exception {
            // given

            // when
            doNothing().when(dimensionService).deleteDimensionById(any(String.class));

            // then
            mvc.perform(delete(DIMENSIONS + "/" + UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
        }

        @Test
        void testDeleteDimension_NonExistingDimension_ReturnHttpStatusNotFoundAndErrorMessage() throws Exception {
            // given

            // when
            doThrow(EntityNotFoundException.class).when(dimensionService).deleteDimensionById(any(String.class));

            // then
            mvc.perform(delete(DIMENSIONS + "/" + UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.*", hasSize(3)))
                    .andExpect(jsonPath("$.timestamp").isNotEmpty())
                    .andExpect(jsonPath("$.message").isEmpty()) // exists but contains null.
                    .andExpect(jsonPath("$.uri").isNotEmpty());
        }

        @Test
        void testDeleteDimension_InvalidId_ReturnHttpStatusNotFoundAndErrorMessage() throws Exception {
            // given

            // when
            doThrow(InvalidUuidException.class).when(dimensionService).deleteDimensionById(any(String.class));

            // then
            mvc.perform(delete(DIMENSIONS + "/" + UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.*", hasSize(3)))
                    .andExpect(jsonPath("$.timestamp").isNotEmpty())
                    .andExpect(jsonPath("$.message").isEmpty()) // exists but contains null.
                    .andExpect(jsonPath("$.uri").isNotEmpty());
        }
    }
}

