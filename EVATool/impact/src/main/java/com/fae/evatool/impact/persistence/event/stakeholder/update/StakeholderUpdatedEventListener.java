package com.fae.evatool.impact.persistence.event.stakeholder.update;

import com.fae.evatool.impact.persistence.repository.StakeholderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StakeholderUpdatedEventListener implements ApplicationListener<StakeholderUpdatedEvent> {
    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Override
    public void onApplicationEvent(final StakeholderUpdatedEvent event) {
        var stakeholder = event.getStakeholder();
        stakeholderRepository.save(stakeholder);
    }
}
