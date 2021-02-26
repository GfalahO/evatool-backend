package com.evatool.variants.events;

import com.evatool.global.event.variants.VariantUpdatedEvent;
import com.evatool.variants.entities.Variant;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class VariantUpdatedEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishVariantUpdatedEvent(Variant variant){
        Gson gson = new Gson();
        String variantJson = gson.toJson(variant);

        VariantUpdatedEvent variantUpdatedEvent = new VariantUpdatedEvent(this, variantJson);
        applicationEventPublisher.publishEvent(variantUpdatedEvent);
    }

}