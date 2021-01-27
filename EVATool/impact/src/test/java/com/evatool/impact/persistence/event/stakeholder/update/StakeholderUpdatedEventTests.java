package com.evatool.impact.persistence.event.stakeholder.update;

import com.evatool.impact.persistence.repository.StakeholderRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.evatool.impact.persistence.TestDataGenerator.getStakeholder;

@SpringBootTest
public class StakeholderUpdatedEventTests {
    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Autowired
    private StakeholderUpdatedEventPublisher publisher;

    @Autowired
    private StakeholderUpdatedEventListener listener;

    @Test
    public void testOnApplicationEvent_ReturnUpdatedStakeholder() {
        // given
        var stakeholder = getStakeholder();
        stakeholderRepository.save(stakeholder);

        // when
        String newName = "newname";
        stakeholder.setName(newName);
        //stakeholderRepository.save(stakeholder); // Should be mandatory for test to pass. Think of another way of passing the objects.
        publisher.onStakeholderUpdated(stakeholder);
        var found = stakeholderRepository.findById(stakeholder.getId()).orElse(null);

        // then
        Assert.assertEquals(newName, found.getName());
    }
}
