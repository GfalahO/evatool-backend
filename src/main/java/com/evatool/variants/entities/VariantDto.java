package com.evatool.variants.entities;

import com.evatool.variants.entities.Variant;
import com.evatool.variants.entities.VariantAnalysis;
import com.evatool.variants.entities.VariantsRequirement;
import com.evatool.variants.entities.VariantsStakeholder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class VariantDto {

    private UUID uuid;
    private String title;
    private String criterion;

    private List<Variant> subVariant;

    private String description;

    private boolean stFlagsPot;
    private boolean stFlagsReal;

    private VariantsStakeholder variantsStakeholder;
    private UUID variantsStakeholderId;

    private List<VariantAnalysis> variantsAnalyses;
    private UUID variantsAnalysisId;

    private List<VariantsRequirement> variantsRequirements;
    private UUID variantsRequirementId;
}