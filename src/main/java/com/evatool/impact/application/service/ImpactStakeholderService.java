package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.ImpactStakeholderDto;

import java.util.List;
import java.util.UUID;

public interface ImpactStakeholderService {
    ImpactStakeholderDto findStakeholderById(UUID id);

    List<ImpactStakeholderDto> getAllStakeholders();

    ImpactStakeholderDto createStakeholder(ImpactStakeholderDto impactStakeholderDto);

    ImpactStakeholderDto updateStakeholder(ImpactStakeholderDto impactStakeholderDto);

    void deleteStakeholderById(UUID id);

    void deleteStakeholders();
}
