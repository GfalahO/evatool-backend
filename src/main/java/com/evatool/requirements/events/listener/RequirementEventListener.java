package com.evatool.requirements.events.listener;

import com.evatool.global.event.dimension.DimensionCreatedEvent;
import com.evatool.global.event.dimension.DimensionDeletedEvent;
import com.evatool.global.event.dimension.DimensionUpdatedEvent;
import com.evatool.global.event.impact.ImpactCreatedEvent;
import com.evatool.global.event.impact.ImpactDeletedEvent;
import com.evatool.global.event.impact.ImpactUpdatedEvent;
import com.evatool.requirements.entity.RequirementDimension;
import com.evatool.requirements.entity.RequirementsImpact;
import com.evatool.requirements.entity.RequirementsVariant;
import com.evatool.requirements.events.RequirementEventPublisher;
import com.evatool.requirements.repository.RequirementDimensionRepository;
import com.evatool.requirements.repository.RequirementsImpactsRepository;
import com.evatool.requirements.repository.RequirementsVariantsRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class RequirementEventListener {

    Logger logger = LoggerFactory.getLogger(RequirementEventListener.class);

    @Autowired
    RequirementsImpactsRepository requirementsImpactsRepository;
    @Autowired
    RequirementsVariantsRepository requirementsVariantsRepository;
    @Autowired
    RequirementDimensionRepository requirementDimensionRepository;


    @EventListener
    @Async
    public void impactCreated(ImpactCreatedEvent event) {
        logger.info("impact created event ");
        logger.debug("Event " + event.getSource() + " With Payload: " + event.getSource().toString() );

        requirementsImpactsRepository.save(RequirementsImpact.fromJson(event.getJsonPayload()));
    }

    @EventListener
    @Async
    public void impactUpdated(ImpactUpdatedEvent event) {
        logger.info("Impact updated event");
        logger.debug("Event " + event.getSource() + " With Payload: " + event.getSource().toString() );
        requirementsImpactsRepository.save(RequirementsImpact.fromJson(event.getJsonPayload()));
    }

    @EventListener
    @Async
    public void impactDeleted(ImpactDeletedEvent event) {
        logger.info("Impact deleted event");
        logger.debug("Event " + event.getSource() + " With Payload: " + event.getSource().toString() );
        requirementsImpactsRepository.delete(RequirementsImpact.fromJson(event.getJsonPayload()));
    }
    @EventListener
    @Async
    public void dimensionCreated(DimensionCreatedEvent event) {
        logger.info("dimension created event");
        logger.debug("Event " + event.getSource() + " With Payload: " + event.getSource().toString() );
        requirementDimensionRepository.save(RequirementDimension.fromJson(event.getJsonPayload()));
    }

    @EventListener
    @Async
    public void dimensionUpdated(DimensionUpdatedEvent event) {
        logger.info("dimension updated event");
        logger.debug("Event " + event.getSource() + " With Payload: " + event.getSource().toString() );
        requirementDimensionRepository.save(RequirementDimension.fromJson(event.getJsonPayload()));
    }

    @EventListener
    @Async
    public void dimensionDeleted(DimensionDeletedEvent event) {
        logger.info("dimension deleted event");
        logger.debug("Event " + event.getSource() + " With Payload: " + event.getSource().toString() );
        requirementDimensionRepository.delete(RequirementDimension.fromJson(event.getJsonPayload()));
    }
    //events do not exist at the moment

/**
 @EventListener
 @Async
    public void variantsCreated(ApplicationEvent event){
    logger.info("dimension deleted event");
    logger.debug("Event " + event.getSource() + " With Payload: " + event.getSource().toString() );
    requirementsVariantsRepository.save(RequirementsVariant.fromJson(event.getJsonPayload()));
    }
 @EventListener
 @Async
    public void variantsUpdated (ApplicationEvent event){
    logger.info("dimension deleted event");
    logger.debug("Event " + event.getSource() + " With Payload: " + event.getSource().toString() );
    requirementsVariantsRepository.save(RequirementsVariant.fromJson(event.getJsonPayload()));
    }
 @EventListener
 @Async
    public void variantsDeleted(ApplicationEvent event){
    logger.info("dimension deleted event");
    logger.debug("Event " + event.getSource() + " With Payload: " + event.getSource().toString() );
    requirementsVariantsRepository.delete(RequirementsVariant.fromJson(event.getJsonPayload()));
    }

    @EventListener
    @Async
    public void analyseCreated(ApplicationEvent event){
        logger.info("analyse created event");
        logger.debug("Event " + event.getSource() + " With Payload: " + event.getSource() );
    }

    @EventListener
    @Async
    public void analyseDeleted(ApplicationEvent event){
        logger.info("analyse deleted event");
        logger.debug("Event " + event.getSource() + " With Payload: " + event.getSource() );

    }
**/
}
