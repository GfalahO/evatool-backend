package com.evatool.variants.services;

import com.evatool.variants.controller.VariantController;
import com.evatool.variants.entities.Variant;
import com.evatool.variants.entities.VariantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VariantMapper {

    @Autowired
    VariantController variantController;

    public List<VariantDto> mapAll(List<Variant> variantList) {
        List<VariantDto> variantDtoList = new ArrayList<>();
        for(Variant variant : variantList) {
            variantDtoList.add(map(variant));
        }
        return variantDtoList;
    }

    private VariantDto map(Variant variant) {
        VariantDto variantDto = new VariantDto();
        variantDto.setTitle(variant.getTitle());
        variantDto.setCriterion(variant.getCriterion());

        variantDto.setVariantsStakeholder(variant.getVariantsStakeholder());

        variantDto.setDescription(variant.getDescription());

        variantDto.setStFlagsPot(variant.isStFlagsPot());
        variantDto.setStFlagsReal(variant.isStFlagsReal());

        variant.getSubVariant().forEach(subVariant -> {
            variantDto.setSubVariant(subVariant.getSubVariant());
        });

        variant.getVariantsAnalyses().forEach(analysis -> {
            variantDto.setVariantsAnalysisId(analysis.getId());
        });
        variant.getVariantsRequirements().forEach(requirement -> {
            variantDto.setVariantsRequirementId(requirement.getId());
        });

        return variantDto;
    }

}