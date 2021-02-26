package com.evatool.variants.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class VariantDto {

    private UUID uuid;
    private String title;
    private String criterion;

    private CollectionModel<Variant> subVariant;

    private String description;

    private boolean stFlagsPot;
    private boolean stFlagsReal;

    private RepresentationModel<VariantsStakeholder> variantsStakeholder;
//    private UUID variantsStakeholderId;

    private CollectionModel<VariantsAnalysis> variantsAnalyses;
//    private UUID variantsAnalysisId;

    private CollectionModel<VariantsRequirement> variantsRequirements;
//    private UUID variantsRequirementId;
}