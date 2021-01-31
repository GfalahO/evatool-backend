package com.evatool.impact.common.mapper;

import com.evatool.impact.common.dto.ImpactDto;
import com.evatool.impact.persistence.entity.Impact;
import org.modelmapper.ModelMapper;

public class ImpactMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public Impact fromDto(ImpactDto impactDto) {
        return modelMapper.map(impactDto, Impact.class);
    }

    public ImpactDto toDto(Impact impact) {
        return modelMapper.map(impact, ImpactDto.class);
    }
}
