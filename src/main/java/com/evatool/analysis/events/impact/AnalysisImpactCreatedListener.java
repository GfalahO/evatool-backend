package com.evatool.analysis.events.impact;

public class AnalysisImpactCreatedListener /*implements ApplicationListener<ImpactCreatedEvent> */{

   /** private static final Logger logger = LoggerFactory.getLogger(AnalysisCreatedEventListener.class);
    private final AnalysisImpactsRepository analysisImpactsRepository;

    public AnalysisCreatedEventListener(AnalysisImpactsRepository impactsRepository) {
        this.analysisImpactsRepository = impactsRepository;
    }

    @Override
    public void onApplicationEvent(final ImpactCreatedEvent event) {
        logger.info("Event received");
        var stakeholder = AnalysisImpactsJsonMapper.fromJson(event.getJsonPayload()); // Mapper muss ausgefüllt werden
        if (analysisImpactsRepository.existsById(stakeholder.getId())) { // Abfrage in das neue Repository schreiben
            throw new EventEntityAlreadyExistsException();
        }
        analysisImpactsRepository.save(stakeholder);
        logger.info("Event successfully processed");
    }**/
}
