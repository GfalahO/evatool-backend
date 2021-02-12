package com.evatool.impact.application.dto.mapper;

import com.evatool.impact.application.dto.ImpactDto;
import com.evatool.impact.domain.entity.Impact;
import com.evatool.impact.domain.repository.DimensionRepository;
import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import org.modelmapper.ModelMapper;

public class ImpactDtoMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static Impact fromDto(ImpactDto impactDto, DimensionRepository dimensionRepository, ImpactStakeholderRepository stakeholderRepository) {
        var impact = modelMapper.map(impactDto, Impact.class);
        impact.setDimension(dimensionRepository.findById(impactDto.getDimension().getId()).orElse(null));
        impact.setStakeholder(stakeholderRepository.findById(impactDto.getStakeholder().getId()).orElse(null));
        return impact;
    }

    public static ImpactDto toDto(Impact impact) {
        return modelMapper.map(impact, ImpactDto.class);
    }
}
