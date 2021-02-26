package com.evatool.analysis.model.event;

import com.evatool.analysis.events.listener.AnalysisEventListener;
import com.evatool.analysis.model.AnalysisImpacts;
import com.evatool.analysis.repository.AnalysisImpactRepository;
import com.evatool.global.event.impact.ImpactCreatedEvent;
import com.evatool.global.event.impact.ImpactDeletedEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles(profiles = "non-async")
public class AnalysisImpactDeletedEventListener {

    @Autowired
    private AnalysisImpactRepository analysisImpactRepository;

    @Autowired
    private AnalysisEventListener analysisEventListener;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Test
    void testOnApplicationEvent_PublishEvent_ImpactDeleted() {
        // given
        UUID id = UUID.randomUUID();
        String name = "name";
        String json = String.format("{\"id\":\"%s\",\"name\":\"%s\"}", id.toString(), name);

        // when
        ImpactDeletedEvent impactDeletedEvent = new ImpactDeletedEvent(applicationEventPublisher, json);
        applicationEventPublisher.publishEvent(impactDeletedEvent);

        // then
        Optional<AnalysisImpacts> createdByEvent = analysisImpactRepository.findById(id);
        assertThat(createdByEvent).isPresent();
        assertThat(createdByEvent.get().getId()).isEqualTo(id);
    }
}
