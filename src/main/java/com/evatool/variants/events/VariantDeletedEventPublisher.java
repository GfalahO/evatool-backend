package com.evatool.variants.events;

import com.evatool.global.event.variants.VariantDeletedEvent;
import com.evatool.variants.entities.Variant;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class VariantDeletedEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishVariantDeletedEvent(Variant variant){
        Gson gson = new Gson();
        String variantJson = gson.toJson(variant);

        VariantDeletedEvent variantDeletedEvent = new VariantDeletedEvent(this, variantJson);
        applicationEventPublisher.publishEvent(variantDeletedEvent);
    }

}