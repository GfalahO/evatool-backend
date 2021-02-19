package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.ImpactStakeholderDto;

import java.util.List;

public interface ImpactStakeholderService {
    ImpactStakeholderDto findStakeholderById(String id);

    List<ImpactStakeholderDto> getAllStakeholders();

    ImpactStakeholderDto createStakeholder(ImpactStakeholderDto impactStakeholderDto);

    ImpactStakeholderDto updateStakeholder(ImpactStakeholderDto impactStakeholderDto);

    void deleteStakeholderById(String id);

    void deleteStakeholders();
}
