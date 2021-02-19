package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.ImpactDto;

import java.util.List;

public interface ImpactService {

    ImpactDto findImpactById(String id);

    List<ImpactDto> getAllImpacts();

    ImpactDto createImpact(ImpactDto impactDto);

    ImpactDto updateImpact(ImpactDto impactDto);

    void deleteImpactById(String id);

    void deleteImpacts();
}
