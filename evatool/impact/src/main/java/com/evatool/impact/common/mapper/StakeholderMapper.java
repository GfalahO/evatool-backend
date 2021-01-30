package com.evatool.impact.common.mapper;

import com.evatool.impact.common.dto.DimensionDto;
import com.evatool.impact.common.dto.StakeholderDto;
import com.evatool.impact.persistence.entity.Dimension;
import com.evatool.impact.persistence.entity.Stakeholder;

public class StakeholderMapper {

    public StakeholderMapper() {

    }

    public Stakeholder fromStakeholderDto(StakeholderDto stakeholderDto) {
        var stakeholder = new Stakeholder();

        stakeholder.setId(stakeholderDto.getId());
        stakeholder.setName(stakeholderDto.getName());

        return stakeholder;
    }

    public StakeholderDto toStakeholderDto(Stakeholder stakeholder) {
        var stakeholderDto = new StakeholderDto();

        stakeholderDto.setId(stakeholder.getId());
        stakeholderDto.setName(stakeholder.getName());

        return stakeholderDto;
    }
}
