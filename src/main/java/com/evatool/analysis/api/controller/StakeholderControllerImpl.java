package com.evatool.analysis.api.controller;

import antlr.ASTNULLType;
import com.evatool.analysis.api.interfaces.AnalysisController;
import com.evatool.analysis.api.interfaces.StakeholderController;
import com.evatool.analysis.dto.AnalysisDTO;
import com.evatool.analysis.dto.StakeholderDTO;
import com.evatool.analysis.enums.StakeholderLevel;
import com.evatool.analysis.events.AnalysisEventPublisher;
import com.evatool.analysis.model.Analysis;
import com.evatool.analysis.model.AnalysisImpacts;
import com.evatool.analysis.model.Stakeholder;
import com.evatool.analysis.repository.AnalysisImpactRepository;
import com.evatool.analysis.repository.StakeholderRepository;
import com.evatool.analysis.services.StakeholderDTOService;
import com.evatool.global.event.analysis.AnalysisCreatedEvent;
import com.evatool.global.event.analysis.AnalysisDeletedEvent;
import com.evatool.global.event.analysis.AnalysisUpdatedEvent;
import com.evatool.global.event.stakeholder.StakeholderCreatedEvent;
import com.evatool.global.event.stakeholder.StakeholderDeletedEvent;
import com.evatool.impact.common.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class StakeholderControllerImpl implements StakeholderController {

    @Autowired
    private StakeholderRepository stakeholderRepository;

    @Autowired
    private StakeholderDTOService stakeholderDTOService;

    @Autowired
    private AnalysisEventPublisher stakeholderEventPublisher;

    @Autowired
    private AnalysisImpactRepository analysisImpactRepository;

    Logger logger = LoggerFactory.getLogger(StakeholderControllerImpl.class);


    @Override
    public List<EntityModel<StakeholderDTO>> getStakeholderList() {
        logger.info("[GET] /stakeholder");
        List<Stakeholder> stakeholderList = stakeholderRepository.findAll();
        if (stakeholderList.size() == 0){
            return Arrays.asList();
        }
        return generateLinks(stakeholderDTOService.findAll(stakeholderList));
    }

    @Override
    public EntityModel<StakeholderDTO> getStakeholderById(UUID id) {
        logger.info("[GET] /stakeholder/{id}");
        Optional<Stakeholder> stakeholder = stakeholderRepository.findById(id);
        if (stakeholder.isEmpty()){
            throw new EntityNotFoundException(Stakeholder.class, id);
        }
        return generateLinks(stakeholderDTOService.findById(stakeholder.get()));
    }

    @Override
    public EntityModel<StakeholderDTO> addStakeholder(@RequestBody StakeholderDTO stakeholderDTO) {
        logger.info("[POST] /addStakeholder");
        Stakeholder stakeholder = stakeholderRepository.save(stakeholderDTOService.create(stakeholderDTO));
        stakeholderEventPublisher.publishEvent(new StakeholderCreatedEvent(stakeholder.toString()));
        return getStakeholderById(stakeholder.getStakeholderId());
    }

    @Override
    public EntityModel<StakeholderDTO> updateStakeholder(@RequestBody StakeholderDTO stakeholderDTO) {
        logger.info("[PUT] /stakeholder");
        Optional<Stakeholder> stakeholderOptional = stakeholderRepository.findById(stakeholderDTO.getRootEntityID());
        Stakeholder stakeholder  = stakeholderOptional.get();
        stakeholder.setStakeholderName(stakeholderDTO.getStakeholderName());
        stakeholder.setStakeholderLevel(stakeholderDTO.getStakeholderLevel());
        stakeholder.setPriority(stakeholderDTO.getPriority());
        List<AnalysisImpacts> analysisImpactsSet = analysisImpactRepository.findAllById(stakeholderDTO.getImpactsTitles().keySet());
        stakeholderEventPublisher.publishEvent(new AnalysisUpdatedEvent(stakeholder.toString()));
        return getStakeholderById(stakeholderDTO.getRootEntityID());
    }

    @Override
    public ResponseEntity<Void> deleteStakeholder(UUID id) {
        logger.info("[DELETE] /stakeholder/{id}");
        Optional<Stakeholder> stakeholderOptional = stakeholderRepository.findById(id);
        if (stakeholderOptional.isEmpty()){
            throw new EntityNotFoundException(Analysis.class, id);
        }
        Stakeholder stakeholder = stakeholderOptional.get();
        stakeholderRepository.deleteById(id);
        stakeholderEventPublisher.publishEvent(new StakeholderDeletedEvent(stakeholder.toString()));
        return ResponseEntity.ok().build();
    }

    private EntityModel<StakeholderDTO> generateLinks(StakeholderDTO stakeholderDTO){
        EntityModel<StakeholderDTO> stakeholderDTOEntityModel = EntityModel.of(stakeholderDTO);
        stakeholderDTOEntityModel.add(linkTo(methodOn(StakeholderController.class).getStakeholderById(stakeholderDTO.getRootEntityID())).withSelfRel());
        stakeholderDTOEntityModel.add(linkTo(methodOn(StakeholderController.class).deleteStakeholder(stakeholderDTO.getRootEntityID())).withRel("Deleted"));
        stakeholderDTOEntityModel.add(linkTo(methodOn(StakeholderController.class).updateStakeholder(stakeholderDTO)).withRel("update"));
        return stakeholderDTOEntityModel;
    }

    private List<EntityModel<StakeholderDTO>> generateLinks(List<StakeholderDTO> stakeholderDTOList){
        List<EntityModel<StakeholderDTO>> returnList = new ArrayList<>();
        stakeholderDTOList.stream().forEach(e -> returnList.add(generateLinks(e)));
        return returnList;

    }


}
