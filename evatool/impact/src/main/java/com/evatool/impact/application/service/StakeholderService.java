package com.evatool.impact.application.service;

import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.common.exception.IdNullException;
import com.evatool.impact.domain.entity.Stakeholder;

import java.util.List;

public interface StakeholderService {
    Stakeholder findStakeholderById(String id) throws EntityNotFoundException, IdNullException;

    List<Stakeholder> getAllStakeholders();

    Stakeholder createStakeholder(Stakeholder stakeholder);

    Stakeholder updateStakeholder(Stakeholder stakeholder) throws EntityNotFoundException, IdNullException;

    void deleteStakeholderById(String id) throws EntityNotFoundException, IdNullException;

    void deleteStakeholders();
}
