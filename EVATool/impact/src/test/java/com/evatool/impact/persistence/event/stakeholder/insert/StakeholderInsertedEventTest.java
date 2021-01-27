package com.evatool.impact.persistence.event.stakeholder.insert;

import com.evatool.impact.persistence.TestDataGenerator;
import com.evatool.impact.persistence.repository.StakeholderRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StakeholderInsertedEventTest {
    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Autowired
    private StakeholderInsertedEventPublisher publisher;

    @Autowired
    private StakeholderInsertedEventListener listener;

    @Test
    public void testOnApplicationEvent_PublishEvent_StakeholderIdIsNotNull() {
        // given
        var stakeholder = TestDataGenerator.getStakeholder();

        // when
        publisher.onStakeholderInserted(stakeholder);

        // then
        Assert.assertNotNull(stakeholder.getId());
    }

    @Test
    public void testOnApplicationEvent_PublishEvent_ReturnInsertedStakeholder() {
        // given
        var stakeholder = TestDataGenerator.getStakeholder();

        // when
        publisher.onStakeholderInserted(stakeholder);
        var found = stakeholderRepository.findById(stakeholder.getId()).orElse(null);

        // then
        Assert.assertNotNull(found);
    }
}
