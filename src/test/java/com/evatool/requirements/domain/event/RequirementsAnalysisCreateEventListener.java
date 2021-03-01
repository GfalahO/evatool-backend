package com.evatool.requirements.domain.event;

import com.evatool.requirements.events.listener.RequirementEventListener;
import com.evatool.requirements.repository.RequirementAnalysisRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(profiles = "non-async")
public class RequirementsAnalysisCreateEventListener {

    @Autowired
    private RequirementAnalysisRepository requirementAnalysisRepository;

    @Autowired
    private RequirementEventListener requirementEventListener;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Test
    void testOnApplicationEvent_PublishEvent_DimensionCreated() {
        /*
        // given
        UUID id = UUID.randomUUID();
        String  title = "name";
        String json = String.format("{\"id\":\"%s\",\"title\":\"%s\"}", id.toString(), title);

        // when
        AnalysisCreatedEvent analysisCreatedEvent = new AnalysisCreatedEvent(json);
        applicationEventPublisher.publishEvent(analysisCreatedEvent);

        // then
        Optional<RequirementsAnalysis> createdByEvent = requirementAnalysisRepository.findById(id);
        assertThat(createdByEvent).isPresent();
        assertThat(createdByEvent.get().getId()).isEqualTo(id);

         */
    }


    @Test
    void testOnApplicationEvent_DimensionAlreadyExists_ThrowEventEntityAlreadyExistsException() {

        /*
        // given
        UUID id = UUID.randomUUID();
        String title = "title";
        String json = String.format("{\"id\":\"%s\",\"title\":\"%s\"}", id.toString(), title);

        RequirementsAnalysis requirementsAnalysis;

        try {
            var jsonObject = new JSONObject(json);
            requirementsAnalysis = new RequirementsAnalysis();
            requirementsAnalysis.setId(UUID.fromString(jsonObject.getString("id")));
        } catch (JSONException jex) {
            throw new InvalidEventPayloadException(json, jex);
        }

        requirementAnalysisRepository.save(requirementsAnalysis);

        // when
        AnalysisCreatedEvent analysisCreatedEvent = new AnalysisCreatedEvent(json);

        // then
        assertThatExceptionOfType(EventEntityAlreadyExistsException.class).isThrownBy(() -> applicationEventPublisher.publishEvent(analysisCreatedEvent));
*/
    }
}
