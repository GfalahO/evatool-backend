package com.evatool.impact.persistence.event.stakeholder.update;

import com.evatool.impact.persistence.TestDataGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class StakeholderUpdatedEventMockTests {
    @Autowired
    private StakeholderUpdatedEventPublisher publisher;

    @MockBean
    private StakeholderUpdatedEventListener listener;

    @Test
    public void testOnApplicationEvent_ReceivesPublishedEventOnce() {
        // given
        var stakeholder = TestDataGenerator.getStakeholder();

        // when
        publisher.onStakeholderUpdated(stakeholder);

        // then
        verify(listener, times(1)).onApplicationEvent(any(StakeholderUpdatedEvent.class));
    }
}
