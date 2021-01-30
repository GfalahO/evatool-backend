package com.evatool.impact.service.api.rest;

import com.evatool.impact.common.controller.StakeholderRestController;
import com.evatool.impact.common.dto.StakeholderDto;
import com.evatool.impact.service.api.rest.StakeholderRestService;
import com.evatool.impact.service.impl.StakeholderNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.UUID;

import static com.evatool.impact.persistence.TestDataGenerator.getStakeholder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
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

    @Autowired
    private StakeholderRestController stakeholderRestController;

    @Test
    public void testGetStakeholderById_ExistingStakeholder_ReturnStakeholder() throws Exception {
        // given
        var stakeholder = getStakeholder();

        // when
        when(stakeholderRestService.getStakeholderById(anyString())).thenReturn(stakeholder.toDto());

        // then
        mvc.perform(get("/api/stakeholder/dummy_id")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(stakeholder.getName()));
    }

    @Test
    public void testInsertStakeholder_InsertedStakeholder_ReturnInsertedStakeholder() throws Exception {
        // given
        var stakeholder = getStakeholder();
        stakeholder.setId(UUID.randomUUID().toString());

        // when
        when(stakeholderRestService.insertStakeholder(any(StakeholderDto.class))).thenReturn(stakeholder.toDto());

        // then
        mvc.perform(post("/api/stakeholder")
                .content(new ObjectMapper().writeValueAsString(stakeholder))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(stakeholder.getName()));
    }

    @Test
    public void testUpdateStakeholder() throws Exception {
        // given
        var stakeholder = getStakeholder();
        stakeholder.setId(UUID.randomUUID().toString());

        // when
        when(stakeholderRestService.insertStakeholder(any(StakeholderDto.class))).thenReturn(stakeholder.toDto());
        stakeholder.setName("new_name");
        when(stakeholderRestService.updateStakeholder(any(StakeholderDto.class))).thenReturn(stakeholder.toDto());

        // then
        mvc.perform(put("/api/stakeholder/dummy_id")
                .content(new ObjectMapper().writeValueAsString(stakeholder))
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

    @Test
    public void testGetAllStakeholders_ExistingStakeholders_ReturnStakeholders() throws Exception {
        // given
        var stakeholder1 = getStakeholder();
        var stakeholder2 = getStakeholder();

        // when
        var allStakeholders = Arrays.asList(stakeholder1.toDto(), stakeholder2.toDto());
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
    // TODO: Parameterized test with get all stakeholders...
}
