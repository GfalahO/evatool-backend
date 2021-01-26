package com.fae.evatool.impact.tests.persistence.event;

import com.fae.evatool.impact.persistence.event.StakeholderInsertEvent;
import com.fae.evatool.impact.persistence.event.StakeholderInsertListener;
import com.fae.evatool.impact.persistence.event.StakeholderInsertPublisher;
import com.fae.evatool.impact.persistence.repository.StakeholderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;

import static com.fae.evatool.impact.tests.persistence.TestDataGenerator.getStakeholder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class StakeholderInsertEventMockTests {
    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Autowired
    private StakeholderInsertPublisher publisher;

    @MockBean
    private StakeholderInsertListener listener;

    @Test
    public void testOnApplicationEvent_ReceivesPublishedEventOnce() {
        // given
        var stakeholder = getStakeholder();

        // when
        publisher.onStakeholderInserted(stakeholder);

        // then
        verify(listener, times(1)).onApplicationEvent(any(StakeholderInsertEvent.class));
    }
}
