package com.evatool.impact.common.controller;

import com.evatool.impact.persistence.entity.Stakeholder;
import com.evatool.impact.service.api.rest.StakeholderRestService;
import com.evatool.impact.service.impl.rest.StakeholderRestServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static com.evatool.impact.persistence.TestDataGenerator.getStakeholder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

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

}
