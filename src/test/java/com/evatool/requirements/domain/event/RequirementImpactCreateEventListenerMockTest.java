package com.evatool.requirements.domain.event;

import com.evatool.global.event.impact.ImpactCreatedEvent;
import com.evatool.global.event.requirements.RequirementCreatedEvent;
import com.evatool.requirements.events.listener.RequirementEventListener;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles(profiles = "non-async")
class RequirementImpactCreateEventListenerMockTest {

    @MockBean
    private RequirementEventListener requirementEventListener;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void testOnApplicationEvent_PublishEvents_ReceivePublishedEvents(int value) {
        for (int i = 0; i < value; i++) {
            // given
            ImpactCreatedEvent impactCreatedEvent = new ImpactCreatedEvent("","");

            // when
            applicationEventPublisher.publishEvent(impactCreatedEvent);
        }

        // then
        verify(requirementEventListener, times(value)).impactCreated(any(ImpactCreatedEvent.class));
    }

    @Test
    void testOnApplicationEvent_PublishWrongEvent_DoNotReceive() {
        // given
        RequirementCreatedEvent dummy = new RequirementCreatedEvent("TEST");

        // when
        applicationEventPublisher.publishEvent(dummy);

        // then
        verify(requirementEventListener, times(0)).impactCreated(any(ImpactCreatedEvent.class));
    }


}
