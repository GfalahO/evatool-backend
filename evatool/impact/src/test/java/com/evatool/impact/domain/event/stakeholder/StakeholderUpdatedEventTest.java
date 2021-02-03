package com.evatool.impact.domain.event.stakeholder;

import com.evatool.impact.common.TestSettings;
import com.evatool.impact.domain.repository.StakeholderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.evatool.impact.common.TestDataGenerator.getStakeholder;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class StakeholderUpdatedEventTest {
    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Autowired
    private StakeholderUpdatedEventPublisher stakeholderUpdatedEventPublisher;

    @Autowired
    private StakeholderUpdatedEventListener stakeholderUpdatedEventListener;

    @Test
    public void testOnApplicationEvent_PublishEvent_ReturnUpdatedStakeholder() throws InterruptedException {
        // given
        var stakeholder = getStakeholder();
        stakeholderRepository.save(stakeholder);

        // when
        String newName = "newname";
        stakeholder.setName(newName);
        //stakeholderRepository.save(stakeholder);
        stakeholderUpdatedEventPublisher.onStakeholderUpdated(stakeholder);
        Thread.sleep(TestSettings.WAIT_MILLIS_FOR_ASYNC_EVENT);
        var found = stakeholderRepository.findById(stakeholder.getId()).orElse(null);

        // then
        assertThat(found.getName()).isEqualTo(newName);
    }
}
