package com.evatool.requirements.service;

import com.evatool.requirements.dto.RequirementDTO;
import com.evatool.requirements.entity.Requirement;
import com.evatool.requirements.entity.RequirementsAnalysis;
import com.evatool.requirements.entity.RequirementsVariant;
import com.evatool.requirements.repository.RequirementAnalysisRepository;
import com.evatool.requirements.repository.RequirementsVariantsRepository;
import com.evatool.variants.entities.VariantsRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RequirementDTOService {

    Logger logger = LoggerFactory.getLogger(RequirementDTOService.class);

    @Autowired
    RequirementMapper requirementMapper;

    @Autowired
    RequirementsVariantsRepository requirementsVariantsRepository;

    @Autowired
    RequirementAnalysisRepository requirementAnalysisRepository;

    public List<RequirementDTO> findAll(Collection<Requirement> resultList) {
        logger.debug("findAll [{}]",resultList);
        return requirementMapper.mapList(resultList);
    }

    public RequirementDTO findId(Requirement requirement) {
        logger.debug("findId [{}]",requirement);
        return requirementMapper.map(requirement);
    }

    public Requirement create(RequirementDTO requirementDTO) {
        logger.debug("create [{}]",requirementDTO);
        Requirement requirement = new Requirement();
        requirement.setTitle(requirementDTO.getRequirementTitle());
        requirement.setDescription(requirementDTO.getRequirementDescription());
        Optional<RequirementsAnalysis> requirementsAnalysis = requirementAnalysisRepository.findById(requirementDTO.getProjectID());
        requirement.setRequirementsAnalysis(requirementsAnalysis.get());
        Collection<RequirementsVariant> requirementsVariantCollection = new ArrayList<>();
        for( Map.Entry<UUID, String> entry:requirementDTO.getVariantsTitle().entrySet()) {
            requirementsVariantCollection.add(requirementsVariantsRepository.getOne(entry.getKey()));
        }
        requirement.setVariants(requirementsVariantCollection);
        return requirement;
    }

}
