package com.evatool.impact.application.service;

import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.common.exception.IdNullException;
import com.evatool.impact.domain.entity.ImpactStakeholder;

import java.util.List;

public interface ImpactStakeholderService {
    ImpactStakeholder findStakeholderById(String id) throws EntityNotFoundException, IdNullException;

    List<ImpactStakeholder> getAllStakeholders();

    ImpactStakeholder createStakeholder(ImpactStakeholder stakeholder);

    ImpactStakeholder updateStakeholder(ImpactStakeholder stakeholder) throws EntityNotFoundException, IdNullException;

    void deleteStakeholderById(String id) throws EntityNotFoundException, IdNullException;

    void deleteStakeholders();
}
