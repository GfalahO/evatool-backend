package com.evatool.analysis.events.impact;

import com.evatool.analysis.json.AnalysisImpactsJsonMapper;
import com.evatool.analysis.model.AnalysisImpacts;
import com.evatool.analysis.repository.AnalysisImpactRepository;
import com.evatool.global.event.impact.ImpactCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AnalysisImpactCreatedListener implements ApplicationListener<ImpactCreatedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(AnalysisImpactCreatedListener.class);
    private final AnalysisImpactRepository analysisImpactsRepository;

    public AnalysisImpactCreatedListener(AnalysisImpactRepository impactsRepository) {
        this.analysisImpactsRepository = impactsRepository;
    }

    @Override
    public void onApplicationEvent(final ImpactCreatedEvent event) {
        /**logger.info("Event received");
        AnalysisImpacts impact = AnalysisImpactsJsonMapper.fromJson(event.getJsonPayload()); // Mapper muss bezüglich der impacts angepasst werden
        if (analysisImpactsRepository.existsById(impact.getId())) {
            //throw new EventEntityAlreadyExistsException();
            logger.error("The entity transmitted in the event already exist.");
        }
        analysisImpactsRepository.save(impact);
        logger.info("Event successfully processed");*/
    }
}
