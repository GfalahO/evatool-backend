package com.evatool.variants.events.listener;

import com.evatool.global.event.analysis.AnalysisCreatedEvent;
import com.evatool.variants.entities.VariantsAnalysis;
import com.evatool.variants.repositories.VariantsAnalysisRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class VariantAnalysisCreatedEventListener implements ApplicationListener<AnalysisCreatedEvent> {
    @Autowired
    VariantsAnalysisRepository variantsAnalysisRepository;
    Gson gson = new Gson();

    @Override
    public void onApplicationEvent(AnalysisCreatedEvent analysisCreatedEvent) {
        VariantsAnalysis variantsAnalysis = gson.fromJson(analysisCreatedEvent.getMessage(), VariantsAnalysis.class);
        variantsAnalysisRepository.save(variantsAnalysis);
    }
}
