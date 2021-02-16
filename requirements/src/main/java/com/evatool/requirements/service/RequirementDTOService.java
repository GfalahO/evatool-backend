package com.evatool.requirements.service;

import com.evatool.requirements.dto.RequirementDTO;
import com.evatool.requirements.entity.Requirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class RequirementDTOService {

    Logger logger = LoggerFactory.getLogger(RequirementDTOService.class);

    @Autowired
    RequirementMapper requirementMapper;

    public List<RequirementDTO> findAll(List<Requirement> resultList) {
        logger.info("findAll");
        return requirementMapper.map(resultList);
    }
}
