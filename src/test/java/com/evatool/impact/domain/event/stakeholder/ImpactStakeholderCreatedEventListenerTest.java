package com.evatool.impact.domain.event.stakeholder;

import com.evatool.global.event.stakeholder.StakeholderCreatedEvent;
import com.evatool.impact.application.json.mapper.ImpactStakeholderJsonMapper;
import com.evatool.impact.common.exception.EventEntityAlreadyExistsException;
import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
@ActiveProfiles(profiles = "non-async")
class ImpactStakeholderCreatedEventListenerTest {

    @Autowired
    private ImpactStakeholderRepository stakeholderRepository;

    @Autowired
    private ImpactStakeholderCreatedEventListener impactStakeholderCreatedEventListener;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @BeforeEach
    void clearData() {
        stakeholderRepository.deleteAll();
    }

    @Test
    void testOnApplicationEvent_PublishEvent_StakeholderCreated() {
        // given
        var id = UUID.randomUUID();
        var name = "name";
        var json = String.format("{\"id\":\"%s\",\"name\":\"%s\"}", id.toString(), name);

        // when
        var stakeholderCreatedEvent = new StakeholderCreatedEvent(applicationEventPublisher, json);
        applicationEventPublisher.publishEvent(stakeholderCreatedEvent);

        // then
        var createdByEvent = stakeholderRepository.findById(id);
        assertThat(createdByEvent).isPresent();
        assertThat(createdByEvent.get().getId()).isEqualTo(id);
        assertThat(createdByEvent.get().getName()).isEqualTo(name);
    }

    @Test
    void testOnApplicationEvent_StakeholderAlreadyExists_ThrowEventEntityAlreadyExistsException() {
        // given
        var id = UUID.randomUUID();
        var name = "name";
        var json = String.format("{\"id\":\"%s\",\"name\":\"%s\"}", id.toString(), name);

        var stakeholder = ImpactStakeholderJsonMapper.fromJson(json);
        stakeholder = stakeholderRepository.save(stakeholder);

        // when
        var stakeholderCreatedEvent = new StakeholderCreatedEvent(applicationEventPublisher, json);

        // then
        assertThatExceptionOfType(EventEntityAlreadyExistsException.class).isThrownBy(() -> applicationEventPublisher.publishEvent(stakeholderCreatedEvent));
    }
}
