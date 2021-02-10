package com.evatool.impact.application.controller;

import com.evatool.impact.ImpactModule;
import com.evatool.impact.common.config.SwaggerConfig;
import com.evatool.impact.application.controller.util.StakeholderRest;
import com.evatool.impact.application.dto.StakeholderDto;
import com.evatool.impact.application.service.ImpactStakeholderService;
import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.domain.entity.ImpactStakeholder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static com.evatool.impact.common.TestDataGenerator.getStakeholderDto;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ImpactStakeholderRestController.class)
@ContextConfiguration(classes = {SwaggerConfig.class, ImpactModule.class})
public class ImpactStakeholderRestControllerMockServiceTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ImpactStakeholderService stakeholderService;

    @Nested
    public class GetAll {
        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5})
        public void testGetAllStakeholders_ExistingStakeholders_ReturnStakeholders(int value) throws Exception {
            var allStakeholders = new ArrayList<StakeholderDto>();
            for (int i = 0; i < value; i++) {
                // given
                var stakeholder = getStakeholderDto();
                allStakeholders.add(stakeholder);
            }
            // when
            given(stakeholderService.getAllStakeholders()).willReturn(allStakeholders);

            // then
            mvc.perform(get(StakeholderRest.buildGetStakeholdersUri())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(value)));
        }
    }
}
