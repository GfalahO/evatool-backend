package com.evatool.impact.application.controller;

import com.evatool.EvaToolApp;
import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.application.service.DimensionService;
import com.evatool.global.config.SwaggerConfig;
import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.domain.entity.Dimension;
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

import java.util.*;

import static com.evatool.impact.application.controller.UriUtil.*;
import static com.evatool.impact.common.TestDataGenerator.createDummyDimensionDto;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DimensionRestController.class)
@ContextConfiguration(classes = {SwaggerConfig.class, EvaToolApp.class})
class DimensionRestControllerMockServiceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DimensionService dimensionService;

    @Nested
    class GetById {

        @Test
        void testGetDimensionById_ExistingDimension_ReturnDimension() throws Exception {
            // given
            var dimensionDto = createDummyDimensionDto();

            // when
            when(dimensionService.findDimensionById(any(UUID.class))).thenReturn(dimensionDto);

            // then
            mvc.perform(get(DIMENSIONS + "/" + UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name").value(dimensionDto.getName()));
        }

        @Test
        void testGetDimension_ExistingDimension_CorrectRestLevel3() throws Exception {
            // given
            var dimensionDto = createDummyDimensionDto();
            dimensionDto.setId(UUID.randomUUID());

            // when
            given(dimensionService.findDimensionById(any(UUID.class))).willReturn(dimensionDto);

            // then
            mvc.perform(get(DIMENSIONS + "/" + UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.links").isNotEmpty())
                    .andExpect(jsonPath("$.links", hasSize(3)))
                    .andExpect(jsonPath("$.links[*].rel").value(containsInAnyOrder(
                            "self",
                            UPDATE_DIMENSION,
                            DELETE_DIMENSION)))
                    .andExpect(jsonPath("$.links[*].href").value(containsInAnyOrder(
                            "http://localhost" + DIMENSIONS,
                            "http://localhost" + DIMENSIONS + "/" + dimensionDto.getId(),
                            "http://localhost" + DIMENSIONS + "/" + dimensionDto.getId())));
        }

        @Test
        void testGetDimensionById_NonExistingDimension_ReturnErrorMessage() throws Exception {
            // given
            var id = UUID.randomUUID().toString();

            // when
            when(dimensionService.findDimensionById(any(UUID.class))).thenThrow(EntityNotFoundException.class);

            // then
            mvc.perform(get(DIMENSIONS + "/" + id)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.*", hasSize(6)))
                    .andExpect(jsonPath("$.timestamp").isNotEmpty())
                    .andExpect(jsonPath("$.status").isNotEmpty())
                    .andExpect(jsonPath("$.error").isNotEmpty())
                    .andExpect(jsonPath("$.trace").isNotEmpty())
                    .andExpect(jsonPath("$.message").isEmpty()) // exists but contains null.
                    .andExpect(jsonPath("$.path").isNotEmpty());
        }
    }

    @Nested
    class GetAll {

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3})
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
    class GetByType {

        @Test
        void testGetByType_ExistingDimensions_ReturnDimensions() throws Exception {
            // given
            var socialDimensions = new ArrayList<DimensionDto>();
            for (int i = 0; i < 3; i++) {
                var socialDimension = createDummyDimensionDto();
                socialDimension.setType(Dimension.Type.SOCIAL);
                socialDimensions.add(socialDimension);
            }

            var economicDimensions = new ArrayList<DimensionDto>();
            for (int i = 0; i < 4; i++) {
                var economicDimension = createDummyDimensionDto();
                economicDimension.setType(Dimension.Type.ECONOMIC);
                economicDimensions.add(economicDimension);
            }

            // when
            given(dimensionService.findDimensionsByType(Dimension.Type.SOCIAL)).willReturn(socialDimensions);
            given(dimensionService.findDimensionsByType(Dimension.Type.ECONOMIC)).willReturn(economicDimensions);

            // then
            mvc.perform(get(DIMENSIONS).param("type", Dimension.Type.SOCIAL.toString())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(socialDimensions.size())));

            mvc.perform(get(DIMENSIONS + "?type=" + Dimension.Type.ECONOMIC.toString())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(economicDimensions.size())));
        }
    }

    @Nested
    class Insert {

        @Test
        void testInsertDimension_InsertedDimension_ReturnInsertedDimension() throws Exception {
            // given
            var dimensionDto = createDummyDimensionDto();
            var id = UUID.randomUUID();
            dimensionDto.setId(id);

            // when
            when(dimensionService.createDimension(any(DimensionDto.class))).thenReturn(dimensionDto);

            // then
            mvc.perform(post(DIMENSIONS).content(new ObjectMapper().writeValueAsString(dimensionDto))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isCreated());
        }
    }

    @Nested
    class Update {

        @Test
        void testUpdateDimension_UpdatedDimension_ReturnUpdatedDimension() throws Exception {
            // given
            var dimensionDto = createDummyDimensionDto();
            dimensionDto.setId(UUID.randomUUID());

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
    }

    @Nested
    class Delete {

        @Test
        void testDeleteDimension_DeletedDimension_ReturnNoDimensions() throws Exception {
            // given

            // when
            doNothing().when(dimensionService).deleteDimensionById(any(UUID.class));

            // then
            mvc.perform(delete(DIMENSIONS + "/" + UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
        }
    }
}

