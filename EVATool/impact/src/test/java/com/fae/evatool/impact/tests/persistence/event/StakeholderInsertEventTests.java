package com.fae.evatool.impact.tests.persistence.event;

import com.fae.evatool.impact.persistence.event.StakeholderInsertEvent;
import com.fae.evatool.impact.persistence.event.StakeholderInsertListener;
import com.fae.evatool.impact.persistence.repository.StakeholderRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

import static com.fae.evatool.impact.tests.persistence.TestDataGenerator.getStakeholder;

@SpringBootTest
public class StakeholderInsertEventTests {
    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private StakeholderInsertListener listener;

    @Test
    public void testOnApplicationEvent_StakeholderIdIsNotNull() {
        // given
        var stakeholder = getStakeholder();
        var stakeholderInsertEvent = new StakeholderInsertEvent(publisher, stakeholder);

        // when
        publisher.publishEvent(stakeholderInsertEvent);

        // then
        Assert.assertNotNull(stakeholder.getId());
    }

    @Test
    public void testOnApplicationEvent_ReturnInsertedStakeholder() {
        // given
        var stakeholder = getStakeholder();
        var stakeholderInsertEvent = new StakeholderInsertEvent(publisher, stakeholder);

        // when
        publisher.publishEvent(stakeholderInsertEvent);
        var found = stakeholderRepository.findById(stakeholder.getId()).orElse(null);

        // then
        Assert.assertNotNull(found);
    }
}
