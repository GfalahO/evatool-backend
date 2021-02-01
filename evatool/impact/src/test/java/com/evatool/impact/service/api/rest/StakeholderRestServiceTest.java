package com.evatool.impact.service.api.rest;

import com.evatool.impact.common.controller.rest.StakeholderRestController;
import com.evatool.impact.service.impl.rest.StakeholderRestServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

public class StakeholderRestServiceTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private StakeholderRestServiceImpl stakeholderRestServiceImpl;

    @Autowired
    private StakeholderRestController stakeholderRestController;

    @Test
    public void dummyTest() {

    }


    // TODO: Check that exceptions are thrown correctly


}
