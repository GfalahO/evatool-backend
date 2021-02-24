package com.evatool.requirements.domain.event;

import com.evatool.global.event.impact.ImpactCreatedEvent;
import com.evatool.global.event.requirements.RequirementCreatedEvent;
import com.evatool.global.event.stakeholder.StakeholderCreatedEvent;
import com.evatool.impact.application.json.mapper.ImpactStakeholderJsonMapper;
import com.evatool.impact.common.exception.EventEntityAlreadyExistsException;
import com.evatool.impact.common.exception.InvalidEventPayloadException;
import com.evatool.impact.domain.entity.Impact;
import com.evatool.impact.domain.entity.ImpactStakeholder;
import com.evatool.impact.domain.repository.ImpactRepository;
import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.entity.RequirementsImpact;
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
    private ApplicationEventPublisher applicationEventPublisher;

    @Test
    void testOnApplicationEvent_PublishEvent_ImpactCreated() {
        // given
        UUID id = UUID.randomUUID();
        String  name = "name";
        String json = String.format("{\"id\":\"%s\",\"name\":\"%s\"}", id.toString(), name);

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
            requirementsImpact.setTitle(jsonObject.getString("title"));
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
