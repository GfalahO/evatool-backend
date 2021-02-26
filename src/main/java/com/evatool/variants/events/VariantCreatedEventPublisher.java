package com.evatool.variants.events;

import com.evatool.global.event.variants.VariantCreatedEvent;
import com.evatool.variants.entities.Variant;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class VariantCreatedEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishVariantCreatedEvent(Variant variant){
        Gson gson = new Gson();
        String variantJson = gson.toJson(variant);

        VariantCreatedEvent variantCreatedEvent = new VariantCreatedEvent(this, variantJson);
        applicationEventPublisher.publishEvent(variantCreatedEvent);
    }

}
