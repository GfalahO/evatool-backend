package com.evatool.impact.service.api.rest;

import com.evatool.impact.persistence.entity.Stakeholder;
import com.evatool.impact.exception.EntityNotFoundException;
import com.evatool.impact.exception.IdNullException;

import java.util.List;

public interface StakeholderRestService {
    public Stakeholder getStakeholderById(String id) throws EntityNotFoundException, IdNullException;

    public List<Stakeholder> getAllStakeholders();

    public Stakeholder insertStakeholder(Stakeholder stakeholderDto);

    public Stakeholder updateStakeholder(Stakeholder stakeholderDto) throws EntityNotFoundException, IdNullException;

    public void deleteStakeholderById(String id) throws EntityNotFoundException, IdNullException;
}
