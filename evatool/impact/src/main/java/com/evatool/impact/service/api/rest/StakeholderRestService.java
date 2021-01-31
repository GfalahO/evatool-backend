package com.evatool.impact.service.api.rest;

import com.evatool.impact.persistence.entity.Stakeholder;
import com.evatool.impact.service.EntityNotFoundException;

import java.util.List;

public interface StakeholderRestService {
    public Stakeholder getStakeholderById(String id) throws EntityNotFoundException;

    public List<Stakeholder> getAllStakeholders();

    public Stakeholder insertStakeholder(Stakeholder stakeholderDto); // TODO: Throw entity already exists exception?

    public Stakeholder updateStakeholder(Stakeholder stakeholderDto) throws EntityNotFoundException;

    public void deleteStakeholderById(String id) throws EntityNotFoundException;
}
