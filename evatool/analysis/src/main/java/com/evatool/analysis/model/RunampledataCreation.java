package com.evatool.analysis.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class RunampledataCreation implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private Sampledata userSampledata;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        userSampledata.creatUser();
        userSampledata.creatStakeholder();
        userSampledata.creatAnalysis();
        userSampledata.loadUser();

    }
}
