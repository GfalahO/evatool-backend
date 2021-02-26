package com.evatool.variants.events.listener;

import com.evatool.global.event.analysis.AnalysisDeletedEvent;
import com.evatool.global.event.requirements.RequirementUpdatedEvent;
import com.evatool.variants.entities.VariantsRequirement;
import com.evatool.variants.repositories.VariantsRequirementRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class VariantRequirementUpdatedEventListener implements ApplicationListener<RequirementUpdatedEvent> {
    @Autowired
    VariantsRequirementRepository variantsRequirementRepository;
    Gson gson = new Gson();

    @Override
    public void onApplicationEvent(RequirementUpdatedEvent requirementUpdatedEvent) {
        VariantsRequirement variantsRequirement = gson.fromJson(requirementUpdatedEvent.getJsonPayload(), VariantsRequirement.class);
        variantsRequirementRepository.save(variantsRequirement);
    }
}
