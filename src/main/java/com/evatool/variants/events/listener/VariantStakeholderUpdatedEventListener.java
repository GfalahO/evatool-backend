package com.evatool.variants.events.listener;

import com.evatool.global.event.analysis.AnalysisDeletedEvent;
import com.evatool.global.event.stakeholder.StakeholderDeletedEvent;
import com.evatool.global.event.stakeholder.StakeholderUpdatedEvent;
import com.evatool.variants.entities.VariantsStakeholder;
import com.evatool.variants.repositories.VariantsStakeholderRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class VariantStakeholderUpdatedEventListener implements ApplicationListener<StakeholderUpdatedEvent> {
    @Autowired
    VariantsStakeholderRepository variantsStakeholderRepository;
    Gson gson = new Gson();

    @Override
    public void onApplicationEvent(StakeholderUpdatedEvent stakeholderUpdatedEvent) {
        // TODO Uncomment once weird test cases get solved
//        VariantsStakeholder variantsStakeholder = gson.fromJson(stakeholderUpdatedEvent.getJsonPayload(), VariantsStakeholder.class);
//        variantsStakeholderRepository.save(variantsStakeholder);
    }
}
