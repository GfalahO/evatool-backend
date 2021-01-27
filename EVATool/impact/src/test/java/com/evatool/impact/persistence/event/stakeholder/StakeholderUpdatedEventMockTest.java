package com.evatool.impact.persistence.event.stakeholder;

import com.evatool.impact.persistence.TestDataGenerator;
import com.evatool.impact.persistence.event.incoming.stakeholder.StakeholderUpdatedEvent;
import com.evatool.impact.persistence.event.incoming.stakeholder.StakeholderUpdatedEventListener;
import com.evatool.impact.persistence.event.incoming.stakeholder.StakeholderUpdatedEventPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class StakeholderUpdatedEventMockTest {
    @Autowired
    private StakeholderUpdatedEventPublisher publisher;

    @MockBean
    private StakeholderUpdatedEventListener listener;

    @Test
    public void testOnApplicationEvent_PublishEvent_ReceivePublishedEventOnce() throws InterruptedException {
        // given
        var stakeholder = TestDataGenerator.getStakeholder();

        // when
        publisher.onStakeholderUpdated(stakeholder);
        Thread.sleep(100);

        // then
        verify(listener, times(1)).onApplicationEvent(any(StakeholderUpdatedEvent.class));
    }
}
