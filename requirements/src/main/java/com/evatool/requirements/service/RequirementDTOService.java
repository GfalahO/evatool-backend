package com.evatool.requirements.service;

import com.evatool.requirements.dto.RequirementDTO;
import com.evatool.requirements.entity.Requirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class RequirementDTOService {

    @Autowired
    RequirementMapper requirementMapper;

    public List<RequirementDTO> findAll(List<Requirement> resultList) {
        return requirementMapper.map(resultList);
    }
}
