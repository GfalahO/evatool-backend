package com.evatool.impact.domain.event.stakeholder;

import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StakeholderInsertedEventListener implements ApplicationListener<StakeholderInsertedEvent> {
    @Autowired
    private ImpactStakeholderRepository stakeholderRepository;

    @Override
    public void onApplicationEvent(final StakeholderInsertedEvent event) {
        var stakeholder = event.getStakeholder();
        stakeholderRepository.save(stakeholder);
    }
}
