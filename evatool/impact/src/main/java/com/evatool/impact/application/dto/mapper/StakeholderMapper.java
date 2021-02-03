package com.evatool.impact.application.dto.mapper;

import com.evatool.impact.application.dto.StakeholderDto;
import com.evatool.impact.domain.entity.Stakeholder;
import org.modelmapper.ModelMapper;

public class StakeholderMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static Stakeholder fromDto(StakeholderDto stakeholderDto) {
        return modelMapper.map(stakeholderDto, Stakeholder.class);
    }

    public static StakeholderDto toDto(Stakeholder stakeholder) {
        return modelMapper.map(stakeholder, StakeholderDto.class);
    }
}
