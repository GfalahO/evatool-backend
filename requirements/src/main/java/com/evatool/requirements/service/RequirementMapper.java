package com.evatool.requirements.service;

import com.evatool.requirements.controller.RequirementGRController;
import com.evatool.requirements.dto.RequirementDTO;
import com.evatool.requirements.entity.RequirementsImpacts;
import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.entity.RequirementGR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class RequirementMapper {

    @Autowired
    RequirementGRController requirement_grController;

    public List<RequirementDTO> map(List<Requirement> resultList) {
        List<RequirementDTO> requirementDTOList = new ArrayList<>();
        for(Requirement requirement : resultList){
            requirementDTOList.add(map(requirement));
        }
        return requirementDTOList;
    }

    private RequirementDTO map(Requirement requirement) {
        RequirementDTO requirementDTO = new RequirementDTO();
        requirementDTO.setRequirementTitle(requirement.getTitel());
        requirementDTO.setRequirementDescription(requirement.getDescription());
        requirement.getVariants().forEach(variants->{
            requirementDTO.setVariantsTitle(variants.getTitel());
            requirementDTO.setVariantsUUID(variants.getId());

        });
        Collection<RequirementsImpacts> requirementsImpactsList = requirement_grController.getRequirement_grByRequirement(requirement.getId());
        requirementsImpactsList.forEach(inpacts -> {
            requirementDTO.getImpactTitles().put(inpacts.getId(),inpacts.getTitel());


            requirementDTO.getDimensions().add(inpacts.getDimension());
            Collection<RequirementGR> requirement_grCollection = requirement_grController.getRequirement_grByRequirementList(requirement,inpacts);
            //TODO keine Berechnung sondern nur den Wert
            requirementDTO.getRequirementImpactPoints().put(inpacts.getId(),calculatePoints(inpacts,requirement_grCollection));
        });



        return requirementDTO;
    }

    private Integer calculatePoints(RequirementsImpacts requirementsImpacts, Collection<RequirementGR> requirement_grCollection) {
        int basicValue = requirementsImpacts.getValue();
        int returnValue = requirementsImpacts.getValue();

        for(RequirementGR requirement_gr :requirement_grCollection){
            returnValue+=requirement_gr.getPoints();
        }

        if(basicValue<0 && returnValue>0){
            return new Integer(0);
        }

        return new Integer(returnValue);
    }
}
