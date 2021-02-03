package com.evatool.impact.domain.event.stakeholder;

import com.evatool.impact.domain.repository.StakeholderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StakeholderDeletedEventListener implements ApplicationListener<StakeholderDeletedEvent> {
    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Override
    public void onApplicationEvent(final StakeholderDeletedEvent event) {
        var stakeholder = event.getStakeholder();
        stakeholderRepository.delete(stakeholder);
    }
}
