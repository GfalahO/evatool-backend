package com.evatool.impact.service.api.rest;

import com.evatool.impact.persistence.entity.Stakeholder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface StakeholderRestService {
    public Stakeholder getStakeholderById(String id);

    public Stakeholder saveStakeholder(Stakeholder stakeholder);

    public void deleteStakeholderById(String id);

    public List<Stakeholder> getAllStakeholders();
}
