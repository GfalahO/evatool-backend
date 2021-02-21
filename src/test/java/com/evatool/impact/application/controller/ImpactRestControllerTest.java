package com.evatool.impact.application.controller;

import com.evatool.impact.application.service.ImpactService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ImpactRestControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ImpactService impactService;

    @BeforeEach
    public void clearDatabase() {
        impactService.deleteImpacts();
    }


}
