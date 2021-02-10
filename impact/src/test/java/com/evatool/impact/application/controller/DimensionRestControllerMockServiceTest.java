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
import java.util.UUID;

import static com.evatool.impact.application.controller.util.DimensionRest.buildGetDimensionsUri;
import static com.evatool.impact.common.TestDataGenerator.*;
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
public class DimensionRestControllerMockServiceTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private DimensionService dimensionService;

    @Nested
    public class GetById {
        @Test
        public void testGetDimensionById_ExistingDimension_ReturnDimension() throws Exception {
            // given
            var dimension = getDimensionDto();

            // when
            when(dimensionService.findDimensionById(anyString())).thenReturn(dimension);

            // then
            mvc.perform(get(DimensionRest.buildGetDimensionUri("dummy_id"))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name").value(dimension.getName()));
        }

        @Test
        public void testGetDimensionById_NonExistingDimension_ReturnHttpStatusNotFound() throws Exception {
            // given
            var nonExistingId = "wrong_id";

            // when
            when(dimensionService.findDimensionById(anyString())).thenThrow(EntityNotFoundException.class);

            // then
            mvc.perform(get(DimensionRest.buildGetDimensionUri(nonExistingId))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    public class GetAll {
        @Test
        public void testGetAllDimensions_ExistingDimension_CorrectRestLevel3() throws Exception {
            // given
            var dimension = getDimensionDto();

            // when
            given(dimensionService.getAllDimensions()).willReturn(Arrays.asList(dimension));

            // then
            mvc.perform(get(buildGetDimensionsUri())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$..links[0].href").value("http://localhost" + buildGetDimensionsUri()));
        }

        @Test
        public void testGetAllDimensions_ExistingDimensions_ReturnDimensions() throws Exception {
            // given
            var dimension1 = getDimensionDto();
            var dimension2 = getDimensionDto();

            // when
            var allDimensions = Arrays.asList(dimension1, dimension2);
            given(dimensionService.getAllDimensions()).willReturn(allDimensions);

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
        public void testGetAllDimensions_ExistingDimensions_ReturnDimensions(int value) throws Exception {
            var allDimensions = new ArrayList<DimensionDto>();
            for (int i = 0; i < value; i++) {
                // given
                var dimension = getDimensionDto();
                allDimensions.add(dimension);
            }
            // when
            given(dimensionService.getAllDimensions()).willReturn(allDimensions);

            // then
            mvc.perform(get(buildGetDimensionsUri())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(value)));
        }
    }

    @Nested
    public class Insert {
        @Test
        public void testInsertDimension_InsertedDimensionWithExistingId_ReturnInsertedDimension() throws Exception {
            // given
            var dimension = getDimensionDto();
            dimension.setId(UUID.randomUUID().toString());

            // when
            when(dimensionService.createDimension(any(DimensionDto.class))).thenReturn(dimension);

            // then
            mvc.perform(post(DimensionRest.buildPostDimensionUri()).content(new ObjectMapper().writeValueAsString(dimension))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").exists())
                    .andExpect(jsonPath("$.id").value(dimension.getId()))
                    .andExpect(jsonPath("$.name").value(dimension.getName()))
                    .andExpect(jsonPath("$..links[0].href").value("http://localhost" + buildGetDimensionsUri()));
        }
    }

    @Nested
    public class Update {
        @Test
        public void testUpdateDimension_UpdatedDimension_ReturnUpdatedDimension() throws Exception {
            // given
            var dimension = getDimensionDto();
            dimension.setId(UUID.randomUUID().toString());

            // when
            when(dimensionService.createDimension(any(DimensionDto.class))).thenReturn(dimension);
            dimension.setName("new_name");
            when(dimensionService.updateDimension(any(DimensionDto.class))).thenReturn(dimension);

            // then
            mvc.perform(put(DimensionRest.buildPutDimensionUri("dummy_id")).content(new ObjectMapper().writeValueAsString(dimension))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").exists())
                    .andExpect(jsonPath("$.name").value(dimension.getName()));
        }
    }

    @Nested
    public class Delete {
        @Test
        public void testDeleteDimension_DeletedDimension_ReturnNull() throws Exception {
            // given

            // when

            // then
            mvc.perform(delete(DimensionRest.buildDeleteDimensionUri("dummy_id"))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
        }
    }
}
