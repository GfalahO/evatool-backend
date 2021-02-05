package com.FAE.EVATool.Analysis.API.Interfaces;
import com.FAE.EVATool.Analysis.Model.Analysis;
import com.FAE.EVATool.Analysis.Model.Stakeholder;

public interface StakeholderController {

    void addStakeholder(Stakeholder stakeholder);
    Stakeholder getStakeholderById(String stakeholderId);
}
