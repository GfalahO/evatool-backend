package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.StakeholderDto;
import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.common.exception.InvalidUuidException;
import com.evatool.impact.common.exception.PropertyViolationException;

import java.util.List;

public interface ImpactStakeholderService {
    StakeholderDto findStakeholderById(String id) ;

    List<StakeholderDto> getAllStakeholders();

    StakeholderDto createStakeholder(StakeholderDto stakeholderDto);

    StakeholderDto updateStakeholder(StakeholderDto stakeholderDto);

    void deleteStakeholderById(String id) ;

    void deleteStakeholders();
}
