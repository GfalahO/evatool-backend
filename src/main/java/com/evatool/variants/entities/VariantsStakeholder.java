package com.evatool.variants.entities;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "VARIANTS_STAKEHOLDER")
@Getter
public class VariantsStakeholder extends RepresentationModel<VariantsStakeholder> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;
}