package com.evatool.impact.application.controller;

import com.evatool.impact.ImpactModule;
import com.evatool.impact.application.controller.util.DimensionRest;
import com.evatool.impact.application.dto.DimensionDto;
import com.evatool.impact.application.service.DimensionService;
import com.evatool.impact.common.config.SwaggerConfig;
import com.evatool.impact.common.exception.EntityNotFoundException;
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

import static com.evatool.impact.application.controller.util.DimensionRest.*;
import static com.evatool.impact.application.controller.util.DimensionRest.buildDeleteDimensionRel;
import static com.evatool.impact.common.TestDataGenerator.createDummyDimensionDto;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
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
            mvc.perform(get(buildGetDimensionUri(UUID.randomUUID().toString()))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.links").isNotEmpty())
                    .andExpect(jsonPath("$.links", hasSize(5)))
                    .andExpect(jsonPath("$.links[*].rel").value(containsInAnyOrder(
                            buildGetDimensionsRel(),
                            buildPostDimensionRel(),
                            "self",
                            buildPutDimensionRel(),
                            buildDeleteDimensionRel())))
                    .andExpect(jsonPath("$.links[*].href").value(containsInAnyOrder(
                            "http://localhost" + buildGetDimensionsUri(),
                            "http://localhost" + buildPostDimensionUri(),
                            "http://localhost" + buildGetDimensionUri(id),
                            "http://localhost" + buildPutDimensionUri(id),
                            "http://localhost" + buildDeleteDimensionUri(id))));
        }

        @Test
        void testGetDimensionById_ExistingDimension_ReturnDimension() throws Exception {
            // given
            var dimensionDto = createDummyDimensionDto();

            // when
            when(dimensionService.findDimensionById(anyString())).thenReturn(dimensionDto);

            // then
            mvc.perform(get(buildGetDimensionUri(UUID.randomUUID().toString()))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name").value(dimensionDto.getName()));
        }

        @Test
        void testGetDimensionById_NonExistingDimension_ReturnHttpStatusNotFound() throws Exception {
            // given
            var nonExistingId = UUID.randomUUID().toString();

            // when
            when(dimensionService.findDimensionById(anyString())).thenThrow(EntityNotFoundException.class);

            // then
            mvc.perform(get(buildGetDimensionUri(nonExistingId))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isNotFound());
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
            mvc.perform(get(buildGetDimensionsUri())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0]..links").isNotEmpty())
                    .andExpect(jsonPath("$[0]..links", hasSize(1)))
                    .andExpect(jsonPath("$[0].links[*].rel").value(containsInAnyOrder(
                            buildGetDimensionsRel(),
                            buildPostDimensionRel(),
                            "self",
                            buildPutDimensionRel(),
                            buildDeleteDimensionRel())))
                    .andExpect(jsonPath("$[0].links[*].href").value(containsInAnyOrder(
                            "http://localhost" + buildGetDimensionsUri(),
                            "http://localhost" + buildPostDimensionUri(),
                            "http://localhost" + buildGetDimensionUri(id),
                            "http://localhost" + buildPutDimensionUri(id),
                            "http://localhost" + buildDeleteDimensionUri(id))));
        }

        @Test
        void testGetAllDimensions_ExistingDimensions_ReturnDimensions() throws Exception {
            // given
            var dimension1 = createDummyDimensionDto();
            var dimension2 = createDummyDimensionDto();

            // when
            var allDimensionDtos = Arrays.asList(dimension1, dimension2);
            given(dimensionService.getAllDimensions()).willReturn(allDimensionDtos);

            // then
            mvc.perform(get(buildGetDimensionsUri())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andExpect(jsonPath("$[0].name").value(dimension1.getName()))
                    .andExpect(jsonPath("$[1].name").value(dimension2.getName()));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5})
        void testGetAllDimensions_ExistingDimensions_ReturnDimensions(int value) throws Exception {
            var allDimensionDtos = new ArrayList<DimensionDto>();
            for (int i = 0; i < value; i++) {
                // given
                var dimensionDto = createDummyDimensionDto();
                allDimensionDtos.add(dimensionDto);
            }
            // when
            given(dimensionService.getAllDimensions()).willReturn(allDimensionDtos);

            // then
            mvc.perform(get(buildGetDimensionsUri())
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
            mvc.perform(post(DimensionRest.buildPostDimensionUri()).content(new ObjectMapper().writeValueAsString(dimensionDto))
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
            mvc.perform(post(DimensionRest.buildPostDimensionUri()).content(new ObjectMapper().writeValueAsString(dimensionDto))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.links").isNotEmpty())
                    .andExpect(jsonPath("$.links", hasSize(5)))
                    .andExpect(jsonPath("$.links[*].rel").value(containsInAnyOrder(
                            buildGetDimensionsRel(),
                            buildPostDimensionRel(),
                            "self",
                            buildPutDimensionRel(),
                            buildDeleteDimensionRel())))
                    .andExpect(jsonPath("$.links[*].href").value(containsInAnyOrder(
                            "http://localhost" + buildGetDimensionsUri(),
                            "http://localhost" + buildPostDimensionUri(),
                            "http://localhost" + buildGetDimensionUri(id),
                            "http://localhost" + buildPutDimensionUri(id),
                            "http://localhost" + buildDeleteDimensionUri(id))));
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
            mvc.perform(put(DimensionRest.buildPutDimensionUri(UUID.randomUUID().toString())).content(new ObjectMapper().writeValueAsString(dimensionDto))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.links").isNotEmpty())
                    .andExpect(jsonPath("$.links", hasSize(5)))
                    .andExpect(jsonPath("$.links[*].rel").value(containsInAnyOrder(
                            buildGetDimensionsRel(),
                            buildPostDimensionRel(),
                            "self",
                            buildPutDimensionRel(),
                            buildDeleteDimensionRel())))
                    .andExpect(jsonPath("$.links[*].href").value(containsInAnyOrder(
                            "http://localhost" + buildGetDimensionsUri(),
                            "http://localhost" + buildPostDimensionUri(),
                            "http://localhost" + buildGetDimensionUri(id),
                            "http://localhost" + buildPutDimensionUri(id),
                            "http://localhost" + buildDeleteDimensionUri(id))));
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
            mvc.perform(put(DimensionRest.buildPutDimensionUri(UUID.randomUUID().toString())).content(new ObjectMapper().writeValueAsString(dimensionDto))
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
        void testDeleteDimension_DeletedDimension_ReturnNull() throws Exception {
            // given

            // when

            // then
            mvc.perform(delete(DimensionRest.buildDeleteDimensionUri(UUID.randomUUID().toString()))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
        }
    }
}
