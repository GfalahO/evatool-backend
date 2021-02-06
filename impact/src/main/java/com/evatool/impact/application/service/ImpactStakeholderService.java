package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.StakeholderDto;
import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.common.exception.IdNullException;

import java.util.List;

public interface ImpactStakeholderService {
    StakeholderDto findStakeholderById(String id) throws EntityNotFoundException, IdNullException;

    List<StakeholderDto> getAllStakeholders();

    StakeholderDto createStakeholder(StakeholderDto stakeholderDto);

    StakeholderDto updateStakeholder(StakeholderDto stakeholderDto) throws EntityNotFoundException, IdNullException;

    void deleteStakeholderById(String id) throws EntityNotFoundException, IdNullException;

    void deleteStakeholders();
}
