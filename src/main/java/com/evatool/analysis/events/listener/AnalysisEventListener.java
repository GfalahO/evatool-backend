package com.evatool.analysis.events.listener;

import com.evatool.analysis.error.exceptions.EventEntityAlreadyExistsException;
import com.evatool.analysis.error.exceptions.EventEntityDoesNotExistException;
import com.evatool.analysis.model.AnalysisImpacts;
import com.evatool.analysis.repository.AnalysisImpactRepository;
import com.evatool.global.event.impact.ImpactCreatedEvent;
import com.evatool.global.event.impact.ImpactDeletedEvent;
import com.evatool.global.event.impact.ImpactUpdatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;



@Component
public class AnalysisEventListener {

    final Logger logger = LoggerFactory.getLogger(AnalysisEventListener.class);

    @Autowired
    AnalysisImpactRepository analysisImpactRepository;

    @EventListener
    @Async
    public void impactCreated(ImpactCreatedEvent event) {
        logger.info("impact created event ");
        logger.debug("Event " + event.getSource() + " With Payload: " + event.getSource().toString());

        if (analysisImpactRepository.existsById(AnalysisImpacts.fromJson(event.getJsonPayload()).getId())) {
            throw new EventEntityAlreadyExistsException(event.getSource().toString());
        }
        analysisImpactRepository.save(AnalysisImpacts.fromJson(event.getJsonPayload()));
    }

    @EventListener
    @Async
    public void impactUpdated(ImpactUpdatedEvent event) {
        logger.info("Impact updated event");
        logger.debug("Event " + event.getSource() + " With Payload: " + event.getSource().toString());

        if (!analysisImpactRepository.existsById(AnalysisImpacts.fromJson(event.getJsonPayload()).getId())) {
            throw new EventEntityDoesNotExistException();
        }
        analysisImpactRepository.save(AnalysisImpacts.fromJson(event.getJsonPayload()));
    }

    @EventListener
    @Async
    public void impactDeleted(ImpactDeletedEvent event) {
        logger.info("Impact deleted event");
        logger.debug("Event " + event.getSource() + " With Payload: " + event.getSource().toString());

        if (!analysisImpactRepository.existsById(AnalysisImpacts.fromJson(event.getJsonPayload()).getId())) {
            throw new EventEntityDoesNotExistException();
        }
        analysisImpactRepository.delete(AnalysisImpacts.fromJson(event.getJsonPayload()));
    }

}
