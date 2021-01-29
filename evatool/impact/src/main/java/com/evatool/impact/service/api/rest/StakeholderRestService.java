package com.evatool.impact.service.api.rest;

import com.evatool.impact.common.dto.StakeholderDto;
import com.evatool.impact.service.impl.StakeholderNotFoundException;

import java.util.List;

public interface StakeholderRestService {
    public StakeholderDto getStakeholderById(String id) throws StakeholderNotFoundException;

    public List<StakeholderDto> getAllStakeholders();

    public StakeholderDto insertStakeholder(StakeholderDto stakeholderDto);

    public StakeholderDto updateStakeholder(StakeholderDto stakeholderDto) throws StakeholderNotFoundException;

    public void deleteStakeholderById(String id) throws StakeholderNotFoundException;
}
