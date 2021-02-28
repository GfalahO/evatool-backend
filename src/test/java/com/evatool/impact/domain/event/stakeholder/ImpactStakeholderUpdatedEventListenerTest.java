package com.evatool.impact.domain.event.stakeholder;

import com.evatool.global.event.stakeholder.StakeholderUpdatedEvent;
import com.evatool.impact.common.exception.EventEntityDoesNotExistException;
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
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
@ActiveProfiles(profiles = "non-async")
class ImpactStakeholderUpdatedEventListenerTest {

    @Autowired
    private ImpactStakeholderRepository stakeholderRepository;

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

        var stakeholder = new ImpactStakeholder(id, "old_name");
        stakeholderRepository.save(stakeholder);

        // when
        var stakeholderUpdatedEvent = new StakeholderUpdatedEvent(applicationEventPublisher, json);
        applicationEventPublisher.publishEvent(stakeholderUpdatedEvent);

        // then
        var updatedByEventStakeholder = stakeholderRepository.findById(id);
        assertThat(updatedByEventStakeholder).isPresent();
        assertThat(updatedByEventStakeholder.get().getId()).isEqualTo(id);
        assertThat(updatedByEventStakeholder.get().getName()).isEqualTo(name);
    }

    @Test
    void testOnApplicationEvent_StakeholderDoesNotExists_ThrowEventEntityDoesNotExistException() {
        // given
        var id = UUID.randomUUID();
        var name = "name";
        var json = String.format("{\"id\":\"%s\",\"name\":\"%s\"}", id.toString(), name);

        // when
        var stakeholderUpdatedEvent = new StakeholderUpdatedEvent(applicationEventPublisher, json);

        // then
        assertThatExceptionOfType(EventEntityDoesNotExistException.class).isThrownBy(() -> applicationEventPublisher.publishEvent(stakeholderUpdatedEvent));
    }
}
