package com.evatool.impact.domain.event.stakeholder;

import com.evatool.global.event.stakeholder.StakeholderCreatedEvent;
import com.evatool.impact.application.json.mapper.ImpactStakeholderJsonMapper;
import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StakeholderCreatedEventListener implements ApplicationListener<StakeholderCreatedEvent> {

    private final ImpactStakeholderRepository stakeholderRepository;

    public StakeholderCreatedEventListener(ImpactStakeholderRepository stakeholderRepository) {
        this.stakeholderRepository = stakeholderRepository;
    }

    @SneakyThrows
    @Override
    public void onApplicationEvent(final StakeholderCreatedEvent event) {
        var jsonPayload = event.getJsonPayload();
        var stakeholder = ImpactStakeholderJsonMapper.fromJson(jsonPayload);
        stakeholderRepository.save(stakeholder);
        for (int i = 0; i < 10; i++) {
            Thread.sleep(100);
            System.out.println("LISTENER: " + jsonPayload);
        }
    }
}
