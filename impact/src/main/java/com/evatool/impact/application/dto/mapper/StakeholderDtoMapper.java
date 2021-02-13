package com.evatool.impact.application.dto.mapper;

import com.evatool.impact.application.dto.StakeholderDto;
import com.evatool.impact.domain.entity.ImpactStakeholder;
import org.modelmapper.ModelMapper;

import java.util.UUID;

public class StakeholderDtoMapper {

    private StakeholderDtoMapper() {

    }

    private static final ModelMapper modelMapper = new ModelMapper();

    public static ImpactStakeholder fromDto(StakeholderDto stakeholderDto) {
        var stakeholder = modelMapper.map(stakeholderDto, ImpactStakeholder.class);
        if (stakeholderDto.getId() != null) {
            stakeholder.setId(stakeholderDto.getId());
        }
        return stakeholder;
    }

    public static StakeholderDto toDto(ImpactStakeholder stakeholder) {
        return modelMapper.map(stakeholder, StakeholderDto.class);
    }
}
