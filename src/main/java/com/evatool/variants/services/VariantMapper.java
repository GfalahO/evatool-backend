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
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class VariantMapper {

    @Autowired
    VariantController variantController;

    public List<VariantDto> mapAll(List<Variant> variantList) {
        List<VariantDto> variantDtoList = new ArrayList<>();
        for (Variant variant : variantList) {
            variantDtoList.add(toDto(variant));
        }
        return variantDtoList;
    }

    public VariantDto toDto(Variant variant) {

        VariantDto variantDto = new VariantDto();
        variantDto.setUuid(variant.getId());
        variantDto.setTitle(variant.getTitle());
        variantDto.setCriterion(variant.getCriterion());

        if (variant.getVariantsStakeholder() != null) {
            Link stakeholderLink = linkTo(methodOn(StakeholderController.class).getStakeholderList()).withSelfRel();
            EntityModel<VariantsStakeholder> variantsStakeholder = EntityModel.of(variant.getVariantsStakeholder());
            variantsStakeholder.add(stakeholderLink);
            variantDto.setVariantsStakeholder(variantsStakeholder);
        }

        variantDto.setDescription(variant.getDescription());

        variantDto.setStFlagsPot(variant.isStFlagsPot());
        variantDto.setStFlagsReal(variant.isStFlagsReal());

        if (variant.getSubVariant() != null){
            Link subVariantLink = linkTo(methodOn(VariantController.class).getAllVariants()).withSelfRel();
            List<Variant> variantList = new ArrayList<>();
            variantList.addAll(variant.getSubVariant());
            CollectionModel<Variant> subVariantCollectionModel = CollectionModel.of(variantList);
            subVariantCollectionModel.add(subVariantLink);
        }

        if (variant.getVariantsAnalyses() != null){
            Link variantAnalysisLink = linkTo(methodOn(AnalysisController.class).getAnalysisList()).withSelfRel();
            List<VariantsAnalysis> variantsAnalysisList = new ArrayList<>();
            variantsAnalysisList.addAll(variant.getVariantsAnalyses());
            CollectionModel<VariantsAnalysis> analysisCollectionModel = CollectionModel.of(variantsAnalysisList);
            analysisCollectionModel.add(variantAnalysisLink);
        }

        if (variant.getVariantsRequirements() != null) {
            Link variantRequirementLink = linkTo(methodOn(RequirementsController.class).getRequirementList()).withSelfRel();
            List<VariantsRequirement> variantsRequirementsList = new ArrayList<>();
            variantsRequirementsList.addAll(variant.getVariantsRequirements());
            CollectionModel<VariantsRequirement> variantsRequirementCollectionModel = CollectionModel.of(variantsRequirementsList);
            variantsRequirementCollectionModel.add(variantRequirementLink);
        }

        return variantDto;
    }

    public Variant fromDto(VariantDto variantDto) {
        Variant variant = new Variant();
        variant.setId(variantDto.getUuid());
        variant.setTitle(variantDto.getTitle());
        variant.setCriterion(variantDto.getCriterion());
        if(variantDto.getSubVariant() != null) {
            variant.setSubVariant(variantDto.getSubVariant().getContent().stream().collect(Collectors.toList()));
        }
        variant.setDescription(variantDto.getDescription());
        variant.setStFlagsPot(variantDto.isStFlagsPot());
        variant.setStFlagsReal(variantDto.isStFlagsReal());
        variant.setVariantsStakeholder(variantDto.getVariantsStakeholder().getContent());
        if(variantDto.getVariantsAnalyses() != null) {
            variant.setVariantsAnalyses(variantDto.getVariantsAnalyses().getContent().stream().collect(Collectors.toList()));
        }
        if (variantDto.getVariantsRequirements() != null){
            variant.setVariantsRequirements(variantDto.getVariantsRequirements().getContent().stream().collect(Collectors.toList()));
        }


        return variant;
    }

}