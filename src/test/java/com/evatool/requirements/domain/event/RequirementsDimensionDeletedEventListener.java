package com.evatool.requirements.domain.event;

import com.evatool.global.event.dimension.DimensionDeletedEvent;
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
public class RequirementsDimensionDeletedEventListener {

    @Autowired
    private RequirementDimensionRepository requirementDimensionRepository;

    @Autowired
    private RequirementEventListener requirementEventListener;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    @Test
    void testOnApplicationEvent_PublishEvent_DimensionDeleted() {
        // given
        RequirementDimension requirementDimension = new RequirementDimension("Title");
        requirementDimensionRepository.save(requirementDimension);

        String json = String.format("{\"id\":\"%s\",\"title\":\"%s\"}", requirementDimension.getId().toString(), "Title");
        UUID tempId = requirementDimension.getId();

        // when
        DimensionDeletedEvent dimensionDeletedEvent = new DimensionDeletedEvent(applicationEventPublisher, json);
        applicationEventPublisher.publishEvent(dimensionDeletedEvent);

        // then
        Optional<RequirementDimension> optionalRequirementDimension = requirementDimensionRepository.findById(tempId);
        assertThat(optionalRequirementDimension).isNotPresent();
    }

    @Test
    void testOnApplicationEvent_DimensionDoesNotExist_ThrowEventEntityDoesNotExistException() {
        // given
        UUID id = UUID.randomUUID();
        String title = "title";
        String json = String.format("{\"id\":\"%s\",\"title\":\"%s\"}", id.toString(), title);

        // when
        DimensionDeletedEvent dimensionDeletedEvent = new DimensionDeletedEvent(applicationEventPublisher, json);

        // then
        assertThatExceptionOfType(EventEntityDoesNotExistException.class).isThrownBy(() -> requirementEventListener.dimensionDeleted(dimensionDeletedEvent));
    }

}
