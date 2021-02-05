package com.evatool.impact.domain.event.stakeholder;

import com.evatool.impact.TestSettings;
import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import org.awaitility.core.ConditionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static com.evatool.impact.TestDataGenerator.getStakeholder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@SpringBootTest
public class ImpactStakeholderUpdatedEventTest {
    public static final ConditionFactory WAIT = await()
            .atMost(Duration.ofMillis(TestSettings.WAIT_MILLIS_FOR_ASYNC_EVENT))
            .pollInterval(Duration.ofMillis(TestSettings.WAIT_MILLIS_FOR_ASYNC_EVENT_POLL));

    @Autowired
    private ImpactStakeholderRepository stakeholderRepository;

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
        String newName = "new_name";
        stakeholder.setName(newName);
        stakeholderUpdatedEventPublisher.onStakeholderUpdated(stakeholder);

        // then
        WAIT.untilAsserted(() -> {
            var found = stakeholderRepository.findById(stakeholder.getId()).orElse(null);
            assertThat(found).isNotNull();
            assertThat(found.getName()).isEqualTo(newName);
        });
    }
}
