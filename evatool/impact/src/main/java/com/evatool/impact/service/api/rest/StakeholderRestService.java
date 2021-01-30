package com.evatool.impact.service.api.rest;

import com.evatool.impact.common.dto.StakeholderDto;
import com.evatool.impact.service.impl.EntityNotFoundException;

import java.util.List;

public interface StakeholderRestService {
    public StakeholderDto getStakeholderById(String id) throws EntityNotFoundException;

    public List<StakeholderDto> getAllStakeholders();

    public StakeholderDto insertStakeholder(StakeholderDto stakeholderDto);

    public StakeholderDto updateStakeholder(StakeholderDto stakeholderDto) throws EntityNotFoundException;

    public void deleteStakeholderById(String id) throws EntityNotFoundException;
}
