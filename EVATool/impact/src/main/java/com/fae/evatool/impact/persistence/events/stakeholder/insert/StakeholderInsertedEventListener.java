package com.fae.evatool.impact.persistence.events.stakeholder.insert;

import com.fae.evatool.impact.persistence.repository.StakeholderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StakeholderInsertedEventListener implements ApplicationListener<StakeholderInsertedEvent> {
    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Override
    public void onApplicationEvent(final StakeholderInsertedEvent event) {
        var stakeholder = event.getStakeholder();
        stakeholderRepository.save(stakeholder);
        //stakeholderRepository.flush();
    }
}
