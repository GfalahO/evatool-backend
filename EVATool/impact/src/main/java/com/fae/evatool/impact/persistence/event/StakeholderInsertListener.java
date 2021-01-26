package com.fae.evatool.impact.persistence.event;

import com.fae.evatool.impact.persistence.repository.StakeholderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StakeholderInsertListener implements ApplicationListener<StakeholderInsertEvent> {
    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Override
    public void onApplicationEvent(final StakeholderInsertEvent event) {
        var stakeholder = event.getStakeholder();
        stakeholderRepository.save(stakeholder);
        //stakeholderRepository.flush();
    }
}
