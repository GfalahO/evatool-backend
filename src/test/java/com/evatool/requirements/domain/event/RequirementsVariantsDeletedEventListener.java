package com.evatool.requirements.domain.event;

import com.evatool.global.event.variants.VariantDeletedEvent;
import com.evatool.requirements.entity.RequirementsVariant;
import com.evatool.requirements.error.exceptions.EventEntityDoesNotExistException;
import com.evatool.requirements.events.listener.RequirementEventListener;
import com.evatool.requirements.repository.RequirementsVariantsRepository;
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
public class RequirementsVariantsDeletedEventListener {

    @Autowired
    private RequirementsVariantsRepository requirementsVariantsRepository;

    @Autowired
    private RequirementEventListener requirementEventListener;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Test
    void testOnApplicationEvent_PublishEvent_VariantsDeleted() {
        // given
        RequirementsVariant requirementsVariant = new RequirementsVariant("Title","Description");
        requirementsVariantsRepository.save(requirementsVariant);
        UUID tempId = requirementsVariant.getId();
        String title = "title";
        String description = "description";
        String json = String.format("{\"id\":\"%s\",\"title\":\"%s\",\"description\":\"%s\"}", tempId,title,description);

        // when
        VariantDeletedEvent variantDeletedEvent = new VariantDeletedEvent(applicationEventPublisher, json);
        requirementEventListener.variantsDeleted(variantDeletedEvent);

        // then
        Optional<RequirementsVariant> optionalRequirementsVariant = requirementsVariantsRepository.findById(tempId);
        assertThat(optionalRequirementsVariant).isNotPresent();
    }

    @Test
    void testOnApplicationEvent_ImpactDoesNotExist_ThrowEventEntityDoesNotExistException() {
        // given
        UUID id = UUID.randomUUID();
        String title = "title";
        String description = "description";
        String json = String.format("{\"id\":\"%s\",\"title\":\"%s\",\"description\":\"%s\"}", id,title,description);

        // when
        VariantDeletedEvent variantDeletedEvent = new VariantDeletedEvent(applicationEventPublisher, json);

        // then
        assertThatExceptionOfType(EventEntityDoesNotExistException.class).isThrownBy(() -> requirementEventListener.variantsDeleted(variantDeletedEvent));
    }
}
