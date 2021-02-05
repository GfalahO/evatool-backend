package com.evatool.impact.domain.event.stakeholder;

import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StakeholderUpdatedEventListener implements ApplicationListener<StakeholderUpdatedEvent> {
    @Autowired
    private ImpactStakeholderRepository stakeholderRepository;

    @Override
    public void onApplicationEvent(final StakeholderUpdatedEvent event) {
        var stakeholder = event.getStakeholder();
        stakeholderRepository.save(stakeholder);
    }
}
