package com.fae.evatool.impact.tests.persistence.event;

import com.fae.evatool.impact.persistence.events.stakeholder.insert.StakeholderInsertedEventListener;
import com.fae.evatool.impact.persistence.events.stakeholder.insert.StakeholderInsertedEventPublisher;
import com.fae.evatool.impact.persistence.repository.StakeholderRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.fae.evatool.impact.tests.persistence.TestDataGenerator.getStakeholder;

@SpringBootTest
public class StakeholderInsertedEventTests {
    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Autowired
    private StakeholderInsertedEventPublisher publisher;

    @Autowired
    private StakeholderInsertedEventListener listener;

    @Test
    public void testOnApplicationEvent_StakeholderIdIsNotNull() {
        // given
        var stakeholder = getStakeholder();

        // when
        publisher.onStakeholderInserted(stakeholder);

        // then
        Assert.assertNotNull(stakeholder.getId());
    }

    @Test
    public void testOnApplicationEvent_ReturnInsertedStakeholder() {
        // given
        var stakeholder = getStakeholder();

        // when
        publisher.onStakeholderInserted(stakeholder);
        var found = stakeholderRepository.findById(stakeholder.getId()).orElse(null);

        // then
        Assert.assertNotNull(found);
    }
}
