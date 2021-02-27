package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.ImpactStakeholderDto;

import java.util.List;
import java.util.UUID;

public interface ImpactStakeholderService {

    ImpactStakeholderDto findById(UUID id);

    List<ImpactStakeholderDto> findAll();

    void deleteAll();
}
