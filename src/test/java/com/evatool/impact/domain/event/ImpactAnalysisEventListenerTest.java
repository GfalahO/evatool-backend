package com.evatool.impact.domain.event;

import com.evatool.global.event.analysis.AnalysisCreatedEvent;
import com.evatool.global.event.analysis.AnalysisDeletedEvent;
import com.evatool.global.event.analysis.AnalysisUpdatedEvent;
import com.evatool.impact.common.exception.EventEntityAlreadyExistsException;
import com.evatool.impact.common.exception.EventEntityDoesNotExistException;
import com.evatool.impact.domain.entity.ImpactAnalysis;
import com.evatool.impact.domain.event.json.mapper.ImpactAnalysisJsonMapper;
import com.evatool.impact.domain.repository.ImpactAnalysisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
@ActiveProfiles(profiles = "non-async")
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.evatool.impact", "com.evatool.global"})
public class ImpactAnalysisEventListenerTest { // TODO Fix when event is fixed

    @Autowired
    private ImpactAnalysisRepository analysisRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @BeforeEach
    void clearData() {
        analysisRepository.deleteAll();
    }

    @Nested
    class Created {

        @Test
        void testOnAnalysisCreatedEvent_PublishEvent_AnalysisCreated() {
            // given
            var id = UUID.randomUUID();
            var json = String.format("{\"id\":\"%s\"}", id.toString());

            // when
            var analysisCreatedEvent = new AnalysisCreatedEvent(json);
            applicationEventPublisher.publishEvent(analysisCreatedEvent);

            // then
            var createdByEvent = analysisRepository.findById(id);
            assertThat(createdByEvent).isPresent();
            assertThat(createdByEvent.get().getId()).isEqualTo(id);
        }

        @Test
        void testOnAnalysisCreatedEvent_AnalysisAlreadyExists_ThrowEventEntityAlreadyExistsException() {
            // given
            var id = UUID.randomUUID();
            var json = String.format("{\"id\":\"%s\"}", id.toString());

            var analysis = ImpactAnalysisJsonMapper.fromJson(json);
            analysisRepository.save(analysis);

            // when
            var analysisCreatedEvent = new AnalysisCreatedEvent(json);

            // then
            assertThatExceptionOfType(EventEntityAlreadyExistsException.class).isThrownBy(() -> applicationEventPublisher.publishEvent(analysisCreatedEvent));
        }
    }

    @Disabled
    @Nested
    class Deleted {

        @Test
        void testOnAnalysisDeletedEvent_PublishEvent_AnalysisDeleted() {
            // given
            var id = UUID.randomUUID();
            var json = String.format("{\"id\":\"%s\"}", id.toString());

            var analysis = new ImpactAnalysis(id);
            analysisRepository.save(analysis);

            // when
            AnalysisDeletedEvent analysisDeletedEvent = null;//new AnalysisDeletedEvent(json);
            applicationEventPublisher.publishEvent(analysisDeletedEvent);

            // then
            var deletedByEventAnalysis = analysisRepository.findById(id);
            assertThat(deletedByEventAnalysis).isNotPresent();
        }

        @Test
        void testOnAnalysisDeletedEvent_AnalysisDoesNotExist_ThrowEventEntityDoesNotExistException() {
            // given
            var id = UUID.randomUUID();
            var json = String.format("{\"id\":\"%s\"}", id.toString());

            // when
            AnalysisDeletedEvent analysisDeletedEvent = null;//new AnalysisDeletedEvent(json);

            // then
            assertThatExceptionOfType(EventEntityDoesNotExistException.class).isThrownBy(() -> applicationEventPublisher.publishEvent(analysisDeletedEvent));
        }
    }

    @Disabled
    @Nested
    class Updated {

        @Test
        void testOnAnalysisUpdatedEvent_PublishEvent_AnalysisUpdated() {
            // given
            var id = UUID.randomUUID();
            var json = String.format("{\"id\":\"%s\"}", id.toString());

            var analysis = new ImpactAnalysis(id);
            analysisRepository.save(analysis);

            // when
            AnalysisUpdatedEvent analysisUpdatedEvent = null;// new AnalysisUpdatedEvent(json);
            applicationEventPublisher.publishEvent(analysisUpdatedEvent);

            // then
            var updatedByEventAnalysis = analysisRepository.findById(id);
            assertThat(updatedByEventAnalysis).isPresent();
            assertThat(updatedByEventAnalysis.get().getId()).isEqualTo(id);
        }

        @Test
        void testOnAnalysisUpdatedEvent_AnalysisDoesNotExists_ThrowEventEntityDoesNotExistException() {
            // given
            var id = UUID.randomUUID();
            var json = String.format("{\"id\":\"%s\"}", id.toString());

            // when
            AnalysisUpdatedEvent analysisUpdatedEvent = null;// new AnalysisUpdatedEvent(json);

            // then
            assertThatExceptionOfType(EventEntityDoesNotExistException.class).isThrownBy(() -> applicationEventPublisher.publishEvent(analysisUpdatedEvent));
        }
    }
}
