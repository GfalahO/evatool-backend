package com.evatool.impact.domain.event.stakeholder;

import com.evatool.impact.common.TestSettings;
import com.evatool.impact.domain.event.TestEvent;
import org.awaitility.core.ConditionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;

import java.time.Duration;

import static com.evatool.impact.common.TestDataGenerator.getStakeholder;
import static org.awaitility.Awaitility.await;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ImpactStakeholderUpdatedEventMockListenerTest {
    public static final ConditionFactory WAIT = await()
            .atMost(Duration.ofMillis(TestSettings.WAIT_MILLIS_FOR_ASYNC_EVENT))
            .pollInterval(Duration.ofMillis(TestSettings.WAIT_MILLIS_FOR_ASYNC_EVENT_POLL));

    @Autowired
    private StakeholderUpdatedEventPublisher stakeholderUpdatedEventPublisher;

    @MockBean
    private StakeholderUpdatedEventListener stakeholderUpdatedEventListener;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Test
    public void testOnApplicationEvent_PublishEvent_ReceivePublishedEvent() throws InterruptedException {
        // given
        var stakeholder = getStakeholder();

        // when
        stakeholderUpdatedEventPublisher.onStakeholderUpdated(stakeholder);

        // then
        WAIT.untilAsserted(() -> {
            verify(stakeholderUpdatedEventListener, times(1)).onApplicationEvent(any(StakeholderUpdatedEvent.class));
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    public void testOnApplicationEvent_PublishEvents_ReceivePublishedEvents(int value) throws InterruptedException {
        for (int i = 0; i < value; i++) {
            // given
            var stakeholder = getStakeholder();

            // when
            stakeholderUpdatedEventPublisher.onStakeholderUpdated(stakeholder);
        }

        // then
        WAIT.untilAsserted(() -> {
            verify(stakeholderUpdatedEventListener, times(value)).onApplicationEvent(any(StakeholderUpdatedEvent.class));
        });
    }

    @Test
    public void testOnApplicationEvent_PublishWrongEvent_DoNotReceivePublishedEvent() throws InterruptedException {
        // given
        var stakeholder = getStakeholder();

        // when
        applicationEventPublisher.publishEvent(new TestEvent(this));

        // then
        WAIT.untilAsserted(() -> {
            verify(stakeholderUpdatedEventListener, times(0)).onApplicationEvent(any(StakeholderUpdatedEvent.class));
        });
    }
}
