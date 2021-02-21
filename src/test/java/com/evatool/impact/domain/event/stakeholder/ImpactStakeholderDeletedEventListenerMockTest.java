package com.evatool.impact.domain.event.stakeholder;

import com.evatool.global.event.stakeholder.StakeholderCreatedEvent;
import com.evatool.impact.domain.event.DummyEvent;
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
class ImpactStakeholderDeletedEventListenerMockTest {

    @MockBean
    private ImpactStakeholderCreatedEventListener impactStakeholderCreatedEventListener;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void testOnApplicationEvent_PublishEvents_ReceivePublishedEvents(int value) {
        for (int i = 0; i < value; i++) {
            // given
            var stakeholderCreatedEvent = new StakeholderCreatedEvent(applicationEventPublisher, "");

            // when
            applicationEventPublisher.publishEvent(stakeholderCreatedEvent);
        }

        // then
        verify(impactStakeholderCreatedEventListener, times(value)).onApplicationEvent(any(StakeholderCreatedEvent.class));
    }

    @Test
    void testOnApplicationEvent_PublishWrongEvent_DoNotReceive() {
        // given
        var dummyEvent = new DummyEvent(applicationEventPublisher);

        // when
        applicationEventPublisher.publishEvent(dummyEvent);

        // then
        verify(impactStakeholderCreatedEventListener, times(0)).onApplicationEvent(any(StakeholderCreatedEvent.class));
    }
}
