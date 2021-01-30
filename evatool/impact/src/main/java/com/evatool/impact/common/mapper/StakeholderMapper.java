package com.evatool.impact.common.mapper;

import com.evatool.impact.common.dto.StakeholderDto;
import com.evatool.impact.persistence.entity.Stakeholder;
import org.modelmapper.ModelMapper;

public class StakeholderMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public Stakeholder fromDto(StakeholderDto stakeholderDto) {
        return modelMapper.map(stakeholderDto, Stakeholder.class);
    }

    public StakeholderDto toDto(Stakeholder stakeholder) {
        return modelMapper.map(stakeholder, StakeholderDto.class);
    }
}
