package com.evatool.requirements.events.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RequirementEventListener {

    @EventListener
    public void variantCreated(ApplicationEvent event){

        //todo
    }

    @EventListener
    public void variantUpdated(ApplicationEvent event){
        //todo
    }

    @EventListener
    public void variantDeleted(ApplicationEvent event){
        //todo
    }

    @EventListener
    public void impactCreated(ApplicationEvent event){
        ///todo
    }

    @EventListener
    public void impactUpdated(ApplicationEvent event){
        //todo
    }

    @EventListener
    public void impactDeleted(ApplicationEvent event){
        /// TODO
    }


}
