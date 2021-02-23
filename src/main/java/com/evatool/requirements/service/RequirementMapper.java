package com.evatool.requirements.service;

import com.evatool.requirements.controller.RequirementPointController;
import com.evatool.requirements.dto.RequirementDTO;
import com.evatool.requirements.entity.RequirementsImpact;
import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.entity.RequirementPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class RequirementMapper {

    @Autowired
    RequirementPointController requirement_grController;

    public List<RequirementDTO> map(List<Requirement> resultList) {
        List<RequirementDTO> requirementDTOList = new ArrayList<>();
        for(Requirement requirement : resultList){
            requirementDTOList.add(map(requirement));
        }
        return requirementDTOList;
    }

    private RequirementDTO map(Requirement requirement) {
        RequirementDTO requirementDTO = new RequirementDTO();
        requirementDTO.setRequirementTitle(requirement.getTitle());
        requirementDTO.setRootEntityId(requirement.getId());
        requirementDTO.setRequirementDescription(requirement.getDescription());
        requirement.getVariants().forEach(variants->{
            requirementDTO.setVariantsTitle(variants.getTitle());
            requirementDTO.setVariantsUUID(variants.getId());

        });
        Collection<RequirementsImpact> requirementsImpactList = requirement_grController.getRequirement_grByRequirement(requirement.getId());
        requirementsImpactList.forEach(inpacts -> {
            requirementDTO.getImpactTitles().put(inpacts.getId(),inpacts.getTitle());


            requirementDTO.getDimensions().add(inpacts.getRequirementDimension());
            Collection<RequirementPoint> requirement_grCollection = requirement_grController.getRequirement_grByRequirementList(requirement,inpacts);
            //TODO keine Berechnung sondern nur den Wert
            requirementDTO.getRequirementImpactPoints().put(inpacts.getId(),calculatePoints(inpacts,requirement_grCollection));
        });



        return requirementDTO;
    }

    private Integer calculatePoints(RequirementsImpact requirementsImpact, Collection<RequirementPoint> requirement_grCollection) {
        int basicValue = requirementsImpact.getValue();
        int returnValue = requirementsImpact.getValue();

        for(RequirementPoint requirement_gr :requirement_grCollection){
            returnValue+=requirement_gr.getPoints();
        }

        if(basicValue<0 && returnValue>0){
            return new Integer(0);
        }

        return new Integer(returnValue);
    }
}
