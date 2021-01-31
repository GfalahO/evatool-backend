package com.evatool.impact.common.controller.rest;

import com.evatool.impact.common.dto.ImpactDto;
import com.evatool.impact.common.dto.StakeholderDto;
import com.evatool.impact.common.mapper.DimensionMapper;
import com.evatool.impact.common.mapper.ImpactMapper;
import com.evatool.impact.common.mapper.StakeholderMapper;
import com.evatool.impact.persistence.entity.Impact;
import com.evatool.impact.persistence.entity.Stakeholder;

public class ImpactRestController {


    private ImpactMapper impactMapper = new ImpactMapper();
    private StakeholderMapper stakeholderMapper = new StakeholderMapper();
    private DimensionMapper dimensionMapper = new DimensionMapper();


}
