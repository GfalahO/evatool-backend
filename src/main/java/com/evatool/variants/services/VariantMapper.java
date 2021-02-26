package com.evatool.variants.services;

import com.evatool.analysis.api.interfaces.AnalysisController;
import com.evatool.analysis.api.interfaces.StakeholderController;
import com.evatool.requirements.controller.RequirementsController;
import com.evatool.variants.controller.VariantController;
import com.evatool.variants.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class VariantMapper {

    @Autowired
    VariantController variantController;

    public List<VariantDto> mapAll(List<Variant> variantList) {
        List<VariantDto> variantDtoList = new ArrayList<>();
        for (Variant variant : variantList) {
            variantDtoList.add(map(variant));
        }
        return variantDtoList;
    }

    private VariantDto map(Variant variant) {

        VariantDto variantDto = new VariantDto();
        variantDto.setTitle(variant.getTitle());
        variantDto.setCriterion(variant.getCriterion());

        Link stakeholderLink = linkTo(methodOn(StakeholderController.class).getStakeholderById(variant.getVariantsStakeholder().getId())).withSelfRel();
        EntityModel<VariantsStakeholder> variantsStakeholders = EntityModel.of(variant.getVariantsStakeholder());
        variantsStakeholders.add(stakeholderLink);

        variantDto.setVariantsStakeholder(variant.getVariantsStakeholder());

        variantDto.setDescription(variant.getDescription());

        variantDto.setStFlagsPot(variant.isStFlagsPot());
        variantDto.setStFlagsReal(variant.isStFlagsReal());

        Link subVariantLink = linkTo(methodOn(VariantController.class).getAllVariants()).withSelfRel();
        CollectionModel<Variant> subVariantCollectionModel = CollectionModel.of(variant.getSubVariant());
        subVariantCollectionModel.add(subVariantLink);

        Link variantAnalysisLink = linkTo(methodOn(AnalysisController.class).getAnalysisList()).withSelfRel();
        CollectionModel<VariantsAnalysis> analysisCollectionModel = CollectionModel.of(variant.getVariantsAnalyses());
        analysisCollectionModel.add(variantAnalysisLink);

        Link variantRequirementLink = linkTo(methodOn(RequirementsController.class).getRequirementList()).withSelfRel();
        CollectionModel<VariantsRequirement> variantsRequirementCollectionModel = CollectionModel.of(variant.getVariantsRequirements());
        variantsRequirementCollectionModel.add(variantRequirementLink);

        return variantDto;
    }

}