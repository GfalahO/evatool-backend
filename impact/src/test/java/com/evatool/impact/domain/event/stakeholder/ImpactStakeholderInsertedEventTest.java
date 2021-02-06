package com.evatool.impact.domain.event.stakeholder;

import com.evatool.impact.common.TestSettings;
import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import org.awaitility.core.ConditionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Duration;

import static com.evatool.impact.common.TestDataGenerator.getStakeholder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@SpringBootTest
@ActiveProfiles(profiles = "non-async")
public class ImpactStakeholderInsertedEventTest {
    @Autowired
    private ImpactStakeholderRepository stakeholderRepository;

    @Autowired
    private StakeholderInsertedEventPublisher stakeholderInsertedEventPublisher;

    @Autowired
    private StakeholderInsertedEventListener stakeholderInsertedEventListener;

    @Test
    public void testOnApplicationEvent_PublishEvent_StakeholderIdIsNotNull() throws InterruptedException {
        // given
        var stakeholder = getStakeholder();

        // when
        stakeholderInsertedEventPublisher.onStakeholderInserted(stakeholder);

        // then
        assertThat(stakeholder.getId()).isNotNull();
    }

    @Test
    public void testOnApplicationEvent_PublishEvent_ReturnInsertedStakeholder() throws InterruptedException {
        // given
        var stakeholder = getStakeholder();

        // when
        stakeholderInsertedEventPublisher.onStakeholderInserted(stakeholder);

        // then
        assertThat(stakeholder.getId()).isNotNull();
    }
}
