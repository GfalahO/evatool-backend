package com.evatool.impact.common.controller;

import com.evatool.impact.persistence.entity.Stakeholder;
import com.evatool.impact.persistence.repository.StakeholderRepository;
import com.evatool.impact.service.api.rest.StakeholderRestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static com.evatool.impact.persistence.TestDataGenerator.getStakeholder;
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
public class StakeholderRestControllerTest {
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
        when(stakeholderRestService.getStakeholderById(anyString())).thenReturn(stakeholder);

        // then
        mvc.perform(get("/api/stakeholder/dummy_id")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(stakeholder.getName()));
    }
    // TODO: Test when getting null

    @Test
    public void testSaveStakeholder_ChangedStakeholder_ReturnChangedStakeholder() throws Exception {
        // given
        var stakeholder = getStakeholder();

        // when
        when(stakeholderRestService.saveStakeholder(any(Stakeholder.class))).thenReturn(stakeholder);

        // then
        mvc.perform(post("/api/stakeholder")
                .content(new ObjectMapper().writeValueAsString(stakeholder))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                //.andExpect(jsonPath("$.id").exists()) // TODO: id is not getting assigned and setId should be exist.
                .andExpect(jsonPath("$.name").value(stakeholder.getName()));
    }
    // TODO: Test when updating?
    // TODO: Test when inserting null

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
    // TODO: Test delete when id does not exist

    @Test
    public void testGetAllStakeholders_ExistingStakeholders_ReturnStakeholders() throws Exception {
        // given
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
    // TODO: Parameterized test with get all stakeholders...
}
