package com.evatool.impact.domain.event;

import com.evatool.global.event.stakeholder.StakeholderCreatedEvent;
import com.evatool.global.event.stakeholder.StakeholderDeletedEvent;
import com.evatool.global.event.stakeholder.StakeholderUpdatedEvent;
import com.evatool.impact.common.exception.EventEntityAlreadyExistsException;
import com.evatool.impact.common.exception.EventEntityDoesNotExistException;
import com.evatool.impact.domain.entity.ImpactStakeholder;
import com.evatool.impact.domain.event.json.mapper.ImpactStakeholderJsonMapper;
import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ImpactStakeholderEventListener {

    private static final Logger logger = LoggerFactory.getLogger(ImpactStakeholderEventListener.class);

    private final ImpactStakeholderRepository stakeholderRepository;

    public ImpactStakeholderEventListener(ImpactStakeholderRepository stakeholderRepository) {
        this.stakeholderRepository = stakeholderRepository;
    }

    @EventListener
    public void onStakeholderCreatedEvent(final StakeholderCreatedEvent event) {
        logger.info("Stakeholder created event received");
        var jsonPayload = event.getJsonPayload();
        var stakeholder = ImpactStakeholderJsonMapper.fromJson(jsonPayload);
        if (stakeholderRepository.existsById(stakeholder.getId())) {
            throw new EventEntityAlreadyExistsException(ImpactStakeholder.class.getSimpleName());
        }
        stakeholderRepository.save(stakeholder);
        logger.info("Stakeholder created event successfully processed");
    }

    @EventListener
    public void onStakeholderDeletedEvent(final StakeholderDeletedEvent event) {
        logger.info("Stakeholder deleted event received");
        var jsonPayload = event.getJsonPayload();
        var stakeholder = ImpactStakeholderJsonMapper.fromJson(jsonPayload);
        if (!stakeholderRepository.existsById(stakeholder.getId())) {
            throw new EventEntityDoesNotExistException(ImpactStakeholder.class.getSimpleName());
        }
        stakeholderRepository.delete(stakeholder);
        logger.info("Stakeholder deleted event successfully processed");
    }

    @EventListener
    public void onStakeholderUpdatedEvent(final StakeholderUpdatedEvent event) {
        logger.info("Stakeholder updated event received");
        var jsonPayload = event.getJsonPayload();
        var stakeholder = ImpactStakeholderJsonMapper.fromJson(jsonPayload);
        if (!stakeholderRepository.existsById(stakeholder.getId())) {
            throw new EventEntityDoesNotExistException(ImpactStakeholder.class.getSimpleName());
        }
        stakeholderRepository.save(stakeholder);
        logger.info("Stakeholder updated event successfully processed");
    }
}
