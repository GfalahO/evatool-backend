package com.evatool.variants.events.listener;

import com.evatool.global.event.analysis.AnalysisDeletedEvent;
import com.evatool.global.event.requirements.RequirementDeletedEvent;
import com.evatool.variants.entities.VariantsRequirement;
import com.evatool.variants.repositories.VariantsRequirementRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class VariantRequirementDeletedEventListener implements ApplicationListener<RequirementDeletedEvent> {
    @Autowired
    VariantsRequirementRepository variantsRequirementRepository;
    Gson gson = new Gson();

    @Override
    public void onApplicationEvent(RequirementDeletedEvent requirementDeletedEvent) {
        VariantsRequirement variantsRequirement = gson.fromJson(requirementDeletedEvent.getJsonPayload(), VariantsRequirement.class);
        variantsRequirementRepository.delete(variantsRequirement);
    }
}
