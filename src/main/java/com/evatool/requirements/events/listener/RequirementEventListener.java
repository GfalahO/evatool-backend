package com.evatool.requirements.events.listener;

import com.evatool.global.event.dimension.DimensionCreatedEvent;
import com.evatool.global.event.dimension.DimensionDeletedEvent;
import com.evatool.global.event.dimension.DimensionUpdatedEvent;
import com.evatool.global.event.impact.ImpactCreatedEvent;
import com.evatool.global.event.impact.ImpactDeletedEvent;
import com.evatool.global.event.impact.ImpactUpdatedEvent;
import com.evatool.requirements.entity.RequirementDimension;
import com.evatool.requirements.entity.RequirementsImpact;
import com.evatool.requirements.error.exceptions.EventEntityAlreadyExistsException;
import com.evatool.requirements.error.exceptions.EventEntityDoesNotExistException;
import com.evatool.requirements.repository.RequirementAnalysisRepository;
import com.evatool.requirements.repository.RequirementDimensionRepository;
import com.evatool.requirements.repository.RequirementsImpactsRepository;
import com.evatool.requirements.repository.RequirementsVariantsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class RequirementEventListener {

    final Logger logger = LoggerFactory.getLogger(RequirementEventListener.class);

    @Autowired
    RequirementsImpactsRepository requirementsImpactsRepository;
    @Autowired
    RequirementsVariantsRepository requirementsVariantsRepository;
    @Autowired
    RequirementDimensionRepository requirementDimensionRepository;
    @Autowired
    RequirementAnalysisRepository requirementAnalysisRepository;

    @EventListener
    @Async
    public void impactCreated(ImpactCreatedEvent event) {
        logger.info("impact created event ");
        logger.debug("Event " + event.getClass() + " With Payload: " + event.getSource().toString() );

        if (requirementsImpactsRepository.existsById(RequirementsImpact.fromJson(event.getJsonPayload()).getId())) {
            throw new EventEntityAlreadyExistsException();
        }
        requirementsImpactsRepository.save(RequirementsImpact.fromJson(event.getJsonPayload()));
    }

    @EventListener
    @Async
    public void impactUpdated(ImpactUpdatedEvent event) {
        logger.info("Impact updated event");
        logger.debug("Event " + event.getClass() + " With Payload: " + event.getSource().toString() );
        if (!requirementsImpactsRepository.existsById(RequirementsImpact.fromJson(event.getJsonPayload()).getId())) {
            throw new EventEntityDoesNotExistException();
        }
        requirementsImpactsRepository.save(RequirementsImpact.fromJson(event.getJsonPayload()));
    }

    @EventListener
    @Async
    public void impactDeleted(ImpactDeletedEvent event) {
        logger.info("Impact deleted event");
        logger.debug("Event " + event.getClass() + " With Payload: " + event.getSource().toString() );
        if (!requirementsImpactsRepository.existsById(RequirementsImpact.fromJson(event.getJsonPayload()).getId())) {
            throw new EventEntityDoesNotExistException();
        }
        requirementsImpactsRepository.delete(RequirementsImpact.fromJson(event.getJsonPayload()));
    }
    @EventListener
    @Async
    public void dimensionCreated(DimensionCreatedEvent event) {
        logger.info("dimension created event");
        logger.debug("Event " + event.getClass() + " With Payload: " + event.getSource().toString() );
        if (requirementDimensionRepository.existsById(RequirementDimension.fromJson(event.getJsonPayload()).getId())) {
            throw new EventEntityAlreadyExistsException();
        }
        requirementDimensionRepository.save(RequirementDimension.fromJson(event.getJsonPayload()));
    }

    @EventListener
    @Async
    public void dimensionUpdated(DimensionUpdatedEvent event) {
        logger.info("dimension updated event");
        logger.debug("Event " + event.getClass() + " With Payload: " + event.getSource().toString() );
        if (!requirementDimensionRepository.existsById(RequirementDimension.fromJson(event.getJsonPayload()).getId())) {
            throw new EventEntityDoesNotExistException();
        }
        requirementDimensionRepository.save(RequirementDimension.fromJson(event.getJsonPayload()));
    }

    @EventListener
    @Async
    public void dimensionDeleted(DimensionDeletedEvent event) {
        logger.info("dimension deleted event");
        logger.debug("Event " + event.getClass() + " With Payload: " + event.getSource().toString() );
        if (!requirementDimensionRepository.existsById(RequirementDimension.fromJson(event.getJsonPayload()).getId())) {
            throw new EventEntityDoesNotExistException();
        }
        requirementDimensionRepository.delete(RequirementDimension.fromJson(event.getJsonPayload()));
    }


    //events do not exist at the moment

/*
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
*/
    /*
    @EventListener
    @Async
    public void analyseCreated(AnalysisCreatedEvent event){
        logger.info("analyse created event");
        logger.debug("Event " + event.getClass() + " With Payload: " + event.getMessage() );
        if (requirementDimensionRepository.existsById(RequirementDimension.fromJson(event.getJsonPayload()).getId())) {
            throw new EventEntityAlreadyExistsException();
        }
        requirementAnalysisRepository.save(RequirementsAnalysis.fromJson(event.getMessage()));

    }
    */
/*
    @EventListener
    @Async
    public void analyseDeleted(AnalysisDeletedEvent analysisDeletedEvent){
        logger.info("analyse deleted event");
        logger.debug("Event " + analysisDeletedEvent.getClass() + " With Payload: " + analysisDeletedEvent.getMessage() );


    }
*/
}
