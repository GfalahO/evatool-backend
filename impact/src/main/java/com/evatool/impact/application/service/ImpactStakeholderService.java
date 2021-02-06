package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.StakeholderDto;
import com.evatool.impact.common.exception.EntityNotFoundException;

import java.util.List;

public interface ImpactStakeholderService {
    StakeholderDto findStakeholderById(String id) throws EntityNotFoundException;

    List<StakeholderDto> getAllStakeholders();

    StakeholderDto createStakeholder(StakeholderDto stakeholderDto);

    StakeholderDto updateStakeholder(StakeholderDto stakeholderDto) throws EntityNotFoundException;

    void deleteStakeholderById(String id) throws EntityNotFoundException;

    void deleteStakeholders();
}
