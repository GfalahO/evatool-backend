package com.evatool.impact.application.dto.mapper;

import com.evatool.impact.application.dto.StakeholderDto;
import com.evatool.impact.domain.entity.ImpactStakeholder;
import org.modelmapper.ModelMapper;

public class StakeholderMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static ImpactStakeholder fromDto(StakeholderDto stakeholderDto) {
        return modelMapper.map(stakeholderDto, ImpactStakeholder.class);
    }

    public static StakeholderDto toDto(ImpactStakeholder stakeholder) {
        return modelMapper.map(stakeholder, StakeholderDto.class);
    }
}
