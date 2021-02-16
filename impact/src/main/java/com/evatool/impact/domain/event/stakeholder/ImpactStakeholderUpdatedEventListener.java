package com.evatool.impact.domain.event.stakeholder;

import com.evatool.global.event.stakeholder.StakeholderCreatedEvent;
import com.evatool.impact.application.json.mapper.ImpactStakeholderJsonMapper;
import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import org.springframework.context.ApplicationListener;

public class ImpactStakeholderUpdatedEventListener implements ApplicationListener<StakeholderCreatedEvent> { // TODO change event!

    private final ImpactStakeholderRepository stakeholderRepository;

    public ImpactStakeholderUpdatedEventListener(ImpactStakeholderRepository stakeholderRepository) {
        this.stakeholderRepository = stakeholderRepository;
    }

    @Override
    public void onApplicationEvent(final StakeholderCreatedEvent event) {
        var jsonPayload = event.getJsonPayload();
        var stakeholder = ImpactStakeholderJsonMapper.fromJson(jsonPayload);
        //stakeholderRepository.save(stakeholder);
    }
}
