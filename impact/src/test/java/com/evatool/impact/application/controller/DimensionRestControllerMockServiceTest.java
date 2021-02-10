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

import static com.evatool.impact.application.controller.util.DimensionRest.*;
import static com.evatool.impact.common.TestDataGenerator.createDummyDimensionDto;
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
        public void testGetDimension_ExistingDimension_CorrectRestLevel3() throws Exception {
            // given
            var dimensionDto = createDummyDimensionDto();
            var id = UUID.randomUUID().toString();
            dimensionDto.setId(id);

            // when
            given(dimensionService.findDimensionById(any(String.class))).willReturn(dimensionDto);

            // then
            mvc.perform(get(buildGetDimensionUri("dummy_id"))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.links").isNotEmpty())
                    .andExpect(jsonPath("$.links", hasSize(5)))
                    .andExpect(jsonPath("$.links[0].rel").value(buildGetDimensionsRel()))
                    .andExpect(jsonPath("$.links[0].href").value("http://localhost" + buildGetDimensionsUri()))
                    .andExpect(jsonPath("$.links[1].rel").value(buildPostDimensionRel()))
                    .andExpect(jsonPath("$.links[1].href").value("http://localhost" + buildPostDimensionUri()))
                    .andExpect(jsonPath("$.links[2].rel").value("self"))
                    .andExpect(jsonPath("$.links[2].href").value("http://localhost" + buildGetDimensionUri(id)))
                    .andExpect(jsonPath("$.links[3].rel").value(buildPutDimensionRel()))
                    .andExpect(jsonPath("$.links[3].href").value("http://localhost" + buildPutDimensionUri(id)))
                    .andExpect(jsonPath("$.links[4].rel").value(buildDeleteDimensionRel()))
                    .andExpect(jsonPath("$.links[4].href").value("http://localhost" + buildDeleteDimensionUri(id)));
        }

        @Test
        public void testGetDimensionById_ExistingDimension_ReturnDimension() throws Exception {
            // given
            var dimensionDto = createDummyDimensionDto();

            // when
            when(dimensionService.findDimensionById(anyString())).thenReturn(dimensionDto);

            // then
            mvc.perform(get(buildGetDimensionUri("dummy_id"))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name").value(dimensionDto.getName()));
        }

        @Test
        public void testGetDimensionById_NonExistingDimension_ReturnHttpStatusNotFound() throws Exception {
            // given
            var nonExistingId = "wrong_id";

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
    public class GetAll {
        @Test
        public void testGetAllDimensions_ExistingDimension_CorrectRestLevel3() throws Exception {
            // given
            var dimensionDto = createDummyDimensionDto();
            var id = UUID.randomUUID().toString();
            dimensionDto.setId(id);

            // when
            given(dimensionService.getAllDimensions()).willReturn(Arrays.asList(dimensionDto));

            // then
            mvc.perform(get(buildGetDimensionsUri())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0]..links").isNotEmpty())
                    .andExpect(jsonPath("$[0]..links", hasSize(1)))
                    .andExpect(jsonPath("$[0]..links[0].rel").value(buildGetDimensionsRel()))
                    .andExpect(jsonPath("$[0]..links[0].href").value("http://localhost" + buildGetDimensionsUri()))
                    .andExpect(jsonPath("$[0]..links[1].rel").value(buildPostDimensionRel()))
                    .andExpect(jsonPath("$[0]..links[1].href").value("http://localhost" + buildPostDimensionUri()))
                    .andExpect(jsonPath("$[0]..links[2].rel").value("self"))
                    .andExpect(jsonPath("$[0]..links[2].href").value("http://localhost" + buildGetDimensionUri(id)))
                    .andExpect(jsonPath("$[0]..links[3].rel").value(buildPutDimensionRel()))
                    .andExpect(jsonPath("$[0]..links[3].href").value("http://localhost" + buildPutDimensionUri(id)))
                    .andExpect(jsonPath("$[0]..links[4].rel").value(buildDeleteDimensionRel()))
                    .andExpect(jsonPath("$[0]..links[4].href").value("http://localhost" + buildDeleteDimensionUri(id)));
        }

        @Test
        public void testGetAllDimensions_ExistingDimensions_ReturnDimensions() throws Exception {
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
        public void testGetAllDimensions_ExistingDimensions_ReturnDimensions(int value) throws Exception {
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
    public class Insert {
        @Test
        public void testInsertDimension_InsertedDimension_CorrectRestLevel3() throws Exception {
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
        public void testInsertDimension_InsertedDimension_ReturnInsertedDimension() throws Exception {
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
                    .andExpect(jsonPath("$.links[0].rel").value(buildGetDimensionsRel()))
                    .andExpect(jsonPath("$.links[0].href").value("http://localhost" + buildGetDimensionsUri()))
                    .andExpect(jsonPath("$.links[1].rel").value(buildPostDimensionRel()))
                    .andExpect(jsonPath("$.links[1].href").value("http://localhost" + buildPostDimensionUri()))
                    .andExpect(jsonPath("$.links[2].rel").value("self"))
                    .andExpect(jsonPath("$.links[2].href").value("http://localhost" + buildGetDimensionUri(id)))
                    .andExpect(jsonPath("$.links[3].rel").value(buildPutDimensionRel()))
                    .andExpect(jsonPath("$.links[3].href").value("http://localhost" + buildPutDimensionUri(id)))
                    .andExpect(jsonPath("$.links[4].rel").value(buildDeleteDimensionRel()))
                    .andExpect(jsonPath("$.links[4].href").value("http://localhost" + buildDeleteDimensionUri(id)));
        }
    }

    @Nested
    public class Update {
        @Test
        public void testUpdateDimension_ExistingDimension_CorrectRestLevel3() throws Exception {
            // given
            var dimensionDto = createDummyDimensionDto();
            var id = UUID.randomUUID().toString();
            dimensionDto.setId(id);

            // when
            when(dimensionService.createDimension(any(DimensionDto.class))).thenReturn(dimensionDto);
            dimensionDto.setName("new_name");
            when(dimensionService.updateDimension(any(DimensionDto.class))).thenReturn(dimensionDto);

            // then
            mvc.perform(put(DimensionRest.buildPutDimensionUri("dummy_id")).content(new ObjectMapper().writeValueAsString(dimensionDto))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.links").isNotEmpty())
                    .andExpect(jsonPath("$.links", hasSize(5)))
                    .andExpect(jsonPath("$.links[0].rel").value(buildGetDimensionsRel()))
                    .andExpect(jsonPath("$.links[0].href").value("http://localhost" + buildGetDimensionsUri()))
                    .andExpect(jsonPath("$.links[1].rel").value(buildPostDimensionRel()))
                    .andExpect(jsonPath("$.links[1].href").value("http://localhost" + buildPostDimensionUri()))
                    .andExpect(jsonPath("$.links[2].rel").value("self"))
                    .andExpect(jsonPath("$.links[2].href").value("http://localhost" + buildGetDimensionUri(id)))
                    .andExpect(jsonPath("$.links[3].rel").value(buildPutDimensionRel()))
                    .andExpect(jsonPath("$.links[3].href").value("http://localhost" + buildPutDimensionUri(id)))
                    .andExpect(jsonPath("$.links[4].rel").value(buildDeleteDimensionRel()))
                    .andExpect(jsonPath("$.links[4].href").value("http://localhost" + buildDeleteDimensionUri(id)));
        }

        @Test
        public void testUpdateDimension_UpdatedDimension_ReturnUpdatedDimension() throws Exception {
            // given
            var dimensionDto = createDummyDimensionDto();
            dimensionDto.setId(UUID.randomUUID().toString());

            // when
            when(dimensionService.createDimension(any(DimensionDto.class))).thenReturn(dimensionDto);
            dimensionDto.setName("new_name");
            when(dimensionService.updateDimension(any(DimensionDto.class))).thenReturn(dimensionDto);

            // then
            mvc.perform(put(DimensionRest.buildPutDimensionUri("dummy_id")).content(new ObjectMapper().writeValueAsString(dimensionDto))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").exists())
                    .andExpect(jsonPath("$.name").value(dimensionDto.getName()));
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
