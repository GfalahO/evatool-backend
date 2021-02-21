package com.evatool.impact.domain.event.stakeholder;

import com.evatool.global.event.stakeholder.StakeholderDeletedEvent;
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
class ImpactStakeholderCreatedEventListenerMockTest {

    @MockBean
    private ImpactStakeholderDeletedEventListener impactStakeholderDeletedEventListener;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void testOnApplicationEvent_PublishEvents_ReceivePublishedEvents(int value) {
        for (int i = 0; i < value; i++) {
            // given
            var stakeholderDeletedEvent = new StakeholderDeletedEvent(applicationEventPublisher, "");

            // when
            applicationEventPublisher.publishEvent(stakeholderDeletedEvent);
        }

        // then
        verify(impactStakeholderDeletedEventListener, times(value)).onApplicationEvent(any(StakeholderDeletedEvent.class));
    }

    @Test
    void testOnApplicationEvent_PublishWrongEvent_DoNotReceive() {
        // given
        var dummyEvent = new DummyEvent(applicationEventPublisher);

        // when
        applicationEventPublisher.publishEvent(dummyEvent);

        // then
        verify(impactStakeholderDeletedEventListener, times(0)).onApplicationEvent(any(StakeholderDeletedEvent.class));
    }
}
