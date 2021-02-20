package com.evatool.impact.application.dto.mapper;

import com.evatool.impact.application.dto.ImpactDto;
import com.evatool.impact.domain.entity.Impact;
import com.evatool.impact.domain.repository.DimensionRepository;
import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImpactDtoMapper {

    private ImpactDtoMapper() {

    }

    private static final Logger logger = LoggerFactory.getLogger(ImpactDtoMapper.class);

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Impact fromDto(ImpactDto impactDto, DimensionRepository dimensionRepository, ImpactStakeholderRepository stakeholderRepository) {
        logger.info("Mapping Dto to Entity");
        var impact = new Impact(
                impactDto.getValue(),
                impactDto.getDescription(),
                dimensionRepository.findById(impactDto.getDimension().getId()).orElse(null),
                stakeholderRepository.findById(impactDto.getStakeholder().getId()).orElse(null)
        );
        if (impactDto.getId() != null) {
            impact.setId(impactDto.getId());
        }
        return impact;
    }

    public static ImpactDto toDto(Impact impact) {
        logger.info("Mapping Entity to Dto");
        return modelMapper.map(impact, ImpactDto.class);
    }
}
