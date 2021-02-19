package com.evatool.impact.domain.event.stakeholder;

import com.evatool.global.event.stakeholder.StakeholderCreatedEvent;
import com.evatool.impact.application.json.mapper.ImpactStakeholderJsonMapper;
import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

public class ImpactStakeholderDeletedEventListener implements ApplicationListener<StakeholderCreatedEvent> { // TODO change event!

    private static final Logger logger = LoggerFactory.getLogger(ImpactStakeholderDeletedEventListener.class);

    private final ImpactStakeholderRepository stakeholderRepository;

    public ImpactStakeholderDeletedEventListener(ImpactStakeholderRepository stakeholderRepository) {
        this.stakeholderRepository = stakeholderRepository;
    }

    @Override
    public void onApplicationEvent(final StakeholderCreatedEvent event) {
        logger.info("Event received");
        var jsonPayload = event.getJsonPayload();
        var stakeholder = ImpactStakeholderJsonMapper.fromJson(jsonPayload);
        stakeholderRepository.delete(stakeholder);
    }
}
