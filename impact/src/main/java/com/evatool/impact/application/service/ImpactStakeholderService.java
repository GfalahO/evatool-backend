package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.StakeholderDto;

import java.util.List;

public interface ImpactStakeholderService {
    StakeholderDto findStakeholderById(String id);

    List<StakeholderDto> getAllStakeholders();

    StakeholderDto createStakeholder(StakeholderDto stakeholderDto);

    StakeholderDto updateStakeholder(StakeholderDto stakeholderDto);

    void deleteStakeholderById(String id);

    void deleteStakeholders();
}
