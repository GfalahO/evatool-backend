package com.evatool.impact.domain.event.stakeholder;

import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.evatool.impact.common.TestDataGenerator.createDummyStakeholder;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles(profiles = "non-async")
public class ImpactStakeholderDeletedEventTest {
    @Autowired
    private ImpactStakeholderRepository stakeholderRepository;

    @Autowired
    private StakeholderDeletedEventPublisher stakeholderDeletedEventPublisher;

    @Autowired
    private StakeholderDeletedEventListener stakeholderDeletedEventListener;

    @Test
    public void testOnApplicationEvent_PublishEvent_ReturnNull() throws InterruptedException {
        // given
        var stakeholder = createDummyStakeholder();
        stakeholderRepository.save(stakeholder);

        // when
        stakeholderDeletedEventPublisher.onStakeholderDeleted(stakeholder);

        // then
        assertThat(stakeholderRepository.findById(stakeholder.getId()).isEmpty());
    }
}
