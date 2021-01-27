package com.evatool.impact.persistence.event.incoming.stakeholder.insert;

import com.evatool.impact.persistence.TestDataGenerator;
import com.evatool.impact.persistence.repository.StakeholderRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class StakeholderInsertedEventTest {
    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Autowired
    private StakeholderInsertedEventPublisher publisher;

    @Autowired
    private StakeholderInsertedEventListener listener;

    @Test
    public void testOnApplicationEvent_PublishEvent_StakeholderIdIsNotNull() throws InterruptedException {
        // given
        var stakeholder = TestDataGenerator.getStakeholder();

        // when
        publisher.onStakeholderInserted(stakeholder);
        Thread.sleep(100);

        // then
        assertThat(stakeholder.getId()).isNotNull();
    }

    @Test
    public void testOnApplicationEvent_PublishEvent_ReturnInsertedStakeholder() throws InterruptedException {
        // given
        var stakeholder = TestDataGenerator.getStakeholder();

        // when
        publisher.onStakeholderInserted(stakeholder);
        Thread.sleep(100);
        var found = stakeholderRepository.findById(stakeholder.getId()).orElse(null);

        // then
        assertThat(found).isNotNull();
    }
}
