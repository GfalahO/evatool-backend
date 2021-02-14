package com.evatool.impact.application.dto.mapper;

import com.evatool.impact.application.dto.StakeholderDto;
import com.evatool.impact.domain.entity.ImpactStakeholder;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImpactStakeholderDtoMapper {

    private static final Logger logger = LoggerFactory.getLogger(ImpactStakeholderDtoMapper.class);

    private static final ModelMapper modelMapper = new ModelMapper();

    public static ImpactStakeholder fromDto(StakeholderDto stakeholderDto) {
        logger.info("Mapping Dto to Entity");
        var stakeholder = modelMapper.map(stakeholderDto, ImpactStakeholder.class);
        if (stakeholderDto.getId() != null) {
            stakeholder.setId(stakeholderDto.getId());
        }
        return stakeholder;
    }

    public static StakeholderDto toDto(ImpactStakeholder stakeholder) {
        logger.info("Mapping Entity to Dto");
        return modelMapper.map(stakeholder, StakeholderDto.class);
    }
}
