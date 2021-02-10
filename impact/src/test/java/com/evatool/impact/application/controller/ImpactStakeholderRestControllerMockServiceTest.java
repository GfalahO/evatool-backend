package com.evatool.impact.application.controller;

import com.evatool.impact.ImpactModule;
import com.evatool.impact.application.dto.StakeholderDto;
import com.evatool.impact.application.service.ImpactStakeholderService;
import com.evatool.impact.common.config.SwaggerConfig;
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

import static com.evatool.impact.application.controller.util.StakeholderRest.buildGetStakeholdersRel;
import static com.evatool.impact.application.controller.util.StakeholderRest.buildGetStakeholdersUri;
import static com.evatool.impact.common.TestDataGenerator.createDummyStakeholderDto;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        @Test
        public void testGetAllStakeholders_ExistingStakeholder_CorrectRestLevel3() throws Exception {
            // given
            var stakeholderDto = createDummyStakeholderDto();
            var id = UUID.randomUUID().toString();
            stakeholderDto.setId(id);

            // when
            given(stakeholderService.getAllStakeholders()).willReturn(Collections.singletonList(stakeholderDto));

            // then
            mvc.perform(get(buildGetStakeholdersUri())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0]..links").isNotEmpty())
                    .andExpect(jsonPath("$[0]..links", hasSize(1)))
                    .andExpect(jsonPath("$[0]..links[0].rel").value(buildGetStakeholdersRel()))
                    .andExpect(jsonPath("$[0]..links[0].href").value("http://localhost" + buildGetStakeholdersUri()));
        }

        @Test
        public void testGetAllStakeholders_ExistingStakeholders_ReturnStakeholders() throws Exception {
            // given
            var stakeholder1 = createDummyStakeholderDto();
            var stakeholder2 = createDummyStakeholderDto();

            // when
            var allStakeholderDtos = Arrays.asList(stakeholder1, stakeholder2);
            given(stakeholderService.getAllStakeholders()).willReturn(allStakeholderDtos);

            // then
            mvc.perform(get(buildGetStakeholdersUri())
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
            var allStakeholderDtos = new ArrayList<StakeholderDto>();
            for (int i = 0; i < value; i++) {
                // given
                var stakeholderDto = createDummyStakeholderDto();
                allStakeholderDtos.add(stakeholderDto);
            }
            // when
            given(stakeholderService.getAllStakeholders()).willReturn(allStakeholderDtos);

            // then
            mvc.perform(get(buildGetStakeholdersUri())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(value)));
        }
    }
}
