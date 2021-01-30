package com.evatool.impact.common.mapper;

import com.evatool.impact.common.dto.ImpactDto;
import com.evatool.impact.persistence.entity.Impact;
import com.evatool.impact.persistence.repository.DimensionRepository;
import com.evatool.impact.persistence.repository.StakeholderRepository;
import com.evatool.impact.service.impl.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

public class ImpactMapper {
    @Autowired
    private DimensionRepository dimensionRepository;

    @Autowired
    private StakeholderRepository stakeholderRepository;

    public ImpactMapper() {

    }

    public Impact fromImpactDto(ImpactDto impactDto) throws EntityNotFoundException {
        var impact = new Impact();

        impact.setId(impactDto.getId());
        impact.setValue(impactDto.getValue());
        impact.setDescription(impactDto.getDescription());

        var dimension = dimensionRepository.findById(impactDto.getDimensionId()).orElse(null);
        if (dimension == null) {
            throw new EntityNotFoundException(String.format("Dimension with id '%s' not found.", impactDto.getDimensionId()));
        }
        impact.setDimension(dimension);

        var stakeholder = stakeholderRepository.findById(impactDto.getStakeholderId()).orElse(null);
        if (stakeholder == null) {
            throw new EntityNotFoundException(String.format("Dimension with id '%s' not found.", impactDto.getStakeholderId()));
        }
        impact.setStakeholder(stakeholder);

        return impact;
    }

    public ImpactDto toImpactDto(Impact impact) {
        var impactDto = new ImpactDto();

        impactDto.setId(impact.getId());
        impactDto.setValue(impact.getValue());
        impactDto.setDescription(impact.getDescription());
        impactDto.setDimensionId(impact.getDimension().getId());
        impactDto.setStakeholderId(impact.getStakeholder().getId());

        return impactDto;
    }
}
