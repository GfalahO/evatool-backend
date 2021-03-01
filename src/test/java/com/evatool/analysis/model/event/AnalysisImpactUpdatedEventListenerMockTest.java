package com.evatool.analysis.model.event;

import com.evatool.analysis.events.listener.AnalysisEventListener;
import com.evatool.global.event.analysis.AnalysisCreatedEvent;
import com.evatool.global.event.impact.ImpactUpdatedEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles(profiles = "non-async")
public class AnalysisImpactUpdatedEventListenerMockTest {

    @MockBean
    private AnalysisEventListener analysisEventListener;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void testOnApplicationEvent_PublishEvents_ReceivePublishedEvents(int value) {
        for (int i = 0; i < value; i++) {
            UUID uuid = UUID.randomUUID();
            // given
            ImpactUpdatedEvent impactUpdatedEvent = new ImpactUpdatedEvent(applicationEventPublisher,"{id: " + uuid + "}");

            // when
            applicationEventPublisher.publishEvent(impactUpdatedEvent);
        }

        // then
        verify(analysisEventListener, times(value)).impactUpdated(any(ImpactUpdatedEvent.class));
    }

    @Test
    void testOnApplicationEvent_PublishWrongEvent_DoNotReceive() {
        // given
        AnalysisCreatedEvent analysisCreatedEvent = new AnalysisCreatedEvent("TEST");

        // when
        applicationEventPublisher.publishEvent(analysisCreatedEvent);

        // then
        verify(analysisEventListener, times(0)).impactUpdated(any(ImpactUpdatedEvent.class));
    }
}
