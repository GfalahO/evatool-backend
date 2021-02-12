package com.evatool.requirements.events.listener;

import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.entity.RequirementsVariants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.spi.mapper.GsonMappingProvider;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class RequirementEventListener {

    @EventListener
    @Async
    public void variantCreated(ApplicationEvent event) {
        try {
            JSONObject jsonObject = new JSONObject((event.getSource()).toString());
        } catch (JSONException err) {

        }
    }

    @EventListener
    @Async
    public void variantUpdated(ApplicationEvent event) {
        try {
            JSONObject jsonObject = new JSONObject((event.getSource()).toString());
        } catch (JSONException err) {

        }
    }

    @EventListener
    @Async
    public void variantDeleted(ApplicationEvent event) {
        try {
            JSONObject jsonObject = new JSONObject((event.getSource()).toString());
        } catch (JSONException err) {

        }
    }

    @EventListener
    @Async
    public void impactCreated(ApplicationEvent event) {
        try {
            JSONObject jsonObject = new JSONObject((event.getSource()).toString());
        }catch (JSONException err){

        }
    }

    @EventListener
    @Async
    public void impactUpdated(ApplicationEvent event) {
        try {
            JSONObject jsonObject = new JSONObject((event.getSource()).toString());
        }catch (JSONException err){

        }
    }

    @EventListener
    @Async
    public void impactDeleted(ApplicationEvent event) {
        try {
            JSONObject jsonObject = new JSONObject((event.getSource()).toString());
        }catch (JSONException err){

        }
    }


}
