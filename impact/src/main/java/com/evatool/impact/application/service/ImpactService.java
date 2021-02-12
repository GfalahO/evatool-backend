package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.ImpactDto;
import com.evatool.impact.common.exception.EntityNotFoundException;

import java.util.List;

public interface ImpactService {

    ImpactDto findImpactById(String id) throws EntityNotFoundException;

    List<ImpactDto> getAllImpacts();

    ImpactDto createImpact(ImpactDto impactDto);

    ImpactDto updateImpact(ImpactDto impactDto) throws EntityNotFoundException;

    void deleteImpactById(String id) throws EntityNotFoundException;

    void deleteImpacts();
}
