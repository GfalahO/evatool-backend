package com.evatool.variants.events.listener;

import com.evatool.global.event.analysis.AnalysisDeletedEvent;
import com.evatool.global.event.stakeholder.StakeholderCreatedEvent;
import com.evatool.global.event.stakeholder.StakeholderDeletedEvent;
import com.evatool.variants.entities.VariantsStakeholder;
import com.evatool.variants.repositories.VariantsStakeholderRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class VariantStakeholderDeletedEventListener implements ApplicationListener<StakeholderDeletedEvent> {
    @Autowired
    VariantsStakeholderRepository variantsStakeholderRepository;
    Gson gson = new Gson();

    @Override
    public void onApplicationEvent(StakeholderDeletedEvent stakeholderDeletedEvent) {
        VariantsStakeholder variantsStakeholder = gson.fromJson(stakeholderDeletedEvent.getJsonPayload(), VariantsStakeholder.class);
        variantsStakeholderRepository.delete(variantsStakeholder);
    }
}
