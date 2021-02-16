package com.evatool.impact.domain.event.stakeholder;

import com.evatool.global.event.stakeholder.StakeholderCreatedEvent;
import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StakeholderCreatedEventListener implements ApplicationListener<StakeholderCreatedEvent> {

    private ImpactStakeholderRepository stakeholderRepository;

    public StakeholderCreatedEventListener(ImpactStakeholderRepository stakeholderRepository) {
        this.stakeholderRepository = stakeholderRepository;
    }

    @Override
    public void onApplicationEvent(final StakeholderCreatedEvent event) {
        var payload = event.getJsonPayload();
        System.out.println("StakeholderCreatedEvent Received. Payload: " + payload);
        //stakeholderRepository.save(stakeholder);
    }
}
