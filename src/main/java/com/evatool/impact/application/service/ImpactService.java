package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.ImpactDto;

import java.util.List;
import java.util.UUID;

public interface ImpactService {

    ImpactDto findImpactById(UUID id);

    List<ImpactDto> getAllImpacts();

    ImpactDto createImpact(ImpactDto impactDto);

    ImpactDto updateImpact(ImpactDto impactDto);

    void deleteImpactById(UUID id);

    void deleteImpacts();
}
