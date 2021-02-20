package com.evatool.impact.domain.event.stakeholder;

import com.evatool.global.event.stakeholder.StakeholderCreatedEvent;
import com.evatool.global.event.stakeholder.StakeholderDeletedEvent;
import com.evatool.impact.domain.entity.ImpactStakeholder;
import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;

import java.util.Locale;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles(profiles = "non-async")
class ImpactStakeholderDeletedEventListenerTest {

    @Autowired
    private ImpactStakeholderRepository stakeholderRepository;

    @Autowired
    private ImpactStakeholderDeletedEventListener impactStakeholderDeletedEventListener;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @BeforeEach
    void clearData() {
        stakeholderRepository.deleteAll();
    }

    @Test
    void testOnApplicationEvent_PublishEvent_StakeholderDeleted() {
        // given
        var id = UUID.randomUUID();
        var name = "name";
        var json = String.format("{\"id\":\"%s\",\"name\":\"%s\"}", id.toString(), name);

        var stakeholder = new ImpactStakeholder(name);
        stakeholder.setId(id);
        stakeholderRepository.save(stakeholder);

        // when
        var stakeholderDeletedEvent = new StakeholderDeletedEvent(applicationEventPublisher, json);
        applicationEventPublisher.publishEvent(stakeholderDeletedEvent);

        // then
        var deletedByEventStakeholder = stakeholderRepository.findById(id).orElse(null);
        assertThat(deletedByEventStakeholder).isNull();
    }
}
