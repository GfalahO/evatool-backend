package com.evatool.variants.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "VARIANT")
@Getter
@Setter
public class Variant extends RepresentationModel<Variant> {

    @ApiModelProperty(notes = "Uuid of a Variant", name = "uuid", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ApiModelProperty(notes = "Title of a Variant", name = "title", required = true)
    private String title;

    @ApiModelProperty(notes = "Criterion of a Variant", name = "criterion", required = true)
    private String criterion;

    @ApiModelProperty(notes = "SubVariant of a Variant", name = "subVariant", required = true)
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<Variant> subVariant;

    @ApiModelProperty(notes = "Description of a Variant", name = "description", required = true)
    private String description;

    @ApiModelProperty(notes = "Potential flag of a Variant", name = "potentialFlag", required = true)
    private boolean stFlagsPot;

    @ApiModelProperty(notes = "Reality flag of a Variant", name = "realityFlag", required = true)
    private boolean stFlagsReal;

    @ApiModelProperty(notes = "Stakeholder of a Variant", name = "stakeholder", required = true)
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private VariantsStakeholder variantsStakeholder;

    @ApiModelProperty(notes = "Analysis of a Variant", name = "analysis", required = true)
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<VariantsAnalysis> variantsAnalyses;

    @ApiModelProperty(notes = "Requirement of a Variant", name = "requirement", required = true)
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<VariantsRequirement> variantsRequirements;
}