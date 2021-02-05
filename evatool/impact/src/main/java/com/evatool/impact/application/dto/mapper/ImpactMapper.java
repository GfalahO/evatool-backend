package com.evatool.impact.application.dto.mapper;

import com.evatool.impact.application.dto.ImpactDto;
import com.evatool.impact.domain.entity.Impact;
import org.modelmapper.ModelMapper;

public class ImpactMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static Impact fromDto(ImpactDto impactDto) {
        return modelMapper.map(impactDto, Impact.class);
    }

    public static ImpactDto toDto(Impact impact) {
        return modelMapper.map(impact, ImpactDto.class);
    }
}
