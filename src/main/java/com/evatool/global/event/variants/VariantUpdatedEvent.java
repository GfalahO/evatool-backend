package com.evatool.global.event.variants;

import com.evatool.variants.entities.Variant;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class VariantUpdatedEvent extends ApplicationEvent {
    private String variantJson;

    public VariantUpdatedEvent(Object source, String variantJson) {
        super(source);
        this.variantJson = variantJson;
    }
}
