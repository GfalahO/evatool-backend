package com.evatool.impact.domain.event.stakeholder;

import com.evatool.impact.domain.event.TestEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;

import static com.evatool.impact.common.TestDataGenerator.createDummyStakeholder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles(profiles = "non-async")
public class ImpactStakeholderInsertedEventMockListenerTest {
    @Autowired
    private StakeholderInsertedEventPublisher stakeholderInsertedEventPublisher;

    @MockBean
    private StakeholderInsertedEventListener stakeholderInsertedEventListener;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Test
    public void testOnApplicationEvent_PublishEvent_ReceivePublishedEvent() throws InterruptedException {
        // given
        var stakeholder = createDummyStakeholder();

        // when
        stakeholderInsertedEventPublisher.onStakeholderInserted(stakeholder);

        // then
        verify(stakeholderInsertedEventListener, times(1)).onApplicationEvent(any(StakeholderInsertedEvent.class));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    public void testOnApplicationEvent_PublishEvents_ReceivePublishedEvents(int value) throws InterruptedException {
        for (int i = 0; i < value; i++) {
            // given
            var stakeholder = createDummyStakeholder();

            // when
            stakeholderInsertedEventPublisher.onStakeholderInserted(stakeholder);
        }

        // then
        verify(stakeholderInsertedEventListener, times(value)).onApplicationEvent(any(StakeholderInsertedEvent.class));
    }

    @Test
    public void testOnApplicationEvent_PublishWrongEvent_DoNotReceivePublishedEvent() throws InterruptedException {
        // given
        var stakeholder = createDummyStakeholder();

        // when
        applicationEventPublisher.publishEvent(new TestEvent(this));

        // then
        verify(stakeholderInsertedEventListener, times(0)).onApplicationEvent(any(StakeholderInsertedEvent.class));
    }
}
