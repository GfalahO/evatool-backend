package com.evatool.analysis.services;

import com.evatool.analysis.dto.StakeholderDTO;
import com.evatool.analysis.api.controller.StakeholderControllerImpl;
import com.evatool.analysis.model.Stakeholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class StakeholderMapper {

    @Autowired
    StakeholderControllerImpl stakeholderController;

    public List<StakeholderDTO> map(List<Stakeholder> resultList) {
        List<StakeholderDTO> stakeholderDTOList = new ArrayList<>();
        for(Stakeholder stakeholder : resultList){
            stakeholderDTOList.add(map(stakeholder));
        }
        return stakeholderDTOList;
    }

    private StakeholderDTO map(Stakeholder stakeholder) {
        StakeholderDTO stakeholderDTO = new StakeholderDTO();
        stakeholderDTO.setPriority(stakeholder.getPriority());
        stakeholderDTO.setStakeholderName(stakeholder.getStakeholderName());
        stakeholderDTO.setStakeholderLevel(stakeholder.getStakeholderLevel());
        stakeholderDTO.setRootEntityID(stakeholder.getStakeholderId());
        stakeholderDTO.setImpacts(stakeholder.getAnalysisImpacts());
        // Calculation?
        return stakeholderDTO;
    }
}
