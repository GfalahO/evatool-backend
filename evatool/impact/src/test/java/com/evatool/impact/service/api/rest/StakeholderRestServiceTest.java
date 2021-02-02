package com.evatool.impact.service.api.rest;

import com.evatool.impact.common.controller.rest.StakeholderRestController;
import com.evatool.impact.common.dto.StakeholderDto;
import com.evatool.impact.common.mapper.StakeholderMapper;
import com.evatool.impact.persistence.entity.Stakeholder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static com.evatool.impact.persistence.TestDataGenerator.getStakeholder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StakeholderRestController.class)
public class StakeholderRestServiceTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private StakeholderRestService stakeholderRestService;

    @Test
    public void testGetStakeholderById_ExistingStakeholder_ReturnStakeholder() throws Exception {
        // given
        var stakeholderMapper = new StakeholderMapper();
        var stakeholder = getStakeholder();

        // when
        when(stakeholderRestService.getStakeholderById(anyString())).thenReturn(stakeholder);

        // then
        mvc.perform(get("/api/stakeholder/dummy_id")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(stakeholder.getName()));
    }

    @Test
    public void testGetAllStakeholders_ExistingStakeholders_ReturnStakeholders() throws Exception {
        // given
        var stakeholderMapper = new StakeholderMapper();
        var stakeholder1 = getStakeholder();
        var stakeholder2 = getStakeholder();

        // when
        var allStakeholders = Arrays.asList(stakeholder1, stakeholder2);
        given(stakeholderRestService.getAllStakeholders()).willReturn(allStakeholders);

        // then
        mvc.perform(get("/api/stakeholders")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value(stakeholder1.getName()))
                .andExpect(jsonPath("$[1].name").value(stakeholder2.getName()));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    public void testGetAllStakeholders_ExistingStakeholders_ReturnStakeholders(int value) throws Exception {
        var stakeholderMapper = new StakeholderMapper();
        var allStakeholders = new ArrayList<Stakeholder>();
        for (int i = 0; i < value; i++) {
            // given
            var stakeholder = getStakeholder();
            allStakeholders.add(stakeholder);
        }
        // when
        given(stakeholderRestService.getAllStakeholders()).willReturn(allStakeholders);

        // then
        mvc.perform(get("/api/stakeholders")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(value)));
    }

    @Test
    public void testInsertStakeholder_InsertedStakeholder_ReturnInsertedStakeholder() throws Exception {
        // given
        var stakeholderMapper = new StakeholderMapper();
        var stakeholder = getStakeholder();
        stakeholder.setId(UUID.randomUUID().toString());

        // when
        when(stakeholderRestService.insertStakeholder(any(Stakeholder.class))).thenReturn(stakeholder);

        // then
        mvc.perform(post("/api/stakeholder").content(new ObjectMapper().writeValueAsString(stakeholder))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(stakeholder.getId()))
                .andExpect(jsonPath("$.name").value(stakeholder.getName()));
    }

    @Test
    public void testUpdateStakeholder() throws Exception {
        // given
        var stakeholderMapper = new StakeholderMapper();
        var stakeholder = getStakeholder();
        stakeholder.setId(UUID.randomUUID().toString());

        // when
        when(stakeholderRestService.insertStakeholder(any(Stakeholder.class))).thenReturn(stakeholder);
        stakeholder.setName("new_name");
        when(stakeholderRestService.updateStakeholder(any(Stakeholder.class))).thenReturn(stakeholder);

        // then
        mvc.perform(put("/api/stakeholder/dummy_id").content(new ObjectMapper().writeValueAsString(stakeholder))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(stakeholder.getName()));
    }

    @Test
    public void testDeleteStakeholder_DeletedStakeholder_ReturnNull() throws Exception {
        // given

        // when

        // then
        mvc.perform(delete("/api/stakeholder/dummy_id")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
