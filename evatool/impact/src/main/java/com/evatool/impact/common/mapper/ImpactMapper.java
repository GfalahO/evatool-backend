package com.evatool.impact.common.mapper;

import com.evatool.impact.common.dto.ImpactDto;
import com.evatool.impact.persistence.entity.Impact;
import com.evatool.impact.persistence.repository.DimensionRepository;
import com.evatool.impact.service.api.rest.DimensionRestService;
import com.evatool.impact.service.api.rest.StakeholderRestService;
import com.evatool.impact.service.impl.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

public class ImpactMapper {
    @Autowired
    private StakeholderRestService stakeholderRestService;

    private ModelMapper modelMapper = new ModelMapper();

    public Impact fromDto(ImpactDto impactDto) throws EntityNotFoundException {
        var impact = modelMapper.map(impactDto, Impact.class);

        if (impactDto.getStakeholderId() != null) {
            var stakeholder = stakeholderRestService.getStakeholderById(impactDto.getDimensionId());
            if (stakeholder == null) {
                throw new EntityNotFoundException(String.format("Dimenion with id %d not found.", impactDto.getDimensionId()));
            }
            impact.setStakeholder(stakeholder);
        }

        return impact;
    }

    public ImpactDto toDto(Impact impact) {
        return modelMapper.map(impact, ImpactDto.class);
    }
}
