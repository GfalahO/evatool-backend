package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.ImpactDto;
import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.common.exception.InvalidUuidException;
import com.evatool.impact.common.exception.PropertyViolationException;

import java.util.List;

public interface ImpactService {

    ImpactDto findImpactById(String id);

    List<ImpactDto> getAllImpacts();

    ImpactDto createImpact(ImpactDto impactDto) ;

    ImpactDto updateImpact(ImpactDto impactDto);

    void deleteImpactById(String id);

    void deleteImpacts();
}
