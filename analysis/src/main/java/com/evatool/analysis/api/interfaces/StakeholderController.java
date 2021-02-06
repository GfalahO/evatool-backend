package com.evatool.analysis.api.interfaces;

import com.evatool.analysis.model.Stakeholder;

public interface StakeholderController {

    void addStakeholder(Stakeholder stakeholder);

    Stakeholder getStakeholderById(String stakeholderId);
}
