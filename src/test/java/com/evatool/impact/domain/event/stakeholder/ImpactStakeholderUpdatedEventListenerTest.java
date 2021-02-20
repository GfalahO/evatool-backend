package com.evatool.impact.domain.event.stakeholder;

import com.evatool.global.event.stakeholder.StakeholderCreatedEvent;
import com.evatool.global.event.stakeholder.StakeholderUpdatedEvent;
import com.evatool.impact.domain.entity.ImpactStakeholder;
import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles(profiles = "non-async")
class ImpactStakeholderUpdatedEventListenerTest {

    @Autowired
    private ImpactStakeholderRepository stakeholderRepository;

    @Autowired
    private ImpactStakeholderUpdatedEventListener impactStakeholderUpdatedEventListener;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @BeforeEach
    void clearData() {
        stakeholderRepository.deleteAll();
    }

    @Test
    void testOnApplicationEvent_PublishEvent_StakeholderUpdated() {
        // given
        var id = UUID.randomUUID();
        var name = "name";
        var json = String.format("{\"id\":\"%s\",\"name\":\"%s\"}", id.toString(), name);

        var stakeholder = new ImpactStakeholder("old_name");
        stakeholder.setId(id);
        stakeholderRepository.save(stakeholder);

        // when
        var stakeholderUpdatedEvent = new StakeholderUpdatedEvent(applicationEventPublisher, json);
        applicationEventPublisher.publishEvent(stakeholderUpdatedEvent);

        // then
        var updatedByEventStakeholder = stakeholderRepository.findById(id).orElse(null);
        assertThat(updatedByEventStakeholder).isNotNull();
        assertThat(updatedByEventStakeholder.getId()).isNotNull();
        assertThat(updatedByEventStakeholder.getId()).isEqualTo(id);
        assertThat(updatedByEventStakeholder.getName()).isEqualTo(name);
    }
}
