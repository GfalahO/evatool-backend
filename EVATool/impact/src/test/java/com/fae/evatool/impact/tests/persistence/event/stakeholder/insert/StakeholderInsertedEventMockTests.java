package com.fae.evatool.impact.tests.persistence.event.stakeholder.insert;

import com.fae.evatool.impact.persistence.event.stakeholder.insert.StakeholderInsertedEvent;
import com.fae.evatool.impact.persistence.event.stakeholder.insert.StakeholderInsertedEventListener;
import com.fae.evatool.impact.persistence.event.stakeholder.insert.StakeholderInsertedEventPublisher;
import com.fae.evatool.impact.persistence.repository.StakeholderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.fae.evatool.impact.tests.persistence.TestDataGenerator.getStakeholder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class StakeholderInsertedEventMockTests {
    @Autowired
    private StakeholderInsertedEventPublisher publisher;

    @MockBean
    private StakeholderInsertedEventListener listener;

    @Test
    public void testOnApplicationEvent_ReceivesPublishedEventOnce() {
        // given
        var stakeholder = getStakeholder();

        // when
        publisher.onStakeholderInserted(stakeholder);

        // then
        verify(listener, times(1)).onApplicationEvent(any(StakeholderInsertedEvent.class));
    }
}
