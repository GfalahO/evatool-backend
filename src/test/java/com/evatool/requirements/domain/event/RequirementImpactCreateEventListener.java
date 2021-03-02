package com.evatool.requirements.domain.event;

import com.evatool.global.event.impact.ImpactCreatedEvent;
import com.evatool.requirements.entity.RequirementsImpact;
import com.evatool.requirements.error.exceptions.EventEntityAlreadyExistsException;
import com.evatool.requirements.error.exceptions.InvalidEventPayloadException;
import com.evatool.requirements.events.listener.RequirementEventListener;
import com.evatool.requirements.repository.RequirementsImpactsRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
@ActiveProfiles(profiles = "non-async")
public class RequirementImpactCreateEventListener {

    @Autowired
    private RequirementsImpactsRepository requirementsImpactsRepository;

    @Autowired
    private RequirementEventListener requirementEventListener;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Test
    void testOnApplicationEvent_PublishEvent_ImpactCreated() {
        // given
        UUID id = UUID.randomUUID();
        String  title = "name";
        String json = String.format("{\"id\":\"%s\",\"title\":\"%s\"}", id.toString(), title);

        // when
        ImpactCreatedEvent impactCreatedEvent = new ImpactCreatedEvent(applicationEventPublisher, json);
        applicationEventPublisher.publishEvent(impactCreatedEvent);

        // then
        Optional<RequirementsImpact> createdByEvent = requirementsImpactsRepository.findById(id);
        assertThat(createdByEvent).isPresent();
        assertThat(createdByEvent.get().getId()).isEqualTo(id);
    }


    @Test
    void testOnApplicationEvent_ImpactAlreadyExists_ThrowEventEntityAlreadyExistsException() {
        // given
        UUID id = UUID.randomUUID();
        String  name = "name";
        String json = String.format("{\"id\":\"%s\",\"title\":\"%s\"}", id.toString(), name);

        RequirementsImpact requirementsImpact;

        try {
            var jsonObject = new JSONObject(json);
            requirementsImpact = new RequirementsImpact();
            requirementsImpact.setDescription(jsonObject.getString("title"));
            requirementsImpact.setId(UUID.fromString(jsonObject.getString("id")));
        } catch (JSONException jex) {
            throw new InvalidEventPayloadException(json, jex);
        }

        requirementsImpactsRepository.save(requirementsImpact);

        // when
        ImpactCreatedEvent impactCreatedEvent = new ImpactCreatedEvent(applicationEventPublisher, json);

        // then
        assertThatExceptionOfType(EventEntityAlreadyExistsException.class).isThrownBy(() -> applicationEventPublisher.publishEvent(impactCreatedEvent));
    }


}
