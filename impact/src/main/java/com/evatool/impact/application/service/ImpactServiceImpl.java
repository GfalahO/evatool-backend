package com.evatool.impact.application.service;

import com.evatool.impact.application.dto.ImpactDto;
import com.evatool.impact.application.dto.mapper.ImpactMapper;
import com.evatool.impact.common.exception.EntityNotFoundException;
import com.evatool.impact.domain.entity.Impact;
import com.evatool.impact.domain.repository.DimensionRepository;
import com.evatool.impact.domain.repository.ImpactRepository;
import com.evatool.impact.domain.repository.ImpactStakeholderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImpactServiceImpl implements ImpactService {
    @Autowired
    private ImpactRepository impactRepository;

    @Autowired
    private ImpactStakeholderRepository impactStakeholderRepository;

    @Autowired
    private DimensionRepository dimensionRepository;

    @Override
    public ImpactDto findImpactById(String id) throws EntityNotFoundException {
        if (id == null) {
            throw new EntityNotFoundException(Impact.class, "null");
        }
        var impact = impactRepository.findById(id);
        if (impact.isEmpty()) {
            throw new EntityNotFoundException(Impact.class, id);
        }
        var impactDto = ImpactMapper.toDto(impact.get());
        return impactDto;
    }

    @Override
    public List<ImpactDto> getAllImpacts() {
        var impacts = impactRepository.findAll();
        var impactDtoList = new ArrayList<ImpactDto>();
        impacts.forEach(s -> impactDtoList.add(ImpactMapper.toDto(s)));
        return impactDtoList;
    }

    @Override
    public ImpactDto createImpact(ImpactDto impactDto) {
        var impact = impactRepository.save(ImpactMapper.fromDto(impactDto));
        this.retrieveImpactRelations(impactDto, impact);
        return ImpactMapper.toDto(impact);
    }

    @Override
    public ImpactDto updateImpact(ImpactDto impactDto) throws EntityNotFoundException {
        this.findImpactById(impactDto.getId());
        var impact = ImpactMapper.fromDto(impactDto);
        this.retrieveImpactRelations(impactDto, impact);
        return ImpactMapper.toDto(impactRepository.save(impact));
    }

    @Override
    public void deleteImpactById(String id) throws EntityNotFoundException {
        var impactDto = this.findImpactById(id);
        var impact = ImpactMapper.fromDto(impactDto);
        this.retrieveImpactRelations(impactDto, impact);
        impactRepository.delete(impact);
    }

    @Override
    public void deleteImpacts() {
        impactRepository.deleteAll();
    }

    private void retrieveImpactRelations(ImpactDto impactDto, Impact impact) {
        if (impactDto.getDimensionId() != null) {
            var dimension = dimensionRepository.findById(impactDto.getDimensionId());
            if (!dimension.isEmpty()) {
                impact.setDimension(dimension.get());
            }
        }
        if (impactDto.getStakeholderId() != null) {
            var stakeholder = impactStakeholderRepository.findById(impactDto.getDimensionId());
            if (!stakeholder.isEmpty()) {
                impact.setStakeholder(stakeholder.get());
            }
        }
    }
}
