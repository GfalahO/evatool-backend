package com.evatool.impact.application.dto.mapper;

import com.evatool.impact.application.dto.ImpactDto;
import com.evatool.impact.domain.entity.Impact;
import com.evatool.impact.domain.repository.DimensionRepository;
import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class ImpactDtoMapper {

    private static final Logger logger = LoggerFactory.getLogger(ImpactDtoMapper.class);

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Impact fromDto(ImpactDto impactDto, DimensionRepository dimensionRepository, ImpactStakeholderRepository stakeholderRepository) {
        logger.info("Mapping Dto to Entity");
        var impact = modelMapper.map(impactDto, Impact.class);
        if (impactDto.getId() != null) {
            impact.setId(impactDto.getId());
        }
        impact.setDimension(dimensionRepository.findById(UUID.fromString(impactDto.getDimension().getId())).orElse(null));
        impact.setStakeholder(stakeholderRepository.findById(UUID.fromString(impactDto.getStakeholder().getId())).orElse(null));
        return impact;
    }

    public static ImpactDto toDto(Impact impact) {
        logger.info("Mapping Entity to Dto");
        return modelMapper.map(impact, ImpactDto.class);
    }
}
