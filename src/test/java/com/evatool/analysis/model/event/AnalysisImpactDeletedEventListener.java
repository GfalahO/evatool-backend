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
import com.evatool.analysis.error.exceptions.*;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

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
        String title = "title";
        String json = String.format("{\"id\":\"%s\",\"title\":\"%s\"}", id.toString(), title);

        AnalysisImpacts analysisImpacts = new AnalysisImpacts("Title","Description",10,null);
        analysisImpacts.setId(id);
        analysisImpactRepository.save(analysisImpacts);

        // when
        ImpactDeletedEvent impactDeletedEvent = new ImpactDeletedEvent(applicationEventPublisher, json);
        applicationEventPublisher.publishEvent(impactDeletedEvent);

        // then
        Optional<AnalysisImpacts> optionalAnalysisImpacts = analysisImpactRepository.findById(id);
        assertThat(optionalAnalysisImpacts).isNotPresent();
    }

    @Test
    void testOnApplicationEvent_ImpactDoesNotExist_ThrowEventEntityDoesNotExistException() {
        // given
        UUID id = UUID.randomUUID();
        String title = "title";
        String json = String.format("{\"id\":\"%s\",\"title\":\"%s\"}", id.toString(), title);

        // when
        ImpactDeletedEvent impactDeletedEvent = new ImpactDeletedEvent(applicationEventPublisher, json);

        // then
        assertThatExceptionOfType(EventEntityDoesNotExistException.class).isThrownBy(() -> applicationEventPublisher.publishEvent(impactDeletedEvent));
    }
}
