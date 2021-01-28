package com.evatool.impact.persistence.event.stakeholder;

import com.evatool.impact.persistence.repository.StakeholderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.evatool.impact.persistence.TestDataGenerator.getStakeholder;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class StakeholderDeletedEventTest {
    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Autowired
    private StakeholderDeletedEventPublisher publisher;

    @Autowired
    private StakeholderDeletedEventListener listener;

    @Test
    public void testOnApplicationEvent_PublishEvent_ReturnNull() throws InterruptedException {
        // given
        var stakeholder = getStakeholder();
        stakeholderRepository.save(stakeholder);

        // when
        publisher.onStakeholderDeleted(stakeholder);
        Thread.sleep(TestSettings.WAIT_MILLIS_FOR_ASYNC_EVENT);
        var found = stakeholderRepository.findById(stakeholder.getId()).orElse(null);

        // then
        assertThat(found).isNull();
    }
}
