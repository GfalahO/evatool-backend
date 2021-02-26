package com.evatool.requirements.domain.event;

import com.evatool.global.event.dimension.DimensionUpdatedEvent;
import com.evatool.requirements.entity.RequirementDimension;
import com.evatool.requirements.error.exceptions.EventEntityDoesNotExistException;
import com.evatool.requirements.events.listener.RequirementEventListener;
import com.evatool.requirements.repository.RequirementDimensionRepository;
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
public class RequirementDimensionUpdateEventListener {

    @Autowired
    private RequirementDimensionRepository requirementDimensionRepository;

    @Autowired
    private RequirementEventListener requirementEventListener;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Test
    void testOnApplicationEvent_PublishEvent_DimensionUpdated() {
        // given
        UUID id = UUID.randomUUID();
        String  title = "title";
        String json = String.format("{\"id\":\"%s\",\"title\":\"%s\"}", id.toString(), title);

        RequirementDimension requirementsImpact = new RequirementDimension("Title");
        requirementsImpact.setId(id);
        requirementDimensionRepository.save(requirementsImpact);

        // when
        DimensionUpdatedEvent dimensionUpdatedEvent = new DimensionUpdatedEvent(applicationEventPublisher, json);
        applicationEventPublisher.publishEvent(dimensionUpdatedEvent);

        // then
        Optional<RequirementDimension> optionalRequirementDimension = requirementDimensionRepository.findById(id);
        assertThat(optionalRequirementDimension).isPresent();
        assertThat(optionalRequirementDimension.get().getId()).isEqualTo(id);
        assertThat(optionalRequirementDimension.get().getTitle()).isEqualTo(title);
    }

    @Test
    void testOnApplicationEvent_DimensionDoesNotExists_ThrowEventEntityDoesNotExistException() {

        // given
        UUID id = UUID.randomUUID();
        String  title = "name";
        String json = String.format("{\"id\":\"%s\",\"title\":\"%s\"}", id.toString(), title);

        // when
        DimensionUpdatedEvent dimensionUpdatedEvent = new DimensionUpdatedEvent(applicationEventPublisher, json);

        // then
        assertThatExceptionOfType(EventEntityDoesNotExistException.class).isThrownBy(() -> applicationEventPublisher.publishEvent(dimensionUpdatedEvent));

    }

}
