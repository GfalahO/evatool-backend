package com.evatool.impact.domain.event.stakeholder;

import com.evatool.global.event.stakeholder.StakeholderCreatedEvent;
import com.evatool.impact.application.json.mapper.ImpactStakeholderJsonMapper;
import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StakeholderCreatedEventListener implements ApplicationListener<StakeholderCreatedEvent> {

    private final ImpactStakeholderRepository stakeholderRepository;

    public StakeholderCreatedEventListener(ImpactStakeholderRepository stakeholderRepository) {
        this.stakeholderRepository = stakeholderRepository;
    }

    @Override
    public void onApplicationEvent(final StakeholderCreatedEvent event) {
        var jsonPayload = event.getJsonPayload();
        var stakeholder = ImpactStakeholderJsonMapper.fromJson(jsonPayload);
        System.out.println(stakeholder);
        System.out.println(stakeholderRepository.save(stakeholder));
        //stakeholderRepository.save(stakeholder);
        System.out.println(stakeholder.getId());
        System.out.println(stakeholderRepository.findById(stakeholder.getId()).orElse(null));
    }
}
